package com.gantzgulch.tools.crypto.alg.aes;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.gantzgulch.tools.crypto.BouncyCastleState;
import com.gantzgulch.tools.crypto.exception.CryptoException;

public final class AESKeyGenerator {

    static {
        BouncyCastleState.init();
    }

    public static final String AES_ALGORITHM = "AES";

    private AESKeyGenerator() {
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
    
    public static SecretKey create(final byte[] key) {
        
        return new SecretKeySpec(key, "AES");
    }

}
