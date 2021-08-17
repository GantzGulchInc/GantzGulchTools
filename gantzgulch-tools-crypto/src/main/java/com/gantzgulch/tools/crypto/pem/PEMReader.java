package com.gantzgulch.tools.crypto.pem;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.security.KeyPair;
import java.security.PublicKey;

import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import com.gantzgulch.tools.common.lang.Cast;
import com.gantzgulch.tools.common.lang.GGArgs;
import com.gantzgulch.tools.crypto.BouncyCastleState;
import com.gantzgulch.tools.crypto.exception.CryptoException;

public final class PEMReader {

    static {
        BouncyCastleState.init();
    }

    private static final JcaPEMKeyConverter CONVERTER = BouncyCastleState.createPemKeyConverter();

    private PEMReader() {
        throw new UnsupportedOperationException();
    }

    public static KeyPair readKeyPair(final Reader reader) {

        try {

            final PEMKeyPair pemKeyPair = read(reader, PEMKeyPair.class);

            return CONVERTER.getKeyPair(pemKeyPair);

        } catch (final PEMException e) {
            throw new CryptoException(e);
        }
    }

    public static KeyPair readKeyPair(final String pem) {

        try {

            final PEMKeyPair pemKeyPair = read(pem, PEMKeyPair.class);

            return CONVERTER.getKeyPair(pemKeyPair);

        } catch (final PEMException e) {
            throw new CryptoException(e);
        }
    }

    public static KeyPair readKeyPair(final InputStream inputStreamPem) {

        try {

            final PEMKeyPair pemKeyPair = read(inputStreamPem, PEMKeyPair.class);

            return CONVERTER.getKeyPair(pemKeyPair);

        } catch (final PEMException e) {
            throw new CryptoException(e);
        }
    }

    public static PublicKey readPublicKey(final Reader reader) {

        try {

            final SubjectPublicKeyInfo publicKeyInfo = read(reader, SubjectPublicKeyInfo.class);

            return CONVERTER.getPublicKey(publicKeyInfo);

        } catch (final PEMException e) {
            throw new CryptoException(e);
        }

    }

    public static PublicKey readPublicKey(final String pem) {

        try {

            final SubjectPublicKeyInfo publicKeyInfo = read(pem, SubjectPublicKeyInfo.class);

            return CONVERTER.getPublicKey(publicKeyInfo);

        } catch (final PEMException e) {
            throw new CryptoException(e);
        }

    }

    public static PublicKey readPublicKey(final InputStream inputStreamPem) {

        try {

            final SubjectPublicKeyInfo publicKeyInfo = read(inputStreamPem, SubjectPublicKeyInfo.class);

            return CONVERTER.getPublicKey(publicKeyInfo);

        } catch (final PEMException e) {
            throw new CryptoException(e);
        }

    }

    public static <T> T read(final String pem, final Class<T> objectClass) {

        GGArgs.notNull(pem, "pem");

        final StringReader stringReader = new StringReader(pem);

        return read(stringReader, objectClass);
    }

    public static <T> T read(final InputStream is, final Class<T> objectClass) {

        GGArgs.notNull(is, "is");

        final Reader reader = new InputStreamReader(is, Charset.forName("UTF-8"));

        return read(reader, objectClass);

    }

    public static <T> T read(final Reader reader, final Class<T> objectClass) {

        GGArgs.notNull(reader, "reader");

        try (final PEMParser pemParser = new PEMParser(reader)) {

            final Object pemObject = pemParser.readObject();

            if (pemObject == null) {
                return null;
            }

            try {
                return Cast.cast(pemObject);

            } catch (final ClassCastException e) {
                throw new CryptoException("PEM did not contain correct object type.", e);
            }
        } catch (final IOException e) {
            throw new CryptoException(e);
        }

    }
}
