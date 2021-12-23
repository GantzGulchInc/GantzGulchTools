package com.gantzgulch.tools.crypto.alg.dsa;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import com.gantzgulch.tools.crypto.BouncyCastleState;
import com.gantzgulch.tools.crypto.exception.CryptoException;

public final class DSAKeyGenerator {

    static {
        BouncyCastleState.init();
    }

    public static final String DSA_ALGORITHM = "DSA";

    private DSAKeyGenerator() {
        throw new UnsupportedOperationException();
    }

    public static KeyPair generate(final int keysize) {

        try {
            
            final KeyPairGenerator generator = KeyPairGenerator.getInstance(DSA_ALGORITHM);

            generator.initialize(keysize);

            return generator.generateKeyPair();
            
        } catch (final NoSuchAlgorithmException nsae) {
            throw new CryptoException(nsae);
        }
    }

}
