package com.gantzgulch.tools.httpclient.impl;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
import com.gantzgulch.tools.common.logging.GGLogger;
import com.gantzgulch.tools.httpclient.GGHttpClient;

public class GGHttpClientImpl implements GGHttpClient {

    public static final int DEFAULT_CONNECTION_REQUEST_TIMEOUT_IN_MILLIS = (int) TimeUnit.MILLISECONDS.convert(60, TimeUnit.SECONDS);
    public static final int DEFAULT_CONNECT_TIMEOUT_IN_MILLIS = (int) TimeUnit.MILLISECONDS.convert(15, TimeUnit.SECONDS);
    public static final int DEFAULT_SOCKET_READ_TIMEOUT_IN_MILLIS = (int) TimeUnit.MILLISECONDS.convert(15, TimeUnit.SECONDS);

    private static final GGLogger LOG = GGLogger.getLogger(GGHttpClientImpl.class);

    private final String agent;

    private final int connectionRequestTimeout;
    private final int connectionTimeout;
    private final int socketReadTimeout;

    private final PoolingHttpClientConnectionManager connectionManager;

    private final CloseableHttpClient httpClient;

    public GGHttpClientImpl(final String agent, final int connectionTimeout, final int socketReadTimeout) {

        this.agent = agent;
        this.connectionRequestTimeout = DEFAULT_CONNECTION_REQUEST_TIMEOUT_IN_MILLIS;
        this.connectionTimeout = connectionTimeout;
        this.socketReadTimeout = socketReadTimeout;

        this.connectionManager = createConnectionManager();
        this.httpClient = createHttpClient();
    }

    @Override
    public CloseableHttpResponse doGet(//
            final URI uri, //
            final Map<String, String> headers, //
            final HttpClientContext httpClientContext) throws IOException {

        LOG.trace("doGet: %s", uri);
        
        final HttpGet request = new HttpGet(uri);

        GGHttpRequests.setHeaders(request, headers);

        return execute(request, httpClientContext);
    }

    @Override
    public CloseableHttpResponse doHead(//
            final URI uri, //
            final Map<String, String> headers, //
            final HttpClientContext httpClientContext) throws IOException {

        LOG.trace("doHead: %s", uri);
        
        final HttpHead request = new HttpHead(uri);

        GGHttpRequests.setHeaders(request, headers);

        return execute(request, httpClientContext);
    }

    @Override
    public CloseableHttpResponse doDelete(//
            final URI uri, //
            final Map<String, String> headers, //
            final HttpClientContext httpClientContext) throws IOException {

        LOG.trace("doDelete: %s", uri);
        
        final HttpDelete request = new HttpDelete(uri);

        GGHttpRequests.setHeaders(request, headers);

        return execute(request, httpClientContext);

    }

    @Override
    public CloseableHttpResponse doPost(//
            final URI uri, //
            final Map<String, String> parameters, //
            final Map<String, String> headers, //
            final HttpClientContext clientContext) throws IOException {

        LOG.trace("doPost: %s", uri);
        
        final HttpPost request = new HttpPost(uri);

        GGHttpRequests.setHeaders(request, headers);

        final List<NameValuePair> params = GGHttpUtils.toListNameValuePair(parameters);

        request.setEntity(new StringEntity(URLEncodedUtils.format(params, "UTF-8")));

        return execute(request, clientContext);
    }

    @Override
    public CloseableHttpResponse doPost(//
            final URI uri, //
            final String content, //
            final Map<String, String> headers, //
            final HttpClientContext clientContext) throws IOException {

        LOG.trace("doPost: called:");

        final HttpPost request = new HttpPost(uri);

        GGHttpRequests.setHeaders(request, headers);

        request.setEntity(new StringEntity(content));

        return execute(request, clientContext);
    }

    @Override
    public CloseableHttpResponse doPut(//
            final URI uri, //
            final Map<String, String> parameters, //
            final Map<String, String> headers, //
            final HttpClientContext clientContext) throws IOException {

        LOG.trace("doPost: %s", uri);
        
        final HttpPut request = new HttpPut(uri);

        GGHttpRequests.setHeaders(request, headers);

        final List<NameValuePair> params = GGHttpUtils.toListNameValuePair(parameters);

        request.setEntity(new StringEntity(URLEncodedUtils.format(params, "UTF-8")));

        return execute(request, clientContext);
    }

    @Override
    public CloseableHttpResponse doPut(//
            final URI uri, //
            final String content, //
            final Map<String, String> headers, //
            final HttpClientContext clientContext) throws IOException {

        LOG.trace("doPut: %s", uri);
        
        final HttpPut request = new HttpPut(uri);

        GGHttpRequests.setHeaders(request, headers);

        request.setEntity(new StringEntity(content));

        return execute(request, clientContext);
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
            final HttpClientContext httpContext) throws IOException {

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
            
            throw ex;
        }

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
