package com.gantzgulch.tools.crypto.pkcs10;

import java.io.IOException;
import java.security.cert.X509Certificate;

import org.bouncycastle.pkcs.PKCS10CertificationRequest;

import com.gantzgulch.tools.common.lang.Arguments;
import com.gantzgulch.tools.crypto.BouncyCastleState;
import com.gantzgulch.tools.crypto.pem.PEMWriter;

public final class PKCS10Writer {

    static {
        BouncyCastleState.init();
    }

    private PKCS10Writer() {
        throw new UnsupportedOperationException();
    }

    public static String write(final PKCS10CertificationRequest x509CertificateRequest) throws IOException {

        Arguments.isNotNull(x509CertificateRequest, "x509CertificateRequest is required to be non null.");

        return PEMWriter.write(x509CertificateRequest);
    }

    public static String write(final X509Certificate x509Certificate) throws IOException {

        Arguments.isNotNull(x509Certificate, "x509Certificate is required to be non null.");

        return PEMWriter.write(x509Certificate);
    }

}
