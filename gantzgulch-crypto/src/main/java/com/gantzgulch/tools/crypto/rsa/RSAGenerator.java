package com.gantzgulch.tools.crypto.rsa;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import com.gantzgulch.tools.crypto.BouncyCastleState;

public class RSAGenerator {

    static {
        BouncyCastleState.init();
    }

    public static final String RSA_ALGORITHM = "RSA";

    public RSAGenerator() {
    }

    public KeyPair generate(final int keysize) {

        try {
            final KeyPairGenerator generator = KeyPairGenerator.getInstance(RSA_ALGORITHM);

            generator.initialize(keysize);

            return generator.generateKeyPair();
            
        } catch (final NoSuchAlgorithmException nsae) {
            throw new RuntimeException("Fatal error: " + nsae.getMessage(), nsae);
        }
    }

}
