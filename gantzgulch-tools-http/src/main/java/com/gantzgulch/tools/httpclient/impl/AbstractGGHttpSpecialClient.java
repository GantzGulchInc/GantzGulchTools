package com.gantzgulch.tools.httpclient.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;

import com.gantzgulch.tools.httpclient.GGHttpClient;
import com.gantzgulch.tools.httpclient.exception.GGHttpClientResponseException;

public class AbstractGGHttpSpecialClient {

    protected final GGHttpClient httpClient;

    public AbstractGGHttpSpecialClient(final GGHttpClient httpClient) {
        this.httpClient = httpClient;

    }

    protected Map<String,String> addHeaderIfMissing(final Map<String,String> headers, final String name, final String value){
        
        final Map<String,String> newHeaders = headers == null ? new HashMap<>() : new HashMap<>(headers);

        if( ! newHeaders.containsKey(name) ){
            newHeaders.put(name, value);
        }
        
        return newHeaders;
        
    }
    protected void checkResponse(final CloseableHttpResponse response) {
        
        final StatusLine statusLine = response.getStatusLine();
        
        if( statusLine == null ){
            throw new GGHttpClientResponseException("No status returned.", 0, getBody(response) );
        }
        
        final int status = statusLine.getStatusCode();
        
        if( status < 200 || status > 299 ){
            throw new GGHttpClientResponseException("Non 2xx status received", status, getBody(response));
        }
    }

    protected String getBody(final HttpResponse response) {
        try{
            return GGHttpResponses.getStringContent(response);
        }catch(final RuntimeException e){
            return null;
        }
    }

}
