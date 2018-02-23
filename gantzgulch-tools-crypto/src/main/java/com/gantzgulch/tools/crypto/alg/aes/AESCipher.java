package com.gantzgulch.tools.crypto.alg.aes;

import java.util.ArrayList;
import java.util.List;

import com.gantzgulch.tools.crypto.alg.impl.AbstractGGCipher;

public class AESCipher extends AbstractGGCipher {

    public static final List<AESCipher> CIPHERS = new ArrayList<>();

    public static final AESCipher AES_ECB_NO_PADDING = new AESCipher("AES/ECB/NoPadding", 0, 0);

    protected AESCipher(final String algorithm, final int ivSize, final int nonceSize) {

        super(algorithm, ivSize, nonceSize);

        CIPHERS.add(this);
    }

}
