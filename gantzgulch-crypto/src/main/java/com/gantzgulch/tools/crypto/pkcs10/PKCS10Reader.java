package com.gantzgulch.tools.crypto.pkcs10;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;

import com.gantzgulch.tools.common.lang.Arguments;
import com.gantzgulch.tools.crypto.BouncyCastleState;

public class PKCS10Reader {

    static {
        BouncyCastleState.init();
    }

    private final JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter().setProvider("BC");

    public PKCS10Reader() {
    }

    public PKCS10CertificationRequest readCertificateSigningRequest(final String pem) throws IOException {

        Arguments.isNotNull(pem, "pem is required to be non null.");

        final StringReader stringReader = new StringReader(pem);

        return readCertificateSigningRequest(stringReader);
    }

    public PKCS10CertificationRequest readCertificateSigningRequest(final Reader reader) throws IOException {

        Arguments.isNotNull(reader, "reader is required to be non null.");

        try (final PEMParser pemParser = new PEMParser(reader)) {

            final Object pemObject = pemParser.readObject();

            return (PKCS10CertificationRequest) pemObject;
        }

    }

    public X509Certificate readCertificate(final String pem) throws IOException, CertificateException {

        Arguments.isNotNull(pem, "pem is required to be non null.");

        final Reader reader = new StringReader(pem);

        return readCertificate(reader);

    }

    public X509Certificate readCertificate(final Reader reader) throws IOException, CertificateException {

        Arguments.isNotNull(reader, "reader is required to be non null.");

        try (final PEMParser pemParser = new PEMParser(reader)) {

            final Object pemObject = pemParser.readObject();
            final X509CertificateHolder holder = (X509CertificateHolder) pemObject;

            return certConverter.getCertificate(holder);
        }

    }

}
