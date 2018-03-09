package com.gantzgulch.tools.crypto.alg.aes;

import java.util.ArrayList;
import java.util.List;

import com.gantzgulch.tools.crypto.GGIvSpec;
import com.gantzgulch.tools.crypto.GGKeySpec;
import com.gantzgulch.tools.crypto.GGNonceSpec;
import com.gantzgulch.tools.crypto.alg.impl.AbstractGGCipher;

public class AESCipher extends AbstractGGCipher {

    public static final List<AESCipher> CIPHERS = new ArrayList<>();

    public static final AESCipher AES_ECB_NO_PADDING = new AESCipher("AES/ECB/NoPadding", KEY_128_192_256, IV_NONE, NONCE_NONE);

    protected AESCipher(final String algorithm, final GGKeySpec keySpec, final GGIvSpec ivSpec, final GGNonceSpec nonceSpec) {

        super(algorithm, keySpec, ivSpec, nonceSpec);

        CIPHERS.add(this);
    }

}
