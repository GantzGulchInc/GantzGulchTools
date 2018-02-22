package com.gantzgulch.tools.crypto;

import java.security.SecureRandom;
import java.util.Random;

import com.gantzgulch.tools.common.lang.Arguments;

public enum GGNonce {

    RANDOM( new Random() ),
    SECURE_RANDOM( new SecureRandom());

    private final Random random;

    private GGNonce(final Random random) {
        this.random = random;
    }
    
    public byte[] nonce(final int size){
        
        if( size == 0 ){
            return null;
        }
        
        Arguments.isGreaterThan(size, 0, "size must be a positive integer.");
        
        final byte[] nonce = new byte[size];
        
        random.nextBytes(nonce);
        
        return nonce;
    }
}
