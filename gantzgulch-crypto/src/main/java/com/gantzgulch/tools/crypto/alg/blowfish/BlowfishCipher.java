package com.gantzgulch.tools.crypto.alg.blowfish;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;

import com.gantzgulch.tools.crypto.alg.AbstractGGCipher;

public class BlowfishCipher extends AbstractGGCipher {

    public static final List<BlowfishCipher> CIPHERS = new ArrayList<>();

    public static final BlowfishCipher BLOWFISH_ECB_NO_PADDING = new BlowfishCipher("Blowfish/ECB/NoPadding", 0, 0);
    public static final BlowfishCipher BLOWFISH_ECB_NO_PKCS5Padding = new BlowfishCipher("Blowfish/ECB/PKCS5Padding", 0, 0);


    protected BlowfishCipher(final String algorithm, final int ivSize, final int nonceSize) {

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
