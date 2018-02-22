package com.gantzgulch.tools.aws.lambda.apig;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.gantzgulch.tools.aws.lambda.apig.domain.ProxyRequest;
import com.gantzgulch.tools.aws.lambda.apig.domain.ProxyResponse;
import com.gantzgulch.tools.aws.lambda.apig.domain.SimpleLambdaError;
import com.gantzgulch.tools.common.logging.GGLogger;
import com.gantzgulch.tools.json.GGJsonReader;
import com.gantzgulch.tools.json.GGJsonWriter;

public class LamdaApigHandler implements RequestStreamHandler {

    private static final GGLogger LOG = GGLogger.getLogger(LamdaApigHandler.class);

    private final Map<ApiGatewayHandlerSignature, ApiGatewayHandler> callHandlerMap = new HashMap<>();

    public LamdaApigHandler(final Set<ApiGatewayHandler> callHandlers) {

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

        final ProxyRequest proxyRequest = GGJsonReader.STRICT_MILLIS.read(input, ProxyRequest.class);

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

                final LambdaError le = new SimpleLambdaError(404, "Url not found.");

                proxyResponse = new ProxyResponse(le);
            }

            final String responseJson = GGJsonWriter.STRICT_MILLIS.writeAsString(proxyResponse);

            output.write(responseJson.getBytes(Charset.forName("UTF-8")));
            output.flush();

        } catch (final Throwable e) {
            LOG.error(e, "An unexpected error occured.");
            throw e;
        } finally      {
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
