package com.gantzgulch.tools.httpclient;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.protocol.HttpClientContext;

public interface GGHttpClient {

    CloseableHttpResponse doGet(URI uri, Map<String,String> headers, HttpClientContext httpClientContext) throws IOException;
    
    CloseableHttpResponse doHead(URI uri, Map<String,String> headers, HttpClientContext httpClientContext) throws IOException;
    
    CloseableHttpResponse doDelete(URI uri, Map<String,String> headers, HttpClientContext httpClientContext) throws IOException;
    
    CloseableHttpResponse doPost(URI uri, Map<String, String> parameters, Map<String,String> headers, HttpClientContext clientContext) throws IOException;

    CloseableHttpResponse doPost(URI uri, String content, Map<String,String> headers, HttpClientContext clientContext) throws IOException;

    CloseableHttpResponse doPut(URI uri, Map<String, String> parameters, Map<String,String> headers, HttpClientContext clientContext) throws IOException;

    CloseableHttpResponse doPut(URI uri, String content, Map<String,String> headers, HttpClientContext clientContext) throws IOException;

    void close();

}
