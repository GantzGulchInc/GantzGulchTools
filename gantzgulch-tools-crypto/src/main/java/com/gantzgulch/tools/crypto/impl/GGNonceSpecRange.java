package com.gantzgulch.tools.crypto.impl;

import com.gantzgulch.tools.crypto.exception.CryptoException;

public class GGNonceSpecRange extends AbstractGGNonceSpec {

    private final int minSize;
    private final int maxSize;

    public GGNonceSpecRange(final int minSize, final int maxSize) {
        super(minSize, maxSize);
        this.minSize = minSize;
        this.maxSize = maxSize;
    }

    @Override
    public void verify(final byte[] nonce) {

        if( nonce == null ){
            throw new CryptoException( String.format("Expected a nonce with size between %d and %d, but received null.", minSize, maxSize));
        }
        
        if( nonce.length < minSize || nonce.length > maxSize) {
            throw new CryptoException( String.format("Expected a nonce with size between %d and %d, but received nonce with size %d.", minSize, maxSize, nonce.length));
        }
    }

}
