package com.gantzgulch.tools.crypto.password;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.gantzgulch.tools.common.lang.GGArgs;
import com.gantzgulch.tools.crypto.GGNonces;
import com.gantzgulch.tools.crypto.exception.CryptoException;

public class PBKDF2Impl {

    public PBKDF2Impl() {
    }

    public PBKDF2Hash hash(final int iterations, final char[] input) {

        GGArgs.notNull(input, "input");
        
        try {
            
            final byte[] salt = GGNonces.SECURE_RANDOM.nonce(16);

            final PBEKeySpec spec = new PBEKeySpec(input, salt, iterations, 64 * 8);
            
            final SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            final byte[] hash = skf.generateSecret(spec).getEncoded();

            return new PBKDF2Hash(iterations, salt, hash);
            
        } catch (final NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new CryptoException(e);
        }

        
    }

    public PBKDF2Hash hash(final int iterations, final String password) {

        GGArgs.isGreaterThan(iterations, 0, "iterations");
        GGArgs.notNull(password, "password");

        return hash(iterations, password.toCharArray());
    }

    public boolean verify(final char[] input, final PBKDF2Hash pbHash) {

        try {

            final byte[] salt = pbHash.getSalt();
            final int iterations = pbHash.getIterations();
            final byte[] hash = pbHash.getHash();
            
            final PBEKeySpec spec = new PBEKeySpec(input, salt, iterations, hash.length * 8);

            final SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            final byte[] testHash = skf.generateSecret(spec).getEncoded();

            int diff = hash.length ^ testHash.length;

            for (int i = 0; i < hash.length && i < testHash.length; i++) {
                diff |= hash[i] ^ testHash[i];
            }

            return diff == 0;
            
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        
    }

    public boolean verify(final String inputString, final PBKDF2Hash pbHash) {

        GGArgs.notNull(inputString, "inputString");
        GGArgs.notNull(pbHash, "pbHash");
        
        return verify(inputString.toCharArray(), pbHash);
        
    }

}
