package com.gantzgulch.tools.aws.lambda.apig.impl;

import java.io.IOException;

import com.amazonaws.services.lambda.runtime.Context;
import com.gantzgulch.tools.aws.lambda.apig.ApiGatewayHandlerSignature;
import com.gantzgulch.tools.aws.lambda.apig.domain.ProxyRequest;
import com.gantzgulch.tools.aws.lambda.apig.domain.ProxyResponse;

public abstract class AbstractTypedApiGatewayHandler<T> extends AbstractApiGatewayHandler {

	private final Class<T> requestClass;

    public AbstractTypedApiGatewayHandler(final ApiGatewayHandlerSignature callSignature, final Class<T> requestClass) {
    	
    	super(callSignature);
    	
		this.requestClass = requestClass;
    }

	@Override
	public ProxyResponse handle(final ProxyRequest request, final Context context) throws IOException{
		
	    LOG.debug("handle: request: context: %s", context);
	    
		final T typedRequest = request.parseBody(requestClass);

		LOG.debug("handle: typeRequest: %s", typedRequest);

		return handle(request, typedRequest, context);
	}

	
	public abstract ProxyResponse handle(final ProxyRequest request, final T typedRequest, final Context context);

}
