package com.gantzgulch.tools.httpclient5.impl;

import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.tools.common.lang.*;
import com.gantzgulch.tools.httpclient5.GGHttpClient5;
import com.gantzgulch.tools.httpclient5.GGHttpClient5AuthType;
import com.gantzgulch.tools.httpclient5.GGHttpClient5Credentials;
import com.gantzgulch.tools.httpclient5.GGHttpClient5Response;
import org.apache.hc.client5.http.ContextBuilder;
import org.apache.hc.client5.http.auth.CredentialsProvider;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.entity.EntityBuilder;
import org.apache.hc.client5.http.entity.mime.*;
import org.apache.hc.client5.http.impl.auth.CredentialsProviderBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.protocol.HttpClientContext;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.util.Timeout;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class GGHttpClient5Impl implements GGHttpClient5 {


    public static final Timeout CONNECT_TIMEOUT = Timeout.of(15, TimeUnit.SECONDS);
    public static final Timeout CONNECT_SO_TIMEOUT = Timeout.of(30, TimeUnit.SECONDS);
    public static final Timeout CONNECTION_REQUEST_TIMEOUT = Timeout.of(60, TimeUnit.SECONDS);

    private static final GGLogger LOG = GGLogger.getLogger(GGHttpClient5Impl.class);

    private final String agent;
    private final boolean processRedirects;

    private final Timeout connectTimeout;
    private final Timeout connectSoTimeout;
    private final Timeout connectionRequestTimeout;

    private final PoolingHttpClientConnectionManager connectionManager;
    private final CloseableHttpClient httpClient;

    public GGHttpClient5Impl() {
        this(null, true, 1, CONNECT_TIMEOUT, CONNECT_SO_TIMEOUT, CONNECTION_REQUEST_TIMEOUT);
    }

    public GGHttpClient5Impl(
            final String agent,
            final boolean processRedirects,
            final int retryCount,
            final Timeout connectTimeout,
            final Timeout connectSoTimeout,
            final Timeout connectionRequestTimeout) {

        this.agent = agent;
        this.processRedirects = processRedirects;

        this.connectTimeout = connectTimeout;
        this.connectSoTimeout = connectSoTimeout;
        this.connectionRequestTimeout = connectionRequestTimeout;

        this.connectionManager = createConnectionManager();
        this.httpClient = createHttpClient(retryCount);
    }

    @Override
    public GGHttpClient5Credentials createCredentials(
            final URI uri,
            final GGHttpClient5AuthType authType,
            final String username,
            final String password) {

        final String scheme = uri.getScheme();
        final String host = uri.getHost();
        final int port = uri.getPort();

        return new GGHttpClient5CredentialsImpl(scheme, host, port, authType, username, password);
    }

    @Override
    public GGHttpClient5Credentials createCredentials(
            final String scheme,
            final String host,
            final int port,
            final GGHttpClient5AuthType authType,
            final String username,
            final String password) {

        return new GGHttpClient5CredentialsImpl(scheme, host, port, authType, username, password);

    }

    public HttpClientContext buildClientContext(final List<GGHttpClient5Credentials> credentialList) {

        final ContextBuilder builder = ContextBuilder.create();
        final CredentialsProviderBuilder providerBuilder = CredentialsProviderBuilder.create();

        GGStream
                .of(credentialList)
                .forEach(credentials -> {

                    final String scheme = credentials.getScheme();
                    final String hostname = credentials.getHost();
                    final int port = credentials.getPort();
                    final String username = credentials.getUsername();
                    final String password = credentials.getPassword();

                    final HttpHost target = new HttpHost(scheme, hostname, port);

                    switch (credentials.getAuthType()) {
                        case NONE:
                            break;
                        case BASIC_AUTH_PREEMPT:
                            builder.preemptiveBasicAuth(target, usernamePasswordCredentials(username, password));
                            break;
                        case DIGEST_AUTH:
                        case BASIC_AUTH:
                            providerBuilder.add(target, usernamePasswordCredentials(username, password));
                            break;
                    }

                });

        builder.useCredentialsProvider( providerBuilder.build() );

        return builder.build();
    }

    private UsernamePasswordCredentials usernamePasswordCredentials(final String username, final String password) {
        return new UsernamePasswordCredentials(username, password != null ? password.toCharArray() : null);
    }

    public HttpClientResponseHandler<String> stringHandler() {
        return new HttpClientResponseHandler<String>() {
            @Override
            public String handleResponse(final ClassicHttpResponse classicHttpResponse) throws HttpException, IOException {
                final HttpEntity entity = classicHttpResponse.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            }
        };
    }

    public HttpClientResponseHandler<Long> streamHandler(final OutputStream os) {
        return new HttpClientResponseHandler<Long>() {
            @Override
            public Long handleResponse(final ClassicHttpResponse classicHttpResponse) throws HttpException, IOException {
                final HttpEntity entity = classicHttpResponse.getEntity();
                final Long length = entity.getContentLength();
                entity.writeTo(os);
                return length;
            }
        };
    }


    public ContentBody toContentBody(final String content, final ContentType contentType) {
        return new StringBody(content, contentType);
    }

    public ContentBody toContentBody(final File content, final ContentType contentType, final String filename) {
        return new FileBody(content, contentType, filename);
    }

    public ContentBody toContentBody(final InputStream inputStream, final ContentType contentType, final String filename) {
        return new InputStreamBody(inputStream, contentType, filename);
    }

    @Override
    public HttpEntity toEntity(final File content, final ContentType contentType) {
        return EntityBuilder
                .create()
                .setContentType(contentType)
                .setFile(content)
                .build();
    }

    @Override
    public HttpEntity toEntity(final String content, final ContentType contentType) {
        return EntityBuilder
                .create()
                .setContentType(contentType)
                .setText(content)
                .build();
    }


    @Override
    public <T> GGHttpClient5Response<T> get(
            final URI uri,
            final Map<String, String> headers,
            final HttpClientContext httpClientContext,
            final HttpClientResponseHandler<T> handler) {

        LOG.trace("get: %s", uri);

        try {

            final HttpGet request = new HttpGet(uri);

            setHeaders(request, headers);

            return httpClient.execute(request, httpClientContext, response -> {

                final T t = handler.handleResponse(response);

                return new GGHttpClient5ResponseImpl<>(response.getCode(), t);

            });

        } catch (final IOException e) {
            LOG.warn(e, "get: Exception: %s", e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public <T> GGHttpClient5Response<T> head(
            final URI uri,
            final Map<String, String> headers,
            final HttpClientContext httpClientContext,
            final HttpClientResponseHandler<T> handler) {

        LOG.trace("head: %s", uri);

        try {

            final HttpHead request = new HttpHead(uri);

            setHeaders(request, headers);

            return httpClient.execute(request, httpClientContext, response -> {

                final T t = handler.handleResponse(response);

                return new GGHttpClient5ResponseImpl<>(response.getCode(), t);

            });

        } catch (final IOException e) {
            LOG.warn(e, "get: Exception: %s", e.getMessage());
            throw new RuntimeException(e);
        }


    }

    private HttpEntity buildEntity(final Map<String, ContentBody> content) {

        final MultipartEntityBuilder eb = MultipartEntityBuilder.create();

        //        b.setMode(HttpMultipartMode.LEGACY);

        if (content != null) {
            content.forEach(eb::addPart);
        }

        return eb.build();
    }

    public <T> GGHttpClient5Response<T> post(
            final URI uri,
            final Map<String, ContentBody> content,
            final Map<String, String> headers,
            final HttpClientContext httpClientContext,
            final HttpClientResponseHandler<T> handler) {

        LOG.trace("post: %s", uri);

        try {

            final HttpPost request = new HttpPost(uri);

            setHeaders(request, headers);

            request.setEntity(buildEntity(content));

            return httpClient.execute(request, httpClientContext, response -> {

                final T t = handler.handleResponse(response);

                return new GGHttpClient5ResponseImpl<>(response.getCode(), t);

            });

        } catch (final IOException e) {
            LOG.warn(e, "post: Exception: %s", e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public <T> GGHttpClient5Response<T> post(
            final URI uri,
            final HttpEntity entity,
            final Map<String, String> headers,
            final HttpClientContext httpClientContext,
            final HttpClientResponseHandler<T> handler) {

        LOG.trace("post: %s", uri);

        try {

            final HttpPost request = new HttpPost(uri);

            setHeaders(request, headers);

            request.setEntity(entity);

            return httpClient.execute(request, httpClientContext, response -> {

                final T t = handler.handleResponse(response);

                return new GGHttpClient5ResponseImpl<>(response.getCode(), t);

            });

        } catch (final IOException e) {
            LOG.warn(e, "post: Exception: %s", e.getMessage());
            throw new RuntimeException(e);
        }

    }


    public <T> GGHttpClient5Response<T> put(
            final URI uri,
            final Map<String, ContentBody> content,
            final Map<String, String> headers,
            final HttpClientContext httpClientContext,
            final HttpClientResponseHandler<T> handler) {

        LOG.trace("put: %s", uri);

        try {

            final HttpPut request = new HttpPut(uri);

            setHeaders(request, headers);

            request.setEntity(buildEntity(content));

            return httpClient.execute(request, httpClientContext, response -> {

                final T t = handler.handleResponse(response);

                return new GGHttpClient5ResponseImpl<>(response.getCode(), t);

            });

        } catch (final IOException e) {
            LOG.warn(e, "put: Exception: %s", e.getMessage());
            throw new RuntimeException(e);
        }

    }


    public <T> GGHttpClient5Response<T> put(
            final URI uri,
            final HttpEntity entity,
            final Map<String, String> headers,
            final HttpClientContext httpClientContext,
            final HttpClientResponseHandler<T> handler) {

        LOG.trace("put: %s", uri);

        try {

            final HttpPut request = new HttpPut(uri);

            setHeaders(request, headers);

            request.setEntity(entity);

            return httpClient.execute(request, httpClientContext, response -> {

                final T t = handler.handleResponse(response);

                return new GGHttpClient5ResponseImpl<>(response.getCode(), t);

            });

        } catch (final IOException e) {
            LOG.warn(e, "put: Exception: %s", e.getMessage());
            throw new RuntimeException(e);
        }

    }


//    @Override
//    public CloseableHttpResponse head(//
//                                      final URI uri, //
//                                      final Map<String, String> headers, //
//                                      final HttpClientContext httpClientContext) {
//
//        LOG.trace("head: %s", uri);
//
//        final HttpHead request = new HttpHead(uri);
//
//        setHeaders(request, headers);
//
//        return execute(request, httpClientContext);
//    }
//
//    @Override
//    public CloseableHttpResponse delete(//
//                                        final URI uri, //
//                                        final Map<String, String> headers, //
//                                        final HttpClientContext httpClientContext) {
//
//        LOG.trace("delete: %s", uri);
//
//        final HttpDelete request = new HttpDelete(uri);
//
//        setHeaders(request, headers);
//
//        return execute(request, httpClientContext);
//
//    }
//
//    @Override
//    public CloseableHttpResponse post(//
//                                      final URI uri, //
//                                      final Map<String, String> parameters, //
//                                      final Map<String, String> headers, //
//                                      final HttpClientContext clientContext) {
//
//        LOG.trace("post: %s", uri);
//
//        final HttpPost request = new HttpPost(uri);
//
//        setHeaders(request, headers);
//
//        final List<NameValuePair> params = toNameValuePairList(parameters);
//
//        request.setEntity(new StringEntity(URLEncodedUtils.format(params, "UTF-8"), GGUtf8.CHARSET));
//
//        return execute(request, clientContext);
//    }
//
//    @Override
//    public CloseableHttpResponse post(//
//                                      final URI uri, //
//                                      final String content, //
//                                      final Map<String, String> headers, //
//                                      final HttpClientContext clientContext) {
//
//        LOG.trace("post: %s:", uri);
//
//        final HttpPost request = new HttpPost(uri);
//
//        setHeaders(request, headers);
//
//        if (content != null) {
//
//            final StringEntity entity = new StringEntity(content, GGUtf8.CHARSET);
//
//            request.setEntity(entity);
//        }
//
//        return execute(request, clientContext);
//    }
//
//    @Override
//    public CloseableHttpResponse post(//
//                                      final URI uri, //
//                                      final InputStream content, //
//                                      final Map<String, String> headers, //
//                                      final HttpClientContext clientContext) {
//
//        LOG.trace("post: %s:", uri);
//
//        final HttpPost request = new HttpPost(uri);
//
//        setHeaders(request, headers);
//
//        if (content != null) {
//
//            final InputStreamEntity entity = new InputStreamEntity(content);
//
//            request.setEntity(entity);
//        }
//
//        return execute(request, clientContext);
//    }
//
//    public CloseableHttpResponse postMultiPart(URI uri, Map<String, ContentBody> content, Map<String, String> headers, HttpClientContext clientContext) {
//
//        LOG.trace("post: %s:", uri);
//
//        final MultipartEntityBuilder b = MultipartEntityBuilder.create();
//
//        b.setMode(HttpMultipartMode.LEGACY);
//
//        content.forEach((name, contentBody) -> {
//            b.addPart(name, contentBody);
//        });
//
//        final HttpEntity httpEntity = b.build();
//
//        final HttpPost request = new HttpPost(uri);
//
//        setHeaders(request, headers);
//
//        request.setEntity(httpEntity);
//
//        return execute(request, clientContext);
//
//    }
//
//    @Override
//    public CloseableHttpResponse put(//
//                                     final URI uri, //
//                                     final Map<String, String> parameters, //
//                                     final Map<String, String> headers, //
//                                     final HttpClientContext clientContext) {
//
//        LOG.trace("put: %s", uri);
//
//        final HttpPut request = new HttpPut(uri);
//
//        setHeaders(request, headers);
//
//        final List<NameValuePair> params = toNameValuePairList(parameters);
//
//        request.setEntity(new StringEntity(URLEncodedUtils.format(params, "UTF-8"), GGUtf8.CHARSET));
//
//        return execute(request, clientContext);
//    }
//
//    @Override
//    public CloseableHttpResponse put(//
//                                     final URI uri, //
//                                     final String content, //
//                                     final Map<String, String> headers, //
//                                     final HttpClientContext clientContext) {
//
//        LOG.trace("put: %s", uri);
//
//        final HttpPut request = new HttpPut(uri);
//
//        setHeaders(request, headers);
//
//        if (content != null) {
//
//            final StringEntity entity = new StringEntity(content, GGUtf8.CHARSET);
//
//            request.setEntity(entity);
//        }
//
//        return execute(request, clientContext);
//    }

    @Override
    public void close() {

        LOG.trace("Shutting down http client.");

        try {
            if (httpClient != null) {
                httpClient.close();
            }
        } catch (final Exception e) {
            LOG.warn(e, "close: Error closing http client.");
        }
    }

    @Override
    public PoolingHttpClientConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public void setHeaders(HttpRequest request, Map<String, String> headers) {

        if (request != null) {

            if (agent != null) {
                request.setHeader(HttpHeaders.USER_AGENT, agent);
            }

            if (headers != null) {
                headers.forEach(request::setHeader);
            }

        }
    }

    public List<NameValuePair> toNameValuePairList(final Map<String, String> parameters) {

        return GGStream
                .of(parameters != null ? parameters.entrySet() : null)
                .map(e -> {
                    return new BasicNameValuePair(e.getKey(), e.getValue());
                })
                .collect(Collectors.toList());
    }

    public String getFirstHeaderValue(final HttpResponse response, final String name) {

        final Header header = response.getFirstHeader(name);

        return header != null ? header.getValue() : null;

    }


//    private CloseableHttpResponse execute(
//            final HttpUriRequest request,
//            final HttpClientContext httpContext) {
//
//        httpContext.setAttribute(HttpClientContext.REDIRECT_LOCATIONS, new ArrayList<String>());
//
//        return executeWithRedirectChecks(request, httpContext);
//
//    }
//
//    private CloseableHttpResponse executeWithRedirectChecks(//
//                                                            final HttpUriRequest request, //
//                                                            final HttpClientContext httpContext) {
//
//        if (httpContext.getRedirectLocations().size() > 100) {
//            throw new GGHttpClient5Exception("Redirect count exceeded.");
//        }
//
//        CloseableHttpResponse response = null;
//
//        try {
//
//            LOG.trace("execute: Executing request : %s", request.getRequestUri());
//
//            if (GGStrings.isNotBlank(agent)) {
//                request.setHeader(HttpHeaders.USER_AGENT, agent);
//            }
//
//            response = httpClient.execute(request, httpContext);
//
//            final HttpUriRequest newRequest = checkForAndProcessRedirect(request, response, httpContext);
//
//            if (newRequest != null) {
//
//                HttpClientUtils.closeQuietly(response);
//
//                return executeWithRedirectChecks(newRequest, httpContext);
//            }
//
//            return response;
//
//        } catch (final GGHttpClient5Exception e) {
//
//            HttpClientUtils.closeQuietly(response);
//
//            throw e;
//
//        } catch (final RuntimeException | IOException ex) {
//
//            LOG.warn(ex, "executeWithRedirectChecks: Request execution for [%s] failed on I/O : %s", request.getURI(), GGExceptions.createMessageStack(ex));
//
//            HttpClientUtils.closeQuietly(response);
//
//            throw new GGHttpClient5Exception(ex);
//        }
//
//    }
//
//    private HttpUriRequest checkForAndProcessRedirect(//
//                                                      final HttpUriRequest localRequest, //
//                                                      final CloseableHttpResponse response, //
//                                                      final HttpClientContext httpContext) {
//
//        if (!processRedirects) {
//            return null;
//        }
//
//        if (httpContext.getAttribute("IGNORE_REDIRECTS") != null) {
//            return null;
//        }
//
//        final int statusCode = response.getCode();
//
//        switch (statusCode) {
//
//            case HttpStatus.SC_MOVED_PERMANENTLY:
//            case HttpStatus.SC_MOVED_TEMPORARILY:
//            case HttpStatus.SC_TEMPORARY_REDIRECT:
//
//                LOG.trace("checkForAndProcessRedirect: Redirect status code received: %d", statusCode);
//
//                final String redirectLocation = getFirstHeaderValue(response, "Location");
//
//                LOG.trace("checkForAndProcessRedirect: redirectLocation: %s", redirectLocation);
//
//                if (GGStrings.isNotBlank(redirectLocation)) {
//
//                    final URI redirectUri = createRedirectUri(redirectLocation, localRequest);
//
//                    LOG.trace("checkForAndProcessRedirect: Redirecting to: %s", redirectUri);
//
//                    httpContext.getRedirectLocations().add(redirectUri);
//
//                    return new HttpGet(redirectUri);
//
//                }
//
//                throw new GGHttpClientResponseException("Received redirect status with no location.", statusCode, GGHttpResponses.getStringContent(response));
//
//        }
//
//        return null;
//    }
//
//    private URI createRedirectUri(final String redirectLocation, final HttpUriRequest request) {
//
//        final URI base = request.getURI();
//
//        return URIUtils.resolve(base, redirectLocation);
//
//    }

    private PoolingHttpClientConnectionManager createConnectionManager() {

        final ConnectionConfig connectionConfig = ConnectionConfig
                .custom()
                .setConnectTimeout(connectTimeout)
                .setSocketTimeout(connectSoTimeout)
                .build();

//        final SocketConfig sc = SocketConfig.custom(). //
//                setSoTimeout(SOCKET_SO_TIMEOUT). //
//                build();

        final PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setDefaultConnectionConfig(connectionConfig);
        //connectionManager.setDefaultSocketConfig(sc);
        connectionManager.setMaxTotal(200);
        connectionManager.setDefaultMaxPerRoute(100);

        return connectionManager;
    }

    private CloseableHttpClient createHttpClient(final int retryCount) {


        final RequestConfig rc = RequestConfig
                .custom()
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setCircularRedirectsAllowed(true).
                build();

        return HttpClients
                .custom()
                .setDefaultRequestConfig(rc)
                .setConnectionManager(connectionManager).
                disableRedirectHandling().
                build();

        // setRetryHandler(new CustomHttpRequestRetryHandler(retryCount)).
    }

//    public static class CustomHttpRequestRetryHandler implements HttpRequestRetryStrategy {
//
//        private final int retryCount;
//
//        public CustomHttpRequestRetryHandler(final int retryCount) {
//            this.retryCount = retryCount;
//        }
//
//        @Override
//        public boolean retryRequest(final IOException exception, final int executionCount, final HttpContext context) {
//
//            return executionCount < retryCount;
//
//        }
//
//    }

}
