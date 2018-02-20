package com.gantzgulch.tools.crypto.alg.rsa;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import com.gantzgulch.tools.crypto.BouncyCastleState;
import com.gantzgulch.tools.crypto.exception.CryptoException;

public final class RSAKeyGenerator {

    static {
        BouncyCastleState.init();
    }

    public static final String RSA_ALGORITHM = "RSA";

    private RSAKeyGenerator() {
        throw new UnsupportedOperationException();
    }

    public static KeyPair generate(final int keysize) {

        try {
            
            final KeyPairGenerator generator = KeyPairGenerator.getInstance(RSA_ALGORITHM);

            generator.initialize(keysize);

            return generator.generateKeyPair();
            
        } catch (final NoSuchAlgorithmException nsae) {
            throw new CryptoException(nsae);
        }
    }

}
