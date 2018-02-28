package com.gantzgulch.tools.httpclient.impl;

import java.net.URI;
import java.util.Map;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.protocol.HttpClientContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.gantzgulch.tools.common.lang.GGCloseables;
import com.gantzgulch.tools.httpclient.GGHttpClient;
import com.gantzgulch.tools.httpclient.GGHttpJsonClient;
import com.gantzgulch.tools.json.GGJsonReader;
import com.gantzgulch.tools.json.GGJsonWriter;

public class GGHttpJsonClientImpl extends AbstractGGHttpSpecialClient implements GGHttpJsonClient {

    private final GGJsonReader jsonReader;
    private final GGJsonWriter jsonWriter;

    public GGHttpJsonClientImpl(final GGHttpClient httpClient, final GGJsonReader jsonReader, final GGJsonWriter jsonWriter) {
        super(httpClient);
        this.jsonReader = jsonReader;
        this.jsonWriter = jsonWriter;
    }

    @Override
    public <T> T get(//
            final URI uri, //
            final Map<String, String> headers, //
            final HttpClientContext httpClientContext, //
            final Class<T> objectClass) {

        return get(uri, headers, httpClientContext, jsonReader.getTypeFactory().constructType(objectClass));
    }

    @Override
    public <T> T get(//
            final URI uri, //
            final Map<String, String> headers, //
            final HttpClientContext httpClientContext, //
            final TypeReference<T> typeRef) {

        return get(uri, headers, httpClientContext, jsonReader.getTypeFactory().constructType(typeRef));

    }

    @Override
    public <T> T get(//
            final URI uri, //
            final Map<String, String> headers, //
            final HttpClientContext httpClientContext, //
            final JavaType javaType) {

        CloseableHttpResponse response = null;

        try {

            Map<String, String> newHeaders = addHeaderIfMissing(headers, HttpHeaders.ACCEPT, "application/json");

            response = httpClient.get(uri, newHeaders, httpClientContext);

            checkResponse(response);

            final String result = GGHttpResponses.getStringContent(response);

            return jsonReader.read(result, javaType);

        } finally {
            GGHttpResponses.consume(response);
            GGCloseables.closeQuietly(response);
        }

    }

    @Override
    public <T> T delete(//
            final URI uri, //
            final Map<String, String> headers, //
            final HttpClientContext httpClientContext, //
            final Class<T> objectClass) {

        return delete(uri, headers, httpClientContext, jsonReader.getTypeFactory().constructType(objectClass));

    }

    @Override
    public <T> T delete( //
            final URI uri, //
            final Map<String, String> headers, //
            final HttpClientContext httpClientContext, //
            final TypeReference<T> typeRef) {

        return delete(uri, headers, httpClientContext, jsonReader.getTypeFactory().constructType(typeRef));

    }

    @Override
    public <T> T delete(//
            final URI uri, //
            final Map<String, String> headers, //
            final HttpClientContext httpClientContext, //
            final JavaType javaType) {

        CloseableHttpResponse response = null;

        try {

            Map<String, String> newHeaders = addHeaderIfMissing(headers, HttpHeaders.ACCEPT, "application/json");

            response = httpClient.delete(uri, newHeaders, httpClientContext);

            checkResponse(response);

            final String result = GGHttpResponses.getStringContent(response);

            return jsonReader.read(result, javaType);

        } finally {
            GGHttpResponses.consume(response);
            GGCloseables.closeQuietly(response);
        }
    }

    @Override
    public <T> T post(//
            final URI uri, //
            final Object content, //
            final Map<String, String> headers, //
            final HttpClientContext httpClientContext, //
            final Class<T> objectClass) {

        return post(uri, content, headers, httpClientContext, jsonReader.getTypeFactory().constructType(objectClass));

    }

    @Override
    public <T> T post(//
            final URI uri, //
            final Object content, //
            final Map<String, String> headers, //
            final HttpClientContext httpClientContext, //
            final TypeReference<T> typeRef) {

        return post(uri, content, headers, httpClientContext, jsonReader.getTypeFactory().constructType(typeRef));

    }

    @Override
    public <T> T post(//
            final URI uri, //
            final Object content, //
            final Map<String, String> headers, //
            final HttpClientContext clientContext, //
            final JavaType javaType) {

        CloseableHttpResponse response = null;

        try {

            Map<String, String> newHeaders = addHeaderIfMissing(headers, HttpHeaders.CONTENT_TYPE, "application/json");

            newHeaders = addHeaderIfMissing(headers, HttpHeaders.ACCEPT, "application/json");

            final String json = jsonWriter.writeAsString(content);

            response = httpClient.post(uri, json, newHeaders, clientContext);

            checkResponse(response);

            final String result = GGHttpResponses.getStringContent(response);

            return jsonReader.read(result, javaType);

        } finally {
            GGHttpResponses.consume(response);
            GGCloseables.closeQuietly(response);
        }
    }

    @Override
    public <T> T put(//
            final URI uri, //
            final Object content, //
            final Map<String, String> headers, //
            final HttpClientContext httpClientContext, //
            final Class<T> objectClass) {

        return put(uri, content, headers, httpClientContext, jsonReader.getTypeFactory().constructType(objectClass));

    }

    @Override
    public <T> T put(//
            final URI uri, //
            final Object content, //
            final Map<String, String> headers, //
            final HttpClientContext httpClientContext, //
            final TypeReference<T> typeRef) {

        return put(uri, content, headers, httpClientContext, jsonReader.getTypeFactory().constructType(typeRef));

    }

    @Override
    public <T> T put(//
            final URI uri, //
            final Object content, //
            final Map<String, String> headers, //
            final HttpClientContext clientContext, //
            final JavaType javaType) {

        CloseableHttpResponse response = null;

        try {

            Map<String, String> newHeaders = addHeaderIfMissing(headers, HttpHeaders.CONTENT_TYPE, "application/json");

            newHeaders = addHeaderIfMissing(headers, HttpHeaders.ACCEPT, "application/json");

            final String json = jsonWriter.writeAsString(content);

            response = httpClient.put(uri, json, newHeaders, clientContext);

            checkResponse(response);

            final String result = GGHttpResponses.getStringContent(response);

            return jsonReader.read(result, javaType);

        } finally {
            GGHttpResponses.consume(response);
            GGCloseables.closeQuietly(response);
        }
    }

}
