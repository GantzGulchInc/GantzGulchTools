package com.gantzgulch.tools.crypto.impl;

import com.gantzgulch.tools.common.lang.GGArgs;
import com.gantzgulch.tools.crypto.exception.CryptoException;

public class GGIvNonceSpecFixed extends AbstractGGIvNonceSpec {

    private final int sizeInBits;

    public GGIvNonceSpecFixed(final int sizeInBits) {
        
        super(sizeInBits, sizeInBits);
        
        GGArgs.isGreaterThanOrEqual(sizeInBits, 0, "sizeInBits");
        
        this.sizeInBits = sizeInBits;
    }

    @Override
    public void verify(final byte[] ivNonce) {

        if( ivNonce == null ){
            throw new CryptoException( String.format("Expected an IV/Nonce with size %d, but received null.", sizeInBits));
        }
        
        final int ivNonceLength = ivNonce.length * 8;
        
        if( ivNonceLength != sizeInBits) {
            throw new CryptoException( String.format("Expected an IV/Nonce with size %d, but received iv with size %d.", ivNonceLength, ivNonce.length));
        }
    }

}
