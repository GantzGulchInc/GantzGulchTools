package com.gantzgulch.tools.crypto.alg.aes;

import java.util.ArrayList;
import java.util.List;

import com.gantzgulch.tools.crypto.GGIvNonceSpec;
import com.gantzgulch.tools.crypto.GGKeySpec;
import com.gantzgulch.tools.crypto.alg.impl.AbstractGGCipher;

public class AESCipher extends AbstractGGCipher {

    public static final List<AESCipher> CIPHERS = new ArrayList<>();

    public static final AESCipher AES_ECB_NO_PADDING = new AESCipher("AES/ECB/NoPadding", KEY_128_192_256, IV_NONCE_NONE);

    protected AESCipher(final String algorithm, final GGKeySpec keySpec, final GGIvNonceSpec ivNonceSpec) {

        super(algorithm, keySpec, ivNonceSpec);

        CIPHERS.add(this);
    }

}
