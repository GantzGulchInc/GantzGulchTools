package com.gantzgulch.tools.crypto.impl;

import com.gantzgulch.tools.crypto.GGIvNonceSpec;

public class GGIvNonceSpecAny extends AbstractGGIvNonceSpec {

    public static final GGIvNonceSpec INSTANCE = new GGIvNonceSpecAny();
    
    public GGIvNonceSpecAny() {
        
        super(0, Integer.MAX_VALUE);
        
    }

    @Override
    public void verify(final byte[] iv) {

    }

}
