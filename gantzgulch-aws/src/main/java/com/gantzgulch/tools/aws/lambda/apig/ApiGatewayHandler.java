package com.gantzgulch.tools.aws.lambda.apig;

import java.io.IOException;

import com.amazonaws.services.lambda.runtime.Context;
import com.gantzgulch.tools.aws.lambda.apig.domain.ProxyRequest;
import com.gantzgulch.tools.aws.lambda.apig.domain.ProxyResponse;

public interface ApiGatewayHandler {

    ApiGatewayHandlerSignature getSignature();
    
    ProxyResponse handle(ProxyRequest request, Context context) throws IOException;
    
}
