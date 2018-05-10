package com.gantzgulch.tools.httpclient;

import java.net.URI;
import java.security.cert.X509Certificate;

public interface GGSslCertificateRetriever {

    X509Certificate[] retrieve(URI uri);
    
}
