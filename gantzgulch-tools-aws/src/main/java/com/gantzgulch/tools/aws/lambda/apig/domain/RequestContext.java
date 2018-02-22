package com.gantzgulch.tools.aws.lambda.apig.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestContext {

    @JsonProperty("path")
    private String path;
    
    @JsonProperty("accountId")
    private String accountId;
    
    @JsonProperty("resourceId")
    private String resourceId;
    
    @JsonProperty("stage")
    private String stage;
    
    @JsonProperty("requestId")
    private String requestId;
    
    @JsonProperty("resourcePath")
    private String resourcePath;
    
    @JsonProperty("httpMethod")
    private String httpMethod;
    
    @JsonProperty("apiId")
    private String apiId;
    
    public RequestContext() {

    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
