package com.gantzgulch.tools.crypto.impl;

import com.gantzgulch.tools.crypto.exception.CryptoException;

public class GGIvNonceSpecRange extends AbstractGGIvNonceSpec {

    private final int minSizeInBits;
    private final int maxSizeInBits;

    public GGIvNonceSpecRange(final int minSizeInBits, final int maxSizeInBits) {
        super(minSizeInBits, maxSizeInBits);
        
        this.minSizeInBits = minSizeInBits;
        this.maxSizeInBits = maxSizeInBits;
    }

    @Override
    public void verify(final byte[] ivNonce) {

        if( ivNonce == null ){
            throw new CryptoException( String.format("Expected an IV/Nonce with size between %d and %d, but received null.", minSizeInBits, maxSizeInBits));
        }
        
        final int ivNonceLength = ivNonce.length * 8;
        
        if( ivNonceLength < minSizeInBits || ivNonce.length > maxSizeInBits) {
            throw new CryptoException( String.format("Expected an IV/Nonce with size between %d and %d, but received iv/nonce with size %d.", minSizeInBits, maxSizeInBits, ivNonceLength));
        }
    }

}
