package com.gantzgulch.tools.crypto.alg.ec;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;

import com.gantzgulch.tools.crypto.BouncyCastleState;
import com.gantzgulch.tools.crypto.exception.CryptoException;

public final class ECKeyGenerator {

    static {
        BouncyCastleState.init();
    }

    private ECKeyGenerator() {
        throw new UnsupportedOperationException();
    }

    public static KeyPair generate(final ECGenParameterSpec ecGenParameterSpec) {

        try {

            final KeyPairGenerator generator = KeyPairGenerator.getInstance("ECDSA", "BC");

            generator.initialize(ecGenParameterSpec, new SecureRandom());

            return generator.generateKeyPair();

        } catch (final NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException e) {
            throw new CryptoException(e);
        }
    }

    public static KeyPair generate(final String curveName) {

        final ECGenParameterSpec ecGenSpec = new ECGenParameterSpec("curveName");

        return generate(ecGenSpec);

    }

}
