package com.gantzgulch.tools.crypto.rsa;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.security.KeyPair;
import java.security.PublicKey;

import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import com.gantzgulch.tools.common.lang.Arguments;
import com.gantzgulch.tools.crypto.BouncyCastleState;

public class RSAReader {

    static {
        BouncyCastleState.init();
    }

    private final JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");

    public RSAReader() {
    }

    public KeyPair readKeyPair(final String pem) throws IOException {

        Arguments.isNotNull(pem, "pem is required to be non null.");

        final StringReader stringReader = new StringReader(pem);

        return readKeyPair(stringReader);
    }

    public KeyPair readKeyPair(final InputStream is) throws IOException {

        Arguments.isNotNull(is, "is is required to be non null.");

        return readKeyPair(new InputStreamReader(is, Charset.forName("UTF-8")));
    }

    public KeyPair readKeyPair(final Reader reader) throws IOException {

        Arguments.isNotNull(reader, "reader is required to be non null.");

        try (final PEMParser pemParser = new PEMParser(reader)) {

            final Object pemObject = pemParser.readObject();

            final KeyPair keyPair = converter.getKeyPair((PEMKeyPair) pemObject);

            return keyPair;
        }
    }

    public PublicKey readPublicKey(final String pem) throws IOException {

        Arguments.isNotNull(pem, "pem is required to be non null.");

        final StringReader stringReader = new StringReader(pem);

        return readPublicKey(stringReader);
    }

    public PublicKey readPublicKey(final InputStream is) throws IOException {

        Arguments.isNotNull(is, "is is required to be non null.");

        return readPublicKey(new InputStreamReader(is, Charset.forName("UTF-8")));

    }

    public PublicKey readPublicKey(final Reader reader) throws IOException {

        Arguments.isNotNull(reader, "reader is required to be non null.");

        try (final PEMParser pemParser = new PEMParser(reader)) {

            final Object pemObject = pemParser.readObject();

            return converter.getPublicKey((SubjectPublicKeyInfo) pemObject);
        }

    }

}
