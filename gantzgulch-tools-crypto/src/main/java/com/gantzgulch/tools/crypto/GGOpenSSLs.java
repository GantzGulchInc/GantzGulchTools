package com.gantzgulch.tools.crypto;

import com.gantzgulch.tools.crypto.openssl.AESOpenSSL;

public final class GGOpenSSLs {

    public static final GGOpenSSL AES_128_CBC = AESOpenSSL.AES_128_CBC;

    public static final GGOpenSSL AES_256_CBC = AESOpenSSL.AES_256_CBC;

    private GGOpenSSLs() {
        throw new UnsupportedOperationException();
    }

}
