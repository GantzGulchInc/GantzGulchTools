package com.gantzgulch.tools.aws.lambda.apig.impl;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.gantzgulch.tools.aws.lambda.apig.ApiGatewayHandler;
import com.gantzgulch.tools.aws.lambda.apig.ApiGatewayHandlerSignature;
import com.gantzgulch.tools.common.logging.GGLogger;

public abstract class AbstractApiGatewayHandler implements ApiGatewayHandler {

    protected final GGLogger LOG = GGLogger.getLogger(getClass());

    private final ApiGatewayHandlerSignature callSignature;

    public AbstractApiGatewayHandler(final ApiGatewayHandlerSignature callSignature) {
        this.callSignature = callSignature;
    }

    @Override
    public ApiGatewayHandlerSignature getSignature() {
        return callSignature;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
