package com.gantzgulch.tools.crypto.impl;

import com.gantzgulch.tools.common.lang.GGArgs;
import com.gantzgulch.tools.crypto.exception.CryptoException;

public class GGNonceSpecFixed extends AbstractGGNonceSpec {

    private final int size;

    public GGNonceSpecFixed(final int size) {
        
        super(size, size);
        
        GGArgs.isGreaterThanOrEqual(size, 0, "size");
        
        this.size = size;
    }

    @Override
    public void verify(final byte[] nonce) {

        if( nonce == null ){
            throw new CryptoException( String.format("Expected a nonce with size %d, but received null.", size));
        }
        
        if( nonce.length != size) {
            throw new CryptoException( String.format("Expected a nonce with size %d, but received nonce with size %d.", size, nonce.length));
        }
    }

}
