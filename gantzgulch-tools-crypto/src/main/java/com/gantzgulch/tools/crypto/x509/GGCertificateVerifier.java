package com.gantzgulch.tools.crypto.x509;

import java.security.cert.X509Certificate;
import java.util.Set;

public interface GGCertificateVerifier {

    GGCertificateVerifyResult verify(final X509Certificate cert, final Set<X509Certificate> intermediateCerts, final Set<X509Certificate> caCerts);
    
    boolean isSelfSigned(final X509Certificate cert);
}
