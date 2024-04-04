package com.gantzgulch.tools.httpclient5;

import com.gantzgulch.tools.httpclient5.impl.GGHttpClient5CredentialsImpl;
import org.apache.hc.client5.http.ContextBuilder;
import org.apache.hc.client5.http.entity.mime.ContentBody;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.protocol.HttpClientContext;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;

import java.io.Closeable;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.List;
import java.util.Map;

public interface GGHttpClient5 extends Closeable {

    GGHttpClient5Credentials createCredentials(
            URI uri,
            GGHttpClient5AuthType authType,
            String username,
            String password );

    GGHttpClient5Credentials createCredentials(
            String scheme,
            String host,
            int port,
            GGHttpClient5AuthType authType,
            String username,
            String password );

    HttpClientContext buildClientContext(List<GGHttpClient5Credentials> credentialList);

    HttpClientResponseHandler<String> stringHandler();
    HttpClientResponseHandler<Long> streamHandler(OutputStream os);

    ContentBody toContentBody(String content, final ContentType contentType);
    ContentBody toContentBody(File content, ContentType contentType, String filename);
    ContentBody toContentBody(InputStream inputStream, ContentType contentType, String filename);

    HttpEntity toEntity(File content, ContentType contentType);
    HttpEntity toEntity(String content, ContentType contentType);

    <T> GGHttpClient5Response<T> get(URI uri, Map<String, String> headers, HttpClientContext httpClientContext, HttpClientResponseHandler<T> handler);

    <T> GGHttpClient5Response<T> head(URI uri, Map<String,String> headers, HttpClientContext httpClientContext, HttpClientResponseHandler<T> handler);

    <T> GGHttpClient5Response<T> post(URI uri, Map<String, ContentBody> content, Map<String,String> headers, HttpClientContext clientContext, HttpClientResponseHandler<T> handler);

    <T> GGHttpClient5Response<T> post(URI uri, HttpEntity entity, Map<String,String> headers, HttpClientContext clientContext, HttpClientResponseHandler<T> handler);

    <T> GGHttpClient5Response<T> put(URI uri, Map<String, ContentBody> content, Map<String,String> headers, HttpClientContext clientContext, HttpClientResponseHandler<T> handler);

    <T> GGHttpClient5Response<T> put(URI uri, HttpEntity entity, Map<String,String> headers, HttpClientContext clientContext, HttpClientResponseHandler<T> handler);

    void close();

    PoolingHttpClientConnectionManager getConnectionManager();
}
