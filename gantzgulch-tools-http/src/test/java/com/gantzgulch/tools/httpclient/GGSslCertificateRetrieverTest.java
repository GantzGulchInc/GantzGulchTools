package com.gantzgulch.tools.httpclient;

import java.net.URI;
import java.security.cert.X509Certificate;

import org.junit.Test;

import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.tools.httpclient.impl.GGSslCertificateRetrieverImpl;

public class GGSslCertificateRetrieverTest {

    private static final GGLogger LOG = GGLogger.getLogger(GGSslCertificateRetrieverTest.class);
    
    @Test
    public void getCerts() {
        
        final GGSslCertificateRetriever rtrv = new GGSslCertificateRetrieverImpl();
        
        final URI uri = URI.create("https://www.google.com");
        
        final X509Certificate certs[] = rtrv.retrieve(uri);
        
        for(final X509Certificate cert : certs) {
            
            LOG.debug("getCerts: cert: \n%s", cert);
            
        }
        
    }
}
