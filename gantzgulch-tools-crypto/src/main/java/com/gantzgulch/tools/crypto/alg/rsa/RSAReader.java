package com.gantzgulch.tools.crypto.alg.rsa;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.security.KeyPair;
import java.security.PublicKey;

import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import com.gantzgulch.tools.common.lang.Arguments;
import com.gantzgulch.tools.crypto.BouncyCastleState;
import com.gantzgulch.tools.crypto.pem.PEMReader;

public final class RSAReader {

    static {
        BouncyCastleState.init();
    }

    private static final JcaPEMKeyConverter CONVERTER = new JcaPEMKeyConverter().setProvider("BC");

    private RSAReader() {
        throw new UnsupportedOperationException();
    }

    public static KeyPair readKeyPair(final String pem) throws IOException {

        Arguments.isNotNull(pem, "pem is required to be non null.");

        return CONVERTER.getKeyPair(PEMReader.read(pem, PEMKeyPair.class));
    }

    public static KeyPair readKeyPair(final InputStream is) throws IOException {

        Arguments.isNotNull(is, "is is required to be non null.");

        return CONVERTER.getKeyPair(PEMReader.read(is, PEMKeyPair.class));
    }

    public static KeyPair readKeyPair(final Reader reader) throws IOException {

        Arguments.isNotNull(reader, "reader is required to be non null.");

        return CONVERTER.getKeyPair(PEMReader.read(reader, PEMKeyPair.class));
    }

    public static PublicKey readPublicKey(final String pem) throws IOException {

        Arguments.isNotNull(pem, "pem is required to be non null.");

        return CONVERTER.getPublicKey( PEMReader.read(pem, SubjectPublicKeyInfo.class));
    }

    public static PublicKey readPublicKey(final InputStream is) throws IOException {

        Arguments.isNotNull(is, "is is required to be non null.");

        return CONVERTER.getPublicKey( PEMReader.read(is, SubjectPublicKeyInfo.class));
    }

    public static PublicKey readPublicKey(final Reader reader) throws IOException {

        Arguments.isNotNull(reader, "reader is required to be non null.");

        return CONVERTER.getPublicKey( PEMReader.read(reader, SubjectPublicKeyInfo.class));
    }

}