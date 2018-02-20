package com.gantzgulch.tools.crypto.pkcs10;

import java.io.Reader;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;

import com.gantzgulch.tools.common.lang.Arguments;
import com.gantzgulch.tools.crypto.BouncyCastleState;
import com.gantzgulch.tools.crypto.exception.CryptoException;
import com.gantzgulch.tools.crypto.pem.PEMReader;

public final class PKCS10Reader {

    static {
        BouncyCastleState.init();
    }

    private static final JcaX509CertificateConverter X509_CERT_CONVERTER = new JcaX509CertificateConverter().setProvider("BC");

    private PKCS10Reader() {
        throw new UnsupportedOperationException();
    }

    public static PKCS10CertificationRequest readCertificateSigningRequest(final String pem) {

        Arguments.isNotNull(pem, "pem is required to be non null.");

        return PEMReader.read(pem, PKCS10CertificationRequest.class);
    }

    public static PKCS10CertificationRequest readCertificateSigningRequest(final Reader reader) {

        Arguments.isNotNull(reader, "reader is required to be non null.");

        return PEMReader.read(reader, PKCS10CertificationRequest.class);
    }

    public static X509Certificate readCertificate(final String pem) {

        Arguments.isNotNull(pem, "pem is required to be non null.");

        try {
            return X509_CERT_CONVERTER.getCertificate(PEMReader.read(pem, X509CertificateHolder.class));
        } catch (final CertificateException e) {
            throw new CryptoException(e);
        }

    }

    public static X509Certificate readCertificate(final Reader reader) {

        Arguments.isNotNull(reader, "reader is required to be non null.");

        try {
            return X509_CERT_CONVERTER.getCertificate(PEMReader.read(reader, X509CertificateHolder.class));
        } catch (final CertificateException e) {
            throw new CryptoException(e);
        }

    }

}
