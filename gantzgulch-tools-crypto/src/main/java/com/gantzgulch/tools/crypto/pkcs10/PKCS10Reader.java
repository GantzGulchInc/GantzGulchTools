package com.gantzgulch.tools.crypto.pkcs10;

import java.io.Reader;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;

import com.gantzgulch.tools.common.lang.GGArgs;
import com.gantzgulch.tools.crypto.BouncyCastleState;
import com.gantzgulch.tools.crypto.exception.CryptoException;
import com.gantzgulch.tools.crypto.pem.PEMReader;

public final class PKCS10Reader {

    static {
        BouncyCastleState.init();
    }

    private static final JcaX509CertificateConverter X509_CERT_CONVERTER = BouncyCastleState.createX509Converter();

    private PKCS10Reader() {
        throw new UnsupportedOperationException();
    }

    public static PKCS10CertificationRequest readCertificateSigningRequest(final String pem) {

        GGArgs.notNull(pem, "pem");

        return PEMReader.read(pem, PKCS10CertificationRequest.class);
    }

    public static PKCS10CertificationRequest readCertificateSigningRequest(final Reader reader) {

        GGArgs.notNull(reader, "reader");

        return PEMReader.read(reader, PKCS10CertificationRequest.class);
    }

    public static X509Certificate readCertificate(final String pem) {

        GGArgs.notNull(pem, "pem");

        try {
            return X509_CERT_CONVERTER.getCertificate(PEMReader.read(pem, X509CertificateHolder.class));
        } catch (final CertificateException e) {
            throw new CryptoException(e);
        }

    }

    public static X509Certificate readCertificate(final Reader reader) {

        GGArgs.notNull(reader, "reader");

        try {
            return X509_CERT_CONVERTER.getCertificate(PEMReader.read(reader, X509CertificateHolder.class));
        } catch (final CertificateException e) {
            throw new CryptoException(e);
        }

    }

}
