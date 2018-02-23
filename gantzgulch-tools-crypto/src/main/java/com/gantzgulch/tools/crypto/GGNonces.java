package com.gantzgulch.tools.crypto;

import java.security.SecureRandom;
import java.util.Random;

import com.gantzgulch.tools.common.lang.Arguments;

public class GGNonces implements GGNonce {

    public static final GGNonce RANDOM = new GGNonces( new Random() );
    
    public static final GGNonce SECURE_RANDOM = new GGNonces( new SecureRandom());

    private final Random random;

    private GGNonces(final Random random) {
        this.random = random;
    }
    
    @Override
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
