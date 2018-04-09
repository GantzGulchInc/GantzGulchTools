package com.gantzgulch.tools.crypto.alg.impl;

import java.security.KeyPair;
import java.security.PublicKey;

import com.gantzgulch.tools.crypto.exception.CryptoException;
import com.google.common.base.Objects;

public final class GGKeyPairs {

    private GGKeyPairs() {
        throw new UnsupportedOperationException();
    }

    
    public static void verifyAlgorithm(final KeyPair keyPair, final String algorithm) {
        
        final String actualAlgorithm = keyPair.getPrivate().getAlgorithm();
        
        if( ! Objects.equal(algorithm, actualAlgorithm) ){
            
            final String msg = String.format("Expected algorithm: %s but algorithm was: %s", algorithm, actualAlgorithm);
            
            throw new CryptoException(msg);
            
        }
        
    }

    public static void verifyAlgorithm(final PublicKey publicKey, final String algorithm) {
        
        final String actualAlgorithm = publicKey.getAlgorithm();
        
        if( ! Objects.equal(algorithm, actualAlgorithm) ){
            
            final String msg = String.format("Expected algorithm: %s but algorithm was: %s", algorithm, actualAlgorithm);
            
            throw new CryptoException(msg);
            
        }
        
    }
}
