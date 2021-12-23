package com.gantzgulch.tools.crypto.alg.dsa;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyPair;
import java.security.interfaces.DSAPublicKey;

import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import com.gantzgulch.tools.common.lang.GGArgs;
import com.gantzgulch.tools.crypto.BouncyCastleState;
import com.gantzgulch.tools.crypto.GGReader;
import com.gantzgulch.tools.crypto.alg.impl.GGKeyPairs;
import com.gantzgulch.tools.crypto.exception.CryptoException;
import com.gantzgulch.tools.crypto.pem.PEMReader;

public final class DSAReader implements GGReader<DSAPublicKey> {

    private static final String ALGORITHM = "DSA";

    static {
        BouncyCastleState.init();
    }

    private static final JcaPEMKeyConverter CONVERTER = BouncyCastleState.createPemKeyConverter();

    public KeyPair readKeyPair(final String pem) {

        GGArgs.notNull(pem, "pem");

        try {

            final KeyPair kp = CONVERTER.getKeyPair(PEMReader.read(pem, PEMKeyPair.class));

            GGKeyPairs.verifyAlgorithm(kp, ALGORITHM);

            return kp;

        } catch (final IOException e) {
            throw new CryptoException(e);
        }
    }

    public KeyPair readKeyPair(final InputStream is) {

        GGArgs.notNull(is, "is");

        try {

            final KeyPair kp = CONVERTER.getKeyPair(PEMReader.read(is, PEMKeyPair.class));

            GGKeyPairs.verifyAlgorithm(kp, ALGORITHM);

            return kp;

        } catch (final IOException e) {
            throw new CryptoException(e);
        }

    }

    public KeyPair readKeyPair(final Reader reader) {

        GGArgs.notNull(reader, "reader");

        try {

            final KeyPair kp = CONVERTER.getKeyPair(PEMReader.read(reader, PEMKeyPair.class));

            GGKeyPairs.verifyAlgorithm(kp, ALGORITHM);

            return kp;

        } catch (final IOException e) {
            throw new CryptoException(e);
        }

    }

    public KeyPair readKeyPair(final Path path) {

        GGArgs.notNull(path, "path");

        try (InputStream is = Files.newInputStream(path)) {

            return readKeyPair(is);

        } catch (final IOException e) {
            throw new CryptoException(e);
        }

    }

    public DSAPublicKey readPublicKey(final String pem) {

        GGArgs.notNull(pem, "pem");

        try {

            final DSAPublicKey key = (DSAPublicKey) CONVERTER.getPublicKey(PEMReader.read(pem, SubjectPublicKeyInfo.class));

            GGKeyPairs.verifyAlgorithm(key, ALGORITHM);

            return key;

        } catch (final IOException e) {
            throw new CryptoException(e);
        }

    }

    public DSAPublicKey readPublicKey(final InputStream is) {

        GGArgs.notNull(is, "is");

        try {

            final DSAPublicKey key = (DSAPublicKey) CONVERTER.getPublicKey(PEMReader.read(is, SubjectPublicKeyInfo.class));

            GGKeyPairs.verifyAlgorithm(key, ALGORITHM);

            return key;

        } catch (final IOException e) {
            throw new CryptoException(e);
        }

    }

    public DSAPublicKey readPublicKey(final Reader reader) {

        GGArgs.notNull(reader, "reader");

        try {

            final DSAPublicKey key = (DSAPublicKey) CONVERTER.getPublicKey(PEMReader.read(reader, SubjectPublicKeyInfo.class));

            GGKeyPairs.verifyAlgorithm(key, ALGORITHM);

            return key;

        } catch (final IOException e) {
            throw new CryptoException(e);
        }

    }

    public DSAPublicKey readPublicKey(final Path path) {

        GGArgs.notNull(path, "path");

        try (InputStream is = Files.newInputStream(path)) {

            return readPublicKey(is);

        } catch (final IOException e) {
            throw new CryptoException(e);
        }

    }

}
