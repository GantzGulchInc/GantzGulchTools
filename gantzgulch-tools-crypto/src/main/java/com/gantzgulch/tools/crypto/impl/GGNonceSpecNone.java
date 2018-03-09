package com.gantzgulch.tools.crypto.impl;

import com.gantzgulch.tools.common.lang.GGArrays;
import com.gantzgulch.tools.crypto.GGNonceSpec;
import com.gantzgulch.tools.crypto.exception.CryptoException;

public class GGNonceSpecNone extends AbstractGGNonceSpec {

    public static final GGNonceSpec INSTANCE = new GGNonceSpecNone();
    
    public GGNonceSpecNone() {
        super(0,0);
    }

    @Override
    public void verify(final byte[] nonce) {

        if( GGArrays.size(nonce) != 0 ) {
            throw new CryptoException("Expected no nonce, but received a nonce with size: " + GGArrays.size(nonce) );
        }
    }

}
