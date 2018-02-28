package com.gantzgulch.tools.httpclient.impl;

import java.net.URI;
import java.util.Map;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.protocol.HttpClientContext;

import com.gantzgulch.tools.common.lang.GGCloseables;
import com.gantzgulch.tools.httpclient.GGHttpClient;
import com.gantzgulch.tools.httpclient.GGHttpStringClient;

public class GGHttpStringClientImpl extends AbstractGGHttpSpecialClient implements GGHttpStringClient {

    public GGHttpStringClientImpl(final GGHttpClient httpClient) {
        super(httpClient);
    }

    @Override
    public String get(//
            final URI uri, //
            final Map<String, String> headers, //
            final HttpClientContext httpClientContext) {

        CloseableHttpResponse response = null;

        try {
            
            Map<String,String> newHeaders = addHeaderIfMissing(headers, HttpHeaders.ACCEPT, "*/*");

            response = httpClient.get(uri, newHeaders, httpClientContext);

            checkResponse(response);
            
            return GGHttpResponses.getStringContent(response);

        } finally {
            GGHttpResponses.consume(response);
            GGCloseables.closeQuietly(response);
        }

    }

    @Override
    public String delete(//
            final URI uri, //
            final Map<String, String> headers, //
            final HttpClientContext httpClientContext) {
        
        CloseableHttpResponse response = null;

        try {
            
            Map<String,String> newHeaders = addHeaderIfMissing(headers, HttpHeaders.ACCEPT, "*/*");

            response = httpClient.delete(uri, newHeaders, httpClientContext);

            checkResponse(response);

            return GGHttpResponses.getStringContent(response);

        } finally {
            GGHttpResponses.consume(response);
            GGCloseables.closeQuietly(response);
        }
    }

    @Override
    public String post(//
            final URI uri, //
            final String content, //
            final Map<String, String> headers, //
            final HttpClientContext clientContext) {

        CloseableHttpResponse response = null;

        try {
            
            Map<String,String> newHeaders = addHeaderIfMissing(headers, HttpHeaders.ACCEPT, "*/*");

            response = httpClient.post(uri, content, newHeaders, clientContext);

            checkResponse(response);

            return GGHttpResponses.getStringContent(response);

        } finally {
            GGHttpResponses.consume(response);
            GGCloseables.closeQuietly(response);
        }
    }

    @Override
    public String post(//
            final URI uri, //
            final Map<String, String> content, //
            final Map<String, String> headers, //
            final HttpClientContext clientContext) {

        CloseableHttpResponse response = null;

        try {
            
            Map<String,String> newHeaders = addHeaderIfMissing(headers, HttpHeaders.ACCEPT, "*/*");

            response = httpClient.post(uri, content, newHeaders, clientContext);

            checkResponse(response);

            return GGHttpResponses.getStringContent(response);

        } finally {
            GGHttpResponses.consume(response);
            GGCloseables.closeQuietly(response);
        }
    }

    @Override
    public String put(//
            final URI uri, //
            final String content, //
            final Map<String, String> headers, //
            final HttpClientContext clientContext) {
        
        CloseableHttpResponse response = null;

        try {
            
            Map<String,String> newHeaders = addHeaderIfMissing(headers, HttpHeaders.ACCEPT, "*/*");

            response = httpClient.put(uri, content, newHeaders, clientContext);

            checkResponse(response);

            return GGHttpResponses.getStringContent(response);

        } finally {
            GGHttpResponses.consume(response);
            GGCloseables.closeQuietly(response);
        }
    }
    
    @Override
    public String put(//
            final URI uri, //
            final Map<String, String> content, //
            final Map<String, String> headers, //
            final HttpClientContext clientContext) {
        
        CloseableHttpResponse response = null;

        try {

            Map<String,String> newHeaders = addHeaderIfMissing(headers, HttpHeaders.ACCEPT, "*/*");

            response = httpClient.put(uri, content, newHeaders, clientContext);

            checkResponse(response);

            return GGHttpResponses.getStringContent(response);

        } finally {
            GGHttpResponses.consume(response);
            GGCloseables.closeQuietly(response);
        }
    }

}
