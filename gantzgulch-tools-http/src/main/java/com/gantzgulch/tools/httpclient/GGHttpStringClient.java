package com.gantzgulch.tools.httpclient;

import java.net.URI;
import java.util.Map;

import org.apache.http.client.protocol.HttpClientContext;

public interface GGHttpStringClient {

    String get(URI uri, Map<String,String> headers, HttpClientContext httpClientContext);
    
    String delete(URI uri, Map<String,String> headers, HttpClientContext httpClientContext);
    
    String post(URI uri, String content, Map<String,String> headers, HttpClientContext clientContext);

    String post(URI uri, Map<String,String> content, Map<String,String> headers, HttpClientContext clientContext);

    String put(URI uri, String content, Map<String,String> headers, HttpClientContext clientContext);

    String put(URI uri, Map<String,String> content, Map<String,String> headers, HttpClientContext clientContext);

}
