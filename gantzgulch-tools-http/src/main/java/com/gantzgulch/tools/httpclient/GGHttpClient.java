package com.gantzgulch.tools.httpclient;

import java.io.Closeable;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import com.gantzgulch.tools.json.GGJsonReader;
import com.gantzgulch.tools.json.GGJsonWriter;

public interface GGHttpClient extends Closeable {

    CloseableHttpResponse get(URI uri, Map<String,String> headers, HttpClientContext httpClientContext);

    CloseableHttpResponse get(URI uri, Map<String,String> headers, HttpClientContext httpClientContext, String username, String password);

    CloseableHttpResponse head(URI uri, Map<String,String> headers, HttpClientContext httpClientContext);
    
    CloseableHttpResponse delete(URI uri, Map<String,String> headers, HttpClientContext httpClientContext);
    
    CloseableHttpResponse post(URI uri, Map<String, String> parameters, Map<String,String> headers, HttpClientContext clientContext);

    CloseableHttpResponse post(URI uri, String content, Map<String,String> headers, HttpClientContext clientContext);

    CloseableHttpResponse post(URI uri, InputStream content, Map<String,String> headers, HttpClientContext clientContext);
    
    CloseableHttpResponse postMultiPart(URI uri, Map<String,ContentBody> content, Map<String,String> headers, HttpClientContext clientContext);

    CloseableHttpResponse put(URI uri, Map<String, String> parameters, Map<String,String> headers, HttpClientContext clientContext);

    CloseableHttpResponse put(URI uri, String content, Map<String,String> headers, HttpClientContext clientContext);

    void close();
    
    GGHttpClientStats getStats();
    
    GGHttpJsonClient jsonClient(GGJsonReader reader, GGJsonWriter writer);
    
    GGHttpStringClient stringClient();

    PoolingHttpClientConnectionManager getConnectionManager();
}
