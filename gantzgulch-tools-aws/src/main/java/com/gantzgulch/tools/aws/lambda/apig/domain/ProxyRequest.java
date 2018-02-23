package com.gantzgulch.tools.aws.lambda.apig.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gantzgulch.tools.aws.lambda.apig.ApiGatewayHandlerSignature;
import com.gantzgulch.tools.aws.lambda.apig.ApiGatewayHandlerSignatureMethod;
import com.gantzgulch.tools.common.lang.GGStrings;
import com.gantzgulch.tools.json.GGJsonReaders;

public class ProxyRequest {

    @JsonProperty("resource")
    private String resource;

    @JsonProperty("path")
    private String path;

    @JsonProperty("httpMethod")
    private String httpMethod;

    @JsonProperty("headers")
    private Map<String, String> headers;

    @JsonProperty("queryStringParameters")
    private Map<String, String> queryStringParameters;

    @JsonProperty("pathParameters")
    private Map<String, String> pathParameters;

    @JsonProperty("stageVariables")
    private Map<String, String> stageVariables;

    @JsonProperty("requestContext")
    private RequestContext requestContext;

    @JsonProperty("body")
    private String body;

    @JsonProperty("isBase64Encoded")
    private Boolean isBase64Encoded;

    public ProxyRequest() {

    }

    public String getClientIp() {

        if (headers != null) {

            final String forwardedFor = headers.get("X-Forwarded-For");

            if (forwardedFor != null) {
                
                final List<String> ips = GGStrings.splitAndClean(forwardedFor, ',');

                if (ips.size() > 0) {
                    return ips.get(0);
                }
            }

        }

        return "unknown";
    }

    public String getResource() {
        return resource;
    }

    public String getPath() {
        return path;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getHeader(final String name) {

        return headers != null ? headers.get(name) : null;
    }

    public String getBody() {
        return body;
    }

    public Set<String> getPathParameterNames() {

        return pathParameters != null ? pathParameters.keySet() : new HashSet<>();
    }

    public String getPathParameter(final String name) {

        return pathParameters != null ? pathParameters.get(name) : null;
    }

    public ApiGatewayHandlerSignature computeCallSignature() {

        final ApiGatewayHandlerSignatureMethod method = ApiGatewayHandlerSignatureMethod.parse(httpMethod);

        return new ApiGatewayHandlerSignature(method, resource);
    }

    public <R> R parseBody(final Class<R> requestType) {

        return GGJsonReaders.STRICT.read(body, requestType);

    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
