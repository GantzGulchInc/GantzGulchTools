package com.gantzgulch.tools.httpclient;

import java.net.URI;
import java.util.Map;

import org.apache.http.client.protocol.HttpClientContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;

public interface GGHttpJsonClient {

    <T> T get(URI uri, Map<String,String> headers, HttpClientContext httpClientContext, Class<T> objectClass);
    
    <T> T get(URI uri, Map<String,String> headers, HttpClientContext httpClientContext, TypeReference<T> typeRef);
    
    <T> T get(URI uri, Map<String,String> headers, HttpClientContext httpClientContext, JavaType javaType);
    
    <T> T delete(URI uri, Map<String,String> headers, HttpClientContext httpClientContext, Class<T> objectClass);
    
    <T> T delete(URI uri, Map<String,String> headers, HttpClientContext httpClientContext, TypeReference<T> typeRef);
    
    <T> T delete(URI uri, Map<String,String> headers, HttpClientContext httpClientContext, JavaType javaType);
    
    <T> T post(URI uri, Object content, Map<String,String> headers, HttpClientContext clientContext, Class<T> objectClass);

    <T> T post(URI uri, Object content, Map<String,String> headers, HttpClientContext clientContext, TypeReference<T> typeRef);

    <T> T post(URI uri, Object content, Map<String,String> headers, HttpClientContext clientContext, JavaType javaType);

    <T> T put(URI uri, Object content, Map<String,String> headers, HttpClientContext clientContext, Class<T> objectClass);

    <T> T put(URI uri, Object content, Map<String,String> headers, HttpClientContext clientContext, TypeReference<T> typeRef);

    <T> T put(URI uri, Object content, Map<String,String> headers, HttpClientContext clientContext, JavaType javaType);

}
