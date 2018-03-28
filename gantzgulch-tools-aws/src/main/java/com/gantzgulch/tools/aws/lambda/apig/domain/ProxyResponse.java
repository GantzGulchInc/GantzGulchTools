package com.gantzgulch.tools.aws.lambda.apig.domain;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gantzgulch.tools.aws.lambda.apig.LambdaError;
import com.gantzgulch.tools.json.GGJsonWriters;

public class ProxyResponse {

    public static final int SC_UNAUTHORIZED = 401;

    public static final int SC_FORBIDDEN = 403;

    public static final int SC_NOT_FOUND = 404;
    
    public static final int SC_CONFLICT = 409;
    
    @JsonProperty("isBase64Encoded")
    private boolean isBase64Encoded;

    @JsonProperty("statusCode")
    private int statusCode;

    @JsonProperty("headers")
    private Map<String, String> headers;

    @JsonProperty("body")
    private String body;

    public ProxyResponse() {

    }

    public ProxyResponse(final LambdaError error) {
    	
        this.isBase64Encoded = false;
        
        this.statusCode = error.getHttpStatusCode();
        
        this.headers = new HashMap<>();
        
        this.body = error.getBody();
        
    }
    
    public ProxyResponse(final Object resp) {
        this.isBase64Encoded = false;
        this.statusCode = 200;
        this.headers = new HashMap<>();
        this.body = GGJsonWriters.STRICT_ISO8601.writeAsString(resp);
    }

    public boolean isBase64Encoded() {
        return isBase64Encoded;
    }

    public void setBase64Encoded(boolean isBase64Encoded) {
        this.isBase64Encoded = isBase64Encoded;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
