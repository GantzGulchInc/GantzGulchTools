package com.gantzgulch.tools.crypto.impl;

import com.gantzgulch.tools.crypto.exception.CryptoException;

public class GGIvSpecRange extends AbstractGGIvSpec {

    private final int minSizeInBits;
    private final int maxSizeInBits;

    public GGIvSpecRange(final int minSizeInBits, final int maxSizeInBits) {
        super(minSizeInBits, maxSizeInBits);
        
        this.minSizeInBits = minSizeInBits;
        this.maxSizeInBits = maxSizeInBits;
    }

    @Override
    public void verify(final byte[] iv) {

        if( iv == null ){
            throw new CryptoException( String.format("Expected an IV with size between %d and %d, but received null.", minSizeInBits, maxSizeInBits));
        }
        
        final int ivLength = iv.length * 8;
        
        if( ivLength < minSizeInBits || iv.length > maxSizeInBits) {
            throw new CryptoException( String.format("Expected an IV with size between %d and %d, but received iv with size %d.", minSizeInBits, maxSizeInBits, ivLength));
        }
    }

}
