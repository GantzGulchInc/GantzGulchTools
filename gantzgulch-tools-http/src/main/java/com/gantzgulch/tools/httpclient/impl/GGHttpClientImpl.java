package com.gantzgulch.tools.httpclient.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;

import com.gantzgulch.tools.common.lang.GGExceptions;
import com.gantzgulch.tools.common.lang.GGStrings;
import com.gantzgulch.tools.common.lang.GGUtf8;
import com.gantzgulch.tools.common.logging.GGLogger;
import com.gantzgulch.tools.httpclient.GGHttpClient;
import com.gantzgulch.tools.httpclient.GGHttpClientStats;
import com.gantzgulch.tools.httpclient.GGHttpJsonClient;
import com.gantzgulch.tools.httpclient.GGHttpStringClient;
import com.gantzgulch.tools.httpclient.exception.GGHttpClientException;
import com.gantzgulch.tools.httpclient.exception.GGHttpClientResponseException;
import com.gantzgulch.tools.json.GGJsonReader;
import com.gantzgulch.tools.json.GGJsonWriter;

public class GGHttpClientImpl implements GGHttpClient {

    public static final int DEFAULT_CONNECTION_REQUEST_TIMEOUT_IN_MILLIS = (int) TimeUnit.MILLISECONDS.convert(60, TimeUnit.SECONDS);
    public static final int DEFAULT_CONNECT_TIMEOUT_IN_MILLIS = (int) TimeUnit.MILLISECONDS.convert(15, TimeUnit.SECONDS);
    public static final int DEFAULT_SOCKET_READ_TIMEOUT_IN_MILLIS = (int) TimeUnit.MILLISECONDS.convert(15, TimeUnit.SECONDS);

    private static final GGLogger LOG = GGLogger.getLogger(GGHttpClientImpl.class);

    private final String agent;
    private final boolean processRedirects;

    private final int connectionRequestTimeout;
    private final int connectionTimeout;
    private final int socketReadTimeout;

    private final PoolingHttpClientConnectionManager connectionManager;

    private final CloseableHttpClient httpClient;

    public GGHttpClientImpl(final String agent, final boolean processRedirects, final int connectionTimeoutMillis, final int socketReadTimeoutMillis) {

        this(agent, processRedirects, connectionTimeoutMillis, socketReadTimeoutMillis, 3);

    }

    public GGHttpClientImpl(//
            final String agent, //
            final boolean processRedirects, //
            final int connectionTimeoutMillis, //
            final int socketReadTimeoutMillis, //
            final int retryCount) {

        this.agent = agent;
        this.processRedirects = processRedirects;
        this.connectionRequestTimeout = DEFAULT_CONNECTION_REQUEST_TIMEOUT_IN_MILLIS;
        this.connectionTimeout = connectionTimeoutMillis;
        this.socketReadTimeout = socketReadTimeoutMillis;

        this.connectionManager = createConnectionManager();

        this.httpClient = createHttpClient(retryCount);
    }

    @Override
    public GGHttpJsonClient jsonClient(final GGJsonReader jsonReader, final GGJsonWriter jsonWriter) {
        return new GGHttpJsonClientImpl(this, jsonReader, jsonWriter);
    }

    @Override
    public GGHttpStringClient stringClient() {
        return new GGHttpStringClientImpl(this);
    }

    @Override
    public CloseableHttpResponse get(//
            final URI uri, //
            final Map<String, String> headers, //
            final HttpClientContext httpClientContext) {

        LOG.trace("get: %s", uri);

        final HttpGet request = new HttpGet(uri);

        GGHttpRequests.setHeaders(request, headers);

        return execute(request, httpClientContext);
    }

    @Override
    public CloseableHttpResponse head(//
            final URI uri, //
            final Map<String, String> headers, //
            final HttpClientContext httpClientContext) {

        LOG.trace("head: %s", uri);

        final HttpHead request = new HttpHead(uri);

        GGHttpRequests.setHeaders(request, headers);

        return execute(request, httpClientContext);
    }

    @Override
    public CloseableHttpResponse delete(//
            final URI uri, //
            final Map<String, String> headers, //
            final HttpClientContext httpClientContext) {

        LOG.trace("delete: %s", uri);

        final HttpDelete request = new HttpDelete(uri);

        GGHttpRequests.setHeaders(request, headers);

        return execute(request, httpClientContext);

    }

    @Override
    public CloseableHttpResponse post(//
            final URI uri, //
            final Map<String, String> parameters, //
            final Map<String, String> headers, //
            final HttpClientContext clientContext) {

        LOG.trace("post: %s", uri);

        final HttpPost request = new HttpPost(uri);

        GGHttpRequests.setHeaders(request, headers);

        final List<NameValuePair> params = GGHttpUtils.toNameValuePairList(parameters);

        request.setEntity(new StringEntity(URLEncodedUtils.format(params, "UTF-8"), GGUtf8.CHARSET));

        return execute(request, clientContext);
    }

    @Override
    public CloseableHttpResponse post(//
            final URI uri, //
            final String content, //
            final Map<String, String> headers, //
            final HttpClientContext clientContext) {

        LOG.trace("post: %s:", uri);

        final HttpPost request = new HttpPost(uri);

        GGHttpRequests.setHeaders(request, headers);

        if (content != null) {

            final StringEntity entity = new StringEntity(content, GGUtf8.CHARSET);

            request.setEntity(entity);
        }

        return execute(request, clientContext);
    }

    @Override
    public CloseableHttpResponse post(//
            final URI uri, //
            final InputStream content, //
            final Map<String, String> headers, //
            final HttpClientContext clientContext) {

        LOG.trace("post: %s:", uri);

        final HttpPost request = new HttpPost(uri);

        GGHttpRequests.setHeaders(request, headers);

        if (content != null) {

            final InputStreamEntity entity = new InputStreamEntity(content);

            request.setEntity(entity);
        }

        return execute(request, clientContext);
    }

    @Override
    public CloseableHttpResponse put(//
            final URI uri, //
            final Map<String, String> parameters, //
            final Map<String, String> headers, //
            final HttpClientContext clientContext) {

        LOG.trace("put: %s", uri);

        final HttpPut request = new HttpPut(uri);

        GGHttpRequests.setHeaders(request, headers);

        final List<NameValuePair> params = GGHttpUtils.toNameValuePairList(parameters);

        request.setEntity(new StringEntity(URLEncodedUtils.format(params, "UTF-8"), GGUtf8.CHARSET));

        return execute(request, clientContext);
    }

    @Override
    public CloseableHttpResponse put(//
            final URI uri, //
            final String content, //
            final Map<String, String> headers, //
            final HttpClientContext clientContext) {

        LOG.trace("put: %s", uri);

        final HttpPut request = new HttpPut(uri);

        GGHttpRequests.setHeaders(request, headers);

        if (content != null) {

            final StringEntity entity = new StringEntity(content, GGUtf8.CHARSET);

            request.setEntity(entity);
        }

        return execute(request, clientContext);
    }

    @Override
    public GGHttpClientStats getStats() {
        return new GGHttpClientStats(connectionManager);
    }

    @Override
    public void close() {

        LOG.trace("Shutting down http client.");

        try {
            HttpClientUtils.closeQuietly(httpClient);
        } catch (final Exception e) {
            LOG.warn(e, "close: Error closing http client.");
        }
    }

    @Override
    public PoolingHttpClientConnectionManager getConnectionManager() {
        return connectionManager;
    }

    private CloseableHttpResponse execute(//
            final HttpUriRequest request, //
            final HttpClientContext httpContext) {

        httpContext.setAttribute(HttpClientContext.REDIRECT_LOCATIONS, new ArrayList<String>());

        return executeWithRedirectChecks(request, httpContext);

    }

    private CloseableHttpResponse executeWithRedirectChecks(//
            final HttpUriRequest request, //
            final HttpClientContext httpContext) {

        if (httpContext.getRedirectLocations().size() > 100) {
            throw new GGHttpClientException("Redirect count exceeded.");
        }

        CloseableHttpResponse response = null;

        try {

            LOG.trace("execute: Executing request : %s", request.getRequestLine());

            if (GGStrings.isNotBlank(agent)) {
                request.setHeader(HttpHeaders.USER_AGENT, agent);
            }

            response = httpClient.execute(request, httpContext);

            final HttpUriRequest newRequest = checkForAndProcessRedirect(request, response, httpContext);

            if (newRequest != null) {

                HttpClientUtils.closeQuietly(response);

                return executeWithRedirectChecks(newRequest, httpContext);
            }

            return response;

        } catch (final GGHttpClientException e) {

            HttpClientUtils.closeQuietly(response);

            throw e;

        } catch (final RuntimeException | IOException ex) {

            LOG.warn(ex, "executeWithRedirectChecks: Request execution for [%s] failed on I/O : %s", request.getURI(), GGExceptions.createMessageStack(ex));

            HttpClientUtils.closeQuietly(response);

            throw new GGHttpClientException(ex);
        }

    }

    private HttpUriRequest checkForAndProcessRedirect(//
            final HttpUriRequest localRequest, //
            final CloseableHttpResponse response, //
            final HttpClientContext httpContext) {

        if (!processRedirects) {
            return null;
        }

        if (httpContext.getAttribute("IGNORE_REDIRECTS") != null) {
            return null;
        }

        final int statusCode = GGHttpResponses.getStatusCode(response);

        switch (statusCode) {

        case HttpServletResponse.SC_MOVED_PERMANENTLY:
        case HttpServletResponse.SC_MOVED_TEMPORARILY:
        case HttpServletResponse.SC_TEMPORARY_REDIRECT:

            LOG.trace("checkForAndProcessRedirect: Redirect status code received: %d", statusCode);

            final String redirectLocation = GGHttpResponses.getFirstHeaderValue(response, "Location");

            LOG.trace("checkForAndProcessRedirect: redirectLocation: %s", redirectLocation);

            if (GGStrings.isNotBlank(redirectLocation)) {

                final URI redirectUri = createRedirectUri(redirectLocation, localRequest);

                LOG.trace("checkForAndProcessRedirect: Redirecting to: %s", redirectUri);

                httpContext.getRedirectLocations().add(redirectUri);

                return new HttpGet(redirectUri);

            }

            throw new GGHttpClientResponseException("Received redirect status with no location.", statusCode, GGHttpResponses.getStringContent(response));

        }

        return null;
    }

    private URI createRedirectUri(final String redirectLocation, final HttpUriRequest request) {

        final URI base = request.getURI();

        return URIUtils.resolve(base, redirectLocation);

    }

    private PoolingHttpClientConnectionManager createConnectionManager() {

        final SocketConfig sc = SocketConfig.custom(). //
                setSoTimeout(socketReadTimeout). //
                build();

        final PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setDefaultSocketConfig(sc);
        connectionManager.setMaxTotal(200);
        connectionManager.setDefaultMaxPerRoute(100);

        return connectionManager;
    }

    private CloseableHttpClient createHttpClient(final int retryCount) {

        final RequestConfig rc = RequestConfig.custom(). //
                setConnectionRequestTimeout(connectionRequestTimeout). //
                setConnectTimeout(connectionTimeout). //
                setSocketTimeout(socketReadTimeout). //
                setCircularRedirectsAllowed(true). //
                build();

        final CloseableHttpClient httpClient = HttpClients.custom(). //
                setDefaultRequestConfig(rc). //
                setConnectionManager(connectionManager). //
                disableRedirectHandling(). //
                setRetryHandler(new CustomHttpRequestRetryHandler(retryCount)). //
                build();

        return httpClient;
    }

    public static class CustomHttpRequestRetryHandler implements HttpRequestRetryHandler {

        private final int retryCount;

        public CustomHttpRequestRetryHandler(final int retryCount) {
            this.retryCount = retryCount;
        }

        @Override
        public boolean retryRequest(final IOException exception, final int executionCount, final HttpContext context) {

            return executionCount < retryCount;

        }

    }

}
