package com.gantzgulch.tools.httpclient.impl;

import java.io.IOException;
import java.security.cert.Certificate;

import javax.net.ssl.SSLSession;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpCoreContext;

import com.gantzgulch.logging.core.GGLogger;

public class GGHttpSslResponseInterceptor implements HttpResponseInterceptor {

    public static final String PEER_CERTIFICATES = "PEER_CERTIFICATES";

    private static final GGLogger LOG = GGLogger.getLogger(GGHttpSslResponseInterceptor.class);
    
    public GGHttpSslResponseInterceptor() {

    }

    @Override
    public void process(final HttpResponse response, final HttpContext context) throws HttpException, IOException {

        context.setAttribute(PEER_CERTIFICATES, null);

        final ManagedHttpClientConnection routedConnection = (ManagedHttpClientConnection) context.getAttribute(HttpCoreContext.HTTP_CONNECTION);

        if (routedConnection.isOpen() ) {

            final SSLSession sslSession = routedConnection.getSSLSession();

            if (sslSession != null) {

                final Certificate[] certificates = sslSession.getPeerCertificates();

                context.setAttribute(PEER_CERTIFICATES, certificates);
            }
            
        }else{
            LOG.warn("process: Connection is not open.");
        }

    }

    public static final Certificate[] getCertificates(final HttpContext context) {

        return (Certificate[]) context.getAttribute(PEER_CERTIFICATES);

    }
}
