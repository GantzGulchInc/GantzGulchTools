package com.gantzgulch.tools.crypto.impl;

import com.gantzgulch.tools.common.lang.GGArrays;
import com.gantzgulch.tools.crypto.GGIvNonceSpec;
import com.gantzgulch.tools.crypto.exception.CryptoException;

public class GGIvNonceSpecNone extends AbstractGGIvNonceSpec {

    public static final GGIvNonceSpec INSTANCE = new GGIvNonceSpecNone();
    
    public GGIvNonceSpecNone() {
        super(0,0);
    }

    @Override
    public void verify(final byte[] ivNonce) {

        if( GGArrays.size(ivNonce) != 0 ) {
            throw new CryptoException("Expected no IV/Nonce, but received an IV/Nonce with size: " + GGArrays.size(ivNonce) );
        }
    }

}
