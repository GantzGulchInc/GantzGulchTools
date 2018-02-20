package com.gantzgulch.tools.crypto.alg.aes;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;

import com.gantzgulch.tools.crypto.alg.AbstractGGCipher;

public class AESCipher extends AbstractGGCipher {

    public static final List<AESCipher> CIPHERS = new ArrayList<>();

    public static final AESCipher AES_ECB_NO_PADDING = new AESCipher("AES/ECB/NoPadding", 0, 0);

    protected AESCipher(final String algorithm, final int ivSize, final int nonceSize) {

        super(algorithm, ivSize, nonceSize);

        CIPHERS.add(this);
    }

    @Override
    protected Cipher createCipher(final int opMode, final Key key, final byte[] iv, final byte[] nonce) throws GeneralSecurityException {

        final Cipher cipher = Cipher.getInstance(algorithm, "BC");

        cipher.init(opMode, key);

        return cipher;
    }

}
