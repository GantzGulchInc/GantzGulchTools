package com.gantzgulch.tools.crypto.aes;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.gantzgulch.tools.crypto.BouncyCastleState;
import com.gantzgulch.tools.crypto.CryptoException;

public final class AESGenerator {

    static {
        BouncyCastleState.init();
    }

    public static final String AES_ALGORITHM = "AES";

    private AESGenerator() {
        throw new UnsupportedOperationException();
    }

    public static SecretKey generate(final int keysize) {

        try {
            
            final KeyGenerator generator = KeyGenerator.getInstance(AES_ALGORITHM);

            generator.init(keysize);

            return generator.generateKey();
            
        } catch (final NoSuchAlgorithmException nsae) {
            throw new CryptoException(nsae);
        }
    }

}
