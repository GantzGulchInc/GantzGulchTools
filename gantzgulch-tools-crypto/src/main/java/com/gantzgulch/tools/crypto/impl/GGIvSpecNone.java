package com.gantzgulch.tools.crypto.impl;

import com.gantzgulch.tools.common.lang.GGArrays;
import com.gantzgulch.tools.crypto.GGIvSpec;
import com.gantzgulch.tools.crypto.exception.CryptoException;

public class GGIvSpecNone extends AbstractGGIvSpec {

    public static final GGIvSpec INSTANCE = new GGIvSpecNone();
    
    public GGIvSpecNone() {
        super(0,0);
    }

    @Override
    public void verify(final byte[] iv) {

        if( GGArrays.size(iv) != 0 ) {
            throw new CryptoException("Expected no IV, but received an IV with size: " + GGArrays.size(iv) );
        }
    }

}
