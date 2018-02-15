package com.gantzgulch.tools.crypto.pkcs10;

import java.io.IOException;
import java.io.StringWriter;
import java.security.cert.X509Certificate;

import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;

import com.gantzgulch.tools.common.lang.Arguments;
import com.gantzgulch.tools.crypto.BouncyCastleState;

public class PKCS10Writer {

    static {
        BouncyCastleState.init();
    }

    public PKCS10Writer() {
    }

    public String write(final PKCS10CertificationRequest x509CertificateRequest) throws IOException {

        Arguments.isNotNull(x509CertificateRequest, "x509CertificateRequest is required to be non null.");

        return writeObject(x509CertificateRequest);
    }

    public String write(final X509Certificate x509Certificate) throws IOException {

        Arguments.isNotNull(x509Certificate, "x509Certificate is required to be non null.");

        return writeObject(x509Certificate);
    }

    private String writeObject(final Object object) throws IOException {

        final StringWriter stringWriter = new StringWriter();

        final JcaPEMWriter pemWriter = new JcaPEMWriter(stringWriter);

        pemWriter.writeObject(object);
        pemWriter.close();

        return stringWriter.toString();
    }

}
