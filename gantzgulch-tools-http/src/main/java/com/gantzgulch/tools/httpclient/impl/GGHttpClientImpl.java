package com.gantzgulch.tools.httpclient.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
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
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import com.gantzgulch.tools.common.lang.GGExceptions;
import com.gantzgulch.tools.common.lang.GGStrings;
import com.gantzgulch.tools.common.lang.GGUtf8;
import com.gantzgulch.tools.common.logging.GGLogger;
import com.gantzgulch.tools.httpclient.GGHttpClient;
import com.gantzgulch.tools.httpclient.GGHttpClientStats;
import com.gantzgulch.tools.httpclient.GGHttpJsonClient;
import com.gantzgulch.tools.httpclient.GGHttpStringClient;
import com.gantzgulch.tools.httpclient.exception.GGHttpClientException;
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

        this.agent = agent;
        this.processRedirects = processRedirects;
        this.connectionRequestTimeout = DEFAULT_CONNECTION_REQUEST_TIMEOUT_IN_MILLIS;
        this.connectionTimeout = connectionTimeoutMillis;
        this.socketReadTimeout = socketReadTimeoutMillis;

        this.connectionManager = createConnectionManager();
        this.httpClient = createHttpClient();
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

        LOG.trace("doGet: %s", uri);

        final HttpGet request = new HttpGet(uri);

        GGHttpRequests.setHeaders(request, headers);

        return execute(request, httpClientContext);
    }

    @Override
    public CloseableHttpResponse head(//
            final URI uri, //
            final Map<String, String> headers, //
            final HttpClientContext httpClientContext) {

        LOG.trace("doHead: %s", uri);

        final HttpHead request = new HttpHead(uri);

        GGHttpRequests.setHeaders(request, headers);

        return execute(request, httpClientContext);
    }

    @Override
    public CloseableHttpResponse delete(//
            final URI uri, //
            final Map<String, String> headers, //
            final HttpClientContext httpClientContext) {

        LOG.trace("doDelete: %s", uri);

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

        LOG.trace("doPost: %s", uri);

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

        LOG.trace("doPost: called:");

        final HttpPost request = new HttpPost(uri);

        GGHttpRequests.setHeaders(request, headers);

        request.setEntity(new StringEntity(content, GGUtf8.CHARSET));

        return execute(request, clientContext);
    }

    @Override
    public CloseableHttpResponse put(//
            final URI uri, //
            final Map<String, String> parameters, //
            final Map<String, String> headers, //
            final HttpClientContext clientContext) {

        LOG.trace("doPost: %s", uri);

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

        LOG.trace("doPut: %s", uri);

        final HttpPut request = new HttpPut(uri);

        GGHttpRequests.setHeaders(request, headers);

        request.setEntity(new StringEntity(content, GGUtf8.CHARSET));

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

    private CloseableHttpResponse execute(//
            final HttpUriRequest request, //
            final HttpClientContext httpContext) {

        if( processRedirects ){
            return executeWithRedirects(request, httpContext);
        }
        
        return executeNoRedirects(request, httpContext);

    }

    private CloseableHttpResponse executeNoRedirects(//
            final HttpUriRequest request, //
            final HttpClientContext httpContext) {

        CloseableHttpResponse response = null;

        try {

            LOG.trace("execute: Executing request : %s", request.getRequestLine());

            for (final Header h : request.getAllHeaders()) {
                LOG.trace("execute: header: %s:%s", h.getName(), h.getValue());
            }

            if (GGStrings.isNotBlank(agent)) {
                request.setHeader(HttpHeaders.USER_AGENT, agent);
            }

            response = httpClient.execute(request, httpContext);

            return response;

        } catch (final RuntimeException | IOException ex) {

            GGHttpResponses.consume(response);

            LOG.warn("execute: Request execution for [%s] failed on I/O : %s", request.getURI(), GGExceptions.createMessageStack(ex));

            throw new GGHttpClientException(ex);
        }

    }

    private CloseableHttpResponse executeWithRedirects(//
            final HttpUriRequest request2, //
            final HttpClientContext httpContext) {

        HttpUriRequest localRequest = request2;
        CloseableHttpResponse response = null;

        String redirectLocation = null;

        httpContext.setAttribute(HttpClientContext.REDIRECT_LOCATIONS, new ArrayList<String>());

        try {

            for (int redirectCount = 0; redirectCount < 100; redirectCount++) {

                LOG.trace("execute: Executing request : %s", localRequest.getRequestLine());

                if (GGStrings.isNotBlank(agent)) {
                    localRequest.setHeader(HttpHeaders.USER_AGENT, agent);
                }

                response = httpClient.execute(localRequest, httpContext);

                final HttpUriRequest newRequest = checkForAndProcessRedirect(localRequest, response, httpContext);

                if (newRequest == null) {
                    return response;
                } else {
                    HttpClientUtils.closeQuietly(response);
                    localRequest = newRequest;
                    continue;
                }
            }

            throw new IOException("execute: Redirect count exceeded.");

        } catch (final RuntimeException | IOException ex) {
            LOG.warn("execute: Request execution for [%s] failed on I/O : %s", localRequest.getURI(), GGExceptions.createMessageStack(ex));
            GGHttpResponses.consume(response);
            throw new GGHttpClientException(ex);
        } catch (final URISyntaxException e) {
            LOG.warn("execute: Redirect URI from [%s] was not valid: %s", localRequest.getURI(), redirectLocation);
            GGHttpResponses.consume(response);
            throw new GGHttpClientException("execute: Redirect URI was not valid: " + redirectLocation, e);
        }

    }
    
    private HttpUriRequest checkForAndProcessRedirect(//
            final HttpUriRequest localRequest, //
            final CloseableHttpResponse response, //
            final HttpClientContext httpContext) throws URISyntaxException, IOException {

        final int statusCode = GGHttpResponses.getStatusCode(response);

        String redirectLocation = null;

        switch (statusCode) {

        case HttpServletResponse.SC_MOVED_PERMANENTLY:
        case HttpServletResponse.SC_MOVED_TEMPORARILY:
        case HttpServletResponse.SC_TEMPORARY_REDIRECT:

            LOG.trace("checkForAndProcessRedirect: Redirect status code received: %d", GGHttpResponses.getStatusCode(response));

            redirectLocation = GGHttpResponses.getFirstHeaderValue(response, "Location");

            LOG.trace("checkForAndProcessRedirect: redirectLocation: %s", redirectLocation);

            if (GGStrings.isNotBlank(redirectLocation)) {

                final URI redirectUri = createRedirectUri(redirectLocation, localRequest);
                LOG.trace("checkForAndProcessRedirect: Redirecting to: %s", redirectUri);

                httpContext.getRedirectLocations().add(redirectUri);

                return new HttpGet(redirectUri);

            }

            throw new IOException("checkForAndProcessRedirect: Received redirect status code, but no location provided.");

        }

        return null;
    }

    private URI createRedirectUri(final String redirectLocation, final HttpUriRequest request) throws URISyntaxException {

        URI uri = new URI(redirectLocation);

        if (!uri.isAbsolute()) {

            final StringBuilder urlBuilder = new StringBuilder();

            URI reqURI = request.getURI();

            urlBuilder.append(reqURI.getScheme());
            urlBuilder.append("://");
            urlBuilder.append(reqURI.getHost());

            if (!GGStrings.startsWith(redirectLocation, "/")) {
                urlBuilder.append("/");
            }

            urlBuilder.append(redirectLocation);

            uri = new URI(urlBuilder.toString());

        }

        return uri;
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

    private CloseableHttpClient createHttpClient() {

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
                build();

        return httpClient;
    }

}
