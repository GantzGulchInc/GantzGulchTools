package com.gantzgulch.tools.aws.lambda.apig;

import java.util.HashMap;
import java.util.Map;

public enum ApiGatewayHandlerSignatureMethod {
    
	GET("GET"), //
    POST("POST"), //
    PUT("PUT"), //
    DELETE("DELETE"),
    HEAD("HEAD"),
    OPTIONS("OPTIONS"),
    UNKNOWN("UNKNOWN");
    
    private static Map<String, ApiGatewayHandlerSignatureMethod> methodMap;

    static {

        methodMap = new HashMap<>();
        
        for(final ApiGatewayHandlerSignatureMethod m : ApiGatewayHandlerSignatureMethod.values()){
            methodMap.put(m.getMethodName(), m);
        }
    }
    
    private final String methodName;

    private ApiGatewayHandlerSignatureMethod(final String methodName){
        this.methodName = methodName;
        
    }
    
    public String getMethodName() {
        return methodName;
    }

    public static ApiGatewayHandlerSignatureMethod parse(final String httpMethod) {
        
        final ApiGatewayHandlerSignatureMethod method = methodMap.get(httpMethod);
        
        return method != null ? method : UNKNOWN;
    }
}