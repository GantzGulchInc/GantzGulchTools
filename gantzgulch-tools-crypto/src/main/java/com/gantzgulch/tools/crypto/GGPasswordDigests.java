package com.gantzgulch.tools.crypto;

import com.gantzgulch.tools.crypto.password.PBKDF2Impl;

public final class GGPasswordDigests {


    private GGPasswordDigests() {
        throw new UnsupportedOperationException();
    }

    public static final PBKDF2Impl PBKDF2 = new PBKDF2Impl();
    
}
