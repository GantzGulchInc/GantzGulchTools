package com.gantzgulch.tools.aws.lambda.apig;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.tools.aws.lambda.apig.domain.ProxyRequest;
import com.gantzgulch.tools.aws.lambda.apig.domain.ProxyResponse;
import com.gantzgulch.tools.aws.lambda.apig.domain.SimpleLambdaError;
import com.gantzgulch.tools.common.lang.GGIO;
import com.gantzgulch.tools.common.lang.GGStrings;
import com.gantzgulch.tools.json.GGJsonReaders;
import com.gantzgulch.tools.json.GGJsonWriters;

public class LambdaApigHandler implements RequestStreamHandler {

    protected final GGLogger LOG = GGLogger.getLogger(getClass());

    private final Map<ApiGatewayHandlerSignature, ApiGatewayHandler> callHandlerMap = new HashMap<>();

    public LambdaApigHandler(final Set<ApiGatewayHandler> callHandlers) {

        for (final ApiGatewayHandler ch : callHandlers) {

            LOG.info("RestHandler: Installing call handler: %s", ch);

            callHandlerMap.put(ch.getSignature(), ch);
        }

        LOG.info("ctor: this: " + this);

    }

    protected void beforeCall(final Context context, final ProxyRequest proxyRequest) {

    }

    protected void afterCall(final Context context, final ProxyResponse proxyResponse) {

    }

    @Override
    public void handleRequest(//
            final InputStream input, //
            final OutputStream output, //
            final Context context) throws IOException {

        final String inputString = GGIO.readToString(input);

        LOG.info("handleRequest: input: \n%s", inputString);

        final ProxyRequest proxyRequest = GGJsonReaders.LOOSE_ISO8601.read(inputString, ProxyRequest.class);

        LOG.info("handleRequest: proxyRequest: %s", proxyRequest);

        ProxyResponse proxyResponse = null;

        try {

            beforeCall(context, proxyRequest);

            final ApiGatewayHandlerSignature callSignature = proxyRequest.computeCallSignature();

            LOG.info("handleRequest: callSignature: %s", callSignature);

            final ApiGatewayHandler callHandler = callHandlerMap.get(callSignature);

            LOG.info("handleRequest: callHandler: %s", callHandler);

            if (callHandler != null) {

                proxyResponse = handleCall(context, proxyRequest, callHandler);

            } else {

                LOG.warn("handleRequest: returning invalid url.");

                final LambdaError le = new SimpleLambdaError(ProxyResponse.SC_NOT_FOUND, "Url not found.");

                proxyResponse = new ProxyResponse(le);
            }

            final String responseJson = GGJsonWriters.PRETTY_ISO8601.writeAsString(proxyResponse);

            LOG.info("handleRequest: response: \n%s", responseJson);

            output.write(GGStrings.toBytes(responseJson));
            output.flush();

        } catch (final Throwable e) {
            LOG.error(e, "An unexpected error occured.");
            throw e;
        } finally {
            afterCall(context, proxyResponse);
        }
    }

    private ProxyResponse handleCall(//
            final Context context, //
            final ProxyRequest proxyRequest, //
            final ApiGatewayHandler callHandler) throws IOException {

        try {

            return callHandler.handle(proxyRequest, context);

        } catch (final RuntimeException | IOException e) {

            LOG.warn(e, "handleCall: error.");

            final LambdaError le = new SimpleLambdaError(500, "Internal error.");

            return new ProxyResponse(le);
        }
    }

}
