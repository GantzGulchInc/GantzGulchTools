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
import com.gantzgulch.tools.httpclient.GGHttpStringClient;
import com.gantzgulch.tools.httpclient.exception.GGHttpClientResponseException;

public class GGHttpStringClientImpl implements GGHttpStringClient {

    public GGHttpStringClientImpl() {

    }

    @Override
    public String get(//
            final GGHttpClient httpClient, //
            final URI uri, //
            final Map<String, String> headers, //
            final HttpClientContext httpClientContext) {

        CloseableHttpResponse response = null;

        try {
            
            if( ! headers.containsKey(HttpHeaders.ACCEPT) ){
                headers.put(HttpHeaders.ACCEPT, "application/json");
            }

            response = httpClient.get(uri, headers, httpClientContext);

            checkResponse(response);
            
            return GGHttpResponses.getStringContent(response);

        } finally {
            GGHttpResponses.consume(response);
            GGCloseables.closeQuietly(response);
        }

    }

    @Override
    public String delete(//
            final GGHttpClient httpClient, //
            final URI uri, //
            final Map<String, String> headers, //
            final HttpClientContext httpClientContext) {
        
        CloseableHttpResponse response = null;

        try {
            
            if( ! headers.containsKey(HttpHeaders.ACCEPT) ){
                headers.put(HttpHeaders.ACCEPT, "application/json");
            }

            response = httpClient.delete(uri, headers, httpClientContext);

            checkResponse(response);

            return GGHttpResponses.getStringContent(response);

        } finally {
            GGHttpResponses.consume(response);
            GGCloseables.closeQuietly(response);
        }
    }

    @Override
    public String post(//
            final GGHttpClient httpClient, //
            final URI uri, //
            final String content, //
            final Map<String, String> headers, //
            final HttpClientContext clientContext) {

        CloseableHttpResponse response = null;

        try {
            
            if( ! headers.containsKey(HttpHeaders.CONTENT_TYPE) ){
                headers.put(HttpHeaders.CONTENT_TYPE, "application/json");
            }
            
            if( ! headers.containsKey(HttpHeaders.ACCEPT) ){
                headers.put(HttpHeaders.ACCEPT, "application/json");
            }

            response = httpClient.post(uri, content, headers, clientContext);

            checkResponse(response);

            return GGHttpResponses.getStringContent(response);

        } finally {
            GGHttpResponses.consume(response);
            GGCloseables.closeQuietly(response);
        }
    }

    @Override
    public String post(//
            final GGHttpClient httpClient, //
            final URI uri, //
            final Map<String, String> content, //
            final Map<String, String> headers, //
            final HttpClientContext clientContext) {

        CloseableHttpResponse response = null;

        try {
            
            if( ! headers.containsKey(HttpHeaders.CONTENT_TYPE) ){
                headers.put(HttpHeaders.CONTENT_TYPE, "application/json");
            }
            
            if( ! headers.containsKey(HttpHeaders.ACCEPT) ){
                headers.put(HttpHeaders.ACCEPT, "application/json");
            }

            response = httpClient.post(uri, content, headers, clientContext);

            checkResponse(response);

            return GGHttpResponses.getStringContent(response);

        } finally {
            GGHttpResponses.consume(response);
            GGCloseables.closeQuietly(response);
        }
    }

    @Override
    public String put(//
            final GGHttpClient httpClient, //
            final URI uri, //
            final String content, //
            final Map<String, String> headers, //
            final HttpClientContext clientContext) {
        
        CloseableHttpResponse response = null;

        try {
            
            if( ! headers.containsKey(HttpHeaders.CONTENT_TYPE) ){
                headers.put(HttpHeaders.CONTENT_TYPE, "application/json");
            }
            
            if( ! headers.containsKey(HttpHeaders.ACCEPT) ){
                headers.put(HttpHeaders.ACCEPT, "application/json");
            }

            response = httpClient.put(uri, content, headers, clientContext);

            checkResponse(response);

            return GGHttpResponses.getStringContent(response);

        } finally {
            GGHttpResponses.consume(response);
            GGCloseables.closeQuietly(response);
        }
    }
    
    @Override
    public String put(//
            final GGHttpClient httpClient, //
            final URI uri, //
            final Map<String, String> content, //
            final Map<String, String> headers, //
            final HttpClientContext clientContext) {
        
        CloseableHttpResponse response = null;

        try {
            
            if( ! headers.containsKey(HttpHeaders.CONTENT_TYPE) ){
                headers.put(HttpHeaders.CONTENT_TYPE, "application/json");
            }
            
            if( ! headers.containsKey(HttpHeaders.ACCEPT) ){
                headers.put(HttpHeaders.ACCEPT, "application/json");
            }

            response = httpClient.put(uri, content, headers, clientContext);

            checkResponse(response);

            return GGHttpResponses.getStringContent(response);

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
