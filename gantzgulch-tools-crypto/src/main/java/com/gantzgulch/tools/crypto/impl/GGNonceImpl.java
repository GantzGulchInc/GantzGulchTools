package com.gantzgulch.tools.crypto.impl;

import java.security.SecureRandom;
import java.util.Random;

import com.gantzgulch.tools.common.lang.GGArgs;
import com.gantzgulch.tools.crypto.GGNonce;

public final class GGNonceImpl implements GGNonce {

    public static final GGNonce RANDOM = new GGNonceImpl( new Random() );
    
    public static final GGNonce SECURE_RANDOM = new GGNonceImpl( new SecureRandom());

    private final Random random;

    private GGNonceImpl(final Random random) {
        this.random = random;
    }
    
    @Override
    public byte[] nonce(final int sizeInBytes){
        
        if( sizeInBytes == 0 ){
            return new byte[0];
        }
        
        GGArgs.isGreaterThan(sizeInBytes, 0, "size");
        
        final byte[] nonce = new byte[sizeInBytes];
        
        random.nextBytes(nonce);
        
        return nonce;
    }
}
