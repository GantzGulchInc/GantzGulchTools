package com.gantzgulch.tools.httpclient;

import java.net.URI;
import java.util.Map;

import org.apache.http.client.protocol.HttpClientContext;

import com.gantzgulch.tools.httpclient.impl.GGHttpJsonClientImpl;
import com.gantzgulch.tools.json.GGJsonReader;
import com.gantzgulch.tools.json.GGJsonWriter;

public interface GGHttpJsonClient {

    public static GGHttpJsonClient create(final GGJsonReader jsonReader, final GGJsonWriter jsonWriter) {
        return new GGHttpJsonClientImpl(jsonReader, jsonWriter);
    }
    
    <T> T get(GGHttpClient httpClient, URI uri, Map<String,String> headers, HttpClientContext httpClientContext, Class<T> objectClass);
    
    <T> T delete(GGHttpClient httpClient, URI uri, Map<String,String> headers, HttpClientContext httpClientContext, Class<T> objectClass);
    
    <T> T post(GGHttpClient httpClient, URI uri, Object content, Map<String,String> headers, HttpClientContext clientContext, Class<T> objectClass);

    <T> T put(GGHttpClient httpClient, URI uri, Object content, Map<String,String> headers, HttpClientContext clientContext, Class<T> objectClass);

}
