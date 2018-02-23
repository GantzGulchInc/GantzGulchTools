package com.gantzgulch.tools.crypto;

import com.gantzgulch.tools.crypto.impl.GGNonceImpl;

public final class GGNonces {

    public static final GGNonce RANDOM = GGNonceImpl.RANDOM;;
    
    public static final GGNonce SECURE_RANDOM = GGNonceImpl.SECURE_RANDOM;

    private GGNonces() {
        throw new UnsupportedOperationException();
    }
}
