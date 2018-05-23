package com.gantzgulch.tools.crypto.alg.rsa;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.security.KeyPair;
import java.security.PublicKey;

import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import com.gantzgulch.tools.common.lang.GGArgs;
import com.gantzgulch.tools.crypto.BouncyCastleState;
import com.gantzgulch.tools.crypto.alg.impl.GGKeyPairs;
import com.gantzgulch.tools.crypto.exception.CryptoException;
import com.gantzgulch.tools.crypto.pem.PEMReader;

public final class RSAReader {

    private static final String ALGORITHM = "RSA";

    static {
        BouncyCastleState.init();
    }

    private static final JcaPEMKeyConverter CONVERTER = BouncyCastleState.createPemKeyConverter();

    private RSAReader() {
        throw new UnsupportedOperationException();
    }

    public static KeyPair readKeyPair(final String pem) {

        GGArgs.notNull(pem, "pem");

        try {

            final KeyPair kp = CONVERTER.getKeyPair(PEMReader.read(pem, PEMKeyPair.class));

            GGKeyPairs.verifyAlgorithm(kp, ALGORITHM);

            return kp;

        } catch (final IOException e) {
            throw new CryptoException(e);
        }
    }

    public static KeyPair readKeyPair(final InputStream is) {

        GGArgs.notNull(is, "is");

        try {

            final KeyPair kp = CONVERTER.getKeyPair(PEMReader.read(is, PEMKeyPair.class));

            GGKeyPairs.verifyAlgorithm(kp, ALGORITHM);

            return kp;

        } catch (final IOException e) {
            throw new CryptoException(e);
        }

    }

    public static KeyPair readKeyPair(final Reader reader) {

        GGArgs.notNull(reader, "reader");

        try {

            final KeyPair kp = CONVERTER.getKeyPair(PEMReader.read(reader, PEMKeyPair.class));

            GGKeyPairs.verifyAlgorithm(kp, ALGORITHM);

            return kp;

        } catch (final IOException e) {
            throw new CryptoException(e);
        }

    }

    public static PublicKey readPublicKey(final String pem) {

        GGArgs.notNull(pem, "pem");

        try {

            final PublicKey key = CONVERTER.getPublicKey(PEMReader.read(pem, SubjectPublicKeyInfo.class));

            GGKeyPairs.verifyAlgorithm(key, ALGORITHM);

            return key;

        } catch (final IOException e) {
            throw new CryptoException(e);
        }

    }

    public static PublicKey readPublicKey(final InputStream is) {

        GGArgs.notNull(is, "is");

        try {

            final PublicKey key = CONVERTER.getPublicKey(PEMReader.read(is, SubjectPublicKeyInfo.class));

            GGKeyPairs.verifyAlgorithm(key, ALGORITHM);

            return key;

        } catch (final IOException e) {
            throw new CryptoException(e);
        }

    }

    public static PublicKey readPublicKey(final Reader reader) {

        GGArgs.notNull(reader, "reader");

        try {

            final PublicKey key = CONVERTER.getPublicKey(PEMReader.read(reader, SubjectPublicKeyInfo.class));

            GGKeyPairs.verifyAlgorithm(key, ALGORITHM);

            return key;

        } catch (final IOException e) {
            throw new CryptoException(e);
        }

    }

}
