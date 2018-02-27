package com.gantzgulch.tools.httpclient.impl;

import java.net.URI;
import java.util.Map;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.protocol.HttpClientContext;

import com.gantzgulch.tools.common.lang.GGCloseables;
import com.gantzgulch.tools.httpclient.GGHttpClient;
import com.gantzgulch.tools.httpclient.GGHttpJsonClient;
import com.gantzgulch.tools.httpclient.exception.GGHttpClientResponseException;
import com.gantzgulch.tools.json.GGJsonReader;
import com.gantzgulch.tools.json.GGJsonWriter;

public class GGHttpJsonClientImpl implements GGHttpJsonClient {

    private final GGJsonReader jsonReader;
    private final GGJsonWriter jsonWriter;

    public GGHttpJsonClientImpl(final GGJsonReader jsonReader, final GGJsonWriter jsonWriter) {
        this.jsonReader = jsonReader;
        this.jsonWriter = jsonWriter;
    }

    @Override
    public <T> T get(//
            final GGHttpClient httpClient, //
            final URI uri, //
            final Map<String, String> headers, //
            final HttpClientContext httpClientContext, //
            final Class<T> objectClass) {

        CloseableHttpResponse response = null;

        try {
            
            if( ! headers.containsKey(HttpHeaders.ACCEPT) ){
                headers.put(HttpHeaders.ACCEPT, "application/json");
            }

            response = httpClient.get(uri, headers, httpClientContext);

            checkResponse(response);
            
            final String result = GGHttpResponses.getStringContent(response);
            
            return jsonReader.read(result, objectClass); 

        } finally {
            GGHttpResponses.consume(response);
            GGCloseables.closeQuietly(response);
        }

    }

    @Override
    public <T> T delete(//
            final GGHttpClient httpClient, //
            final URI uri, //
            final Map<String, String> headers, //
            final HttpClientContext httpClientContext, //
            final Class<T> objectClass) {
        
        CloseableHttpResponse response = null;

        try {
            
            if( ! headers.containsKey(HttpHeaders.ACCEPT) ){
                headers.put(HttpHeaders.ACCEPT, "application/json");
            }

            response = httpClient.delete(uri, headers, httpClientContext);

            checkResponse(response);

            final String result = GGHttpResponses.getStringContent(response);
            
            return jsonReader.read(result, objectClass); 

        } finally {
            GGHttpResponses.consume(response);
            GGCloseables.closeQuietly(response);
        }
    }

    @Override
    public <T> T post(//
            final GGHttpClient httpClient, //
            final URI uri, //
            final Object content, //
            final Map<String, String> headers, //
            final HttpClientContext clientContext, //
            final Class<T> objectClass) {

        CloseableHttpResponse response = null;

        try {
            
            if( ! headers.containsKey(HttpHeaders.CONTENT_TYPE) ){
                headers.put(HttpHeaders.CONTENT_TYPE, "application/json");
            }
            
            if( ! headers.containsKey(HttpHeaders.ACCEPT) ){
                headers.put(HttpHeaders.ACCEPT, "application/json");
            }

            final String json = jsonWriter.writeAsString(content);
            
            response = httpClient.post(uri, json, headers, clientContext);

            checkResponse(response);

            final String result = GGHttpResponses.getStringContent(response);
            
            return jsonReader.read(result, objectClass); 

        } finally {
            GGHttpResponses.consume(response);
            GGCloseables.closeQuietly(response);
        }
    }

    @Override
    public <T> T put(//
            final GGHttpClient httpClient, //
            final URI uri, //
            final Object content, //
            final Map<String, String> headers, //
            final HttpClientContext clientContext, //
            final Class<T> objectClass) {
        
        CloseableHttpResponse response = null;

        try {
            
            if( ! headers.containsKey(HttpHeaders.CONTENT_TYPE) ){
                headers.put(HttpHeaders.CONTENT_TYPE, "application/json");
            }
            
            if( ! headers.containsKey(HttpHeaders.ACCEPT) ){
                headers.put(HttpHeaders.ACCEPT, "application/json");
            }

            final String json = jsonWriter.writeAsString(content);
            
            response = httpClient.put(uri, json, headers, clientContext);

            checkResponse(response);

            final String result = GGHttpResponses.getStringContent(response);
            
            return jsonReader.read(result, objectClass); 

        } finally {
            GGHttpResponses.consume(response);
            GGCloseables.closeQuietly(response);
        }
    }

    private void checkResponse(final CloseableHttpResponse response) {
        
        final StatusLine statusLine = response.getStatusLine();
        
        if( statusLine == null ){
            throw new GGHttpClientResponseException("No status returned.", 0, getBody(response) );
        }
        
        final int status = statusLine.getStatusCode();
        
        if( status < 200 || status > 299 ){
            throw new GGHttpClientResponseException("Non 2xx status received", status, getBody(response));
        }
    }

    private String getBody(final HttpResponse response) {
        try{
            return GGHttpResponses.getStringContent(response);
        }catch(final RuntimeException e){
            return null;
        }
    }

}
