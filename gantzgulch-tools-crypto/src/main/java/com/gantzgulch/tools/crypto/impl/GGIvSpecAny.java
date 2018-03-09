package com.gantzgulch.tools.crypto.impl;

import com.gantzgulch.tools.crypto.GGIvSpec;

public class GGIvSpecAny extends AbstractGGIvSpec {

    public static final GGIvSpec INSTANCE = new GGIvSpecAny();
    
    public GGIvSpecAny() {
        
        super(0, Integer.MAX_VALUE);
        
    }

    @Override
    public void verify(final byte[] iv) {

    }

}
