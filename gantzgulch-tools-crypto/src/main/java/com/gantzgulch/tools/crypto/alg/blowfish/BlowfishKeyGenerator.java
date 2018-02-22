package com.gantzgulch.tools.crypto.alg.blowfish;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.gantzgulch.tools.crypto.BouncyCastleState;
import com.gantzgulch.tools.crypto.exception.CryptoException;

public final class BlowfishKeyGenerator {

    static {
        BouncyCastleState.init();
    }

    public static final String BLOWFISH_ALGORITHM = "Blowfish";

    private BlowfishKeyGenerator() {
        throw new UnsupportedOperationException();
    }

    public static SecretKey generate(final int keysize) {

        try {
            
            final KeyGenerator generator = KeyGenerator.getInstance(BLOWFISH_ALGORITHM);

            generator.init(keysize);

            return generator.generateKey();
            
        } catch (final NoSuchAlgorithmException nsae) {
            throw new CryptoException(nsae);
        }
    }

}
