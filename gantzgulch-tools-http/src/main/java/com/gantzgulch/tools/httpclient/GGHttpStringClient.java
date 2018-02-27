package com.gantzgulch.tools.httpclient;

import java.net.URI;
import java.util.Map;

import org.apache.http.client.protocol.HttpClientContext;

import com.gantzgulch.tools.httpclient.impl.GGHttpStringClientImpl;

public interface GGHttpStringClient {

    public static GGHttpStringClient create() {
        return new GGHttpStringClientImpl();
    }
    
    String get(GGHttpClient httpClient, URI uri, Map<String,String> headers, HttpClientContext httpClientContext);
    
    String delete(GGHttpClient httpClient, URI uri, Map<String,String> headers, HttpClientContext httpClientContext);
    
    String post(GGHttpClient httpClient, URI uri, String content, Map<String,String> headers, HttpClientContext clientContext);

    String post(GGHttpClient httpClient, URI uri, Map<String,String> content, Map<String,String> headers, HttpClientContext clientContext);

    String put(GGHttpClient httpClient, URI uri, String content, Map<String,String> headers, HttpClientContext clientContext);

    String put(GGHttpClient httpClient, URI uri, Map<String,String> content, Map<String,String> headers, HttpClientContext clientContext);

}
