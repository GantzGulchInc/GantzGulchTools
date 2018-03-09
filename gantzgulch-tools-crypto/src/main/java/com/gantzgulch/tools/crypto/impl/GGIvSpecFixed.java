package com.gantzgulch.tools.crypto.impl;

import com.gantzgulch.tools.common.lang.GGArgs;
import com.gantzgulch.tools.crypto.exception.CryptoException;

public class GGIvSpecFixed extends AbstractGGIvSpec {

    private final int sizeInBits;

    public GGIvSpecFixed(final int sizeInBits) {
        
        super(sizeInBits, sizeInBits);
        
        GGArgs.isGreaterThanOrEqual(sizeInBits, 0, "sizeInBits");
        
        this.sizeInBits = sizeInBits;
    }

    @Override
    public void verify(final byte[] iv) {

        if( iv == null ){
            throw new CryptoException( String.format("Expected an IV with size %d, but received null.", sizeInBits));
        }
        
        final int ivLength = iv.length * 8;
        
        if( ivLength != sizeInBits) {
            throw new CryptoException( String.format("Expected an IV with size %d, but received iv with size %d.", ivLength, iv.length));
        }
    }

}
