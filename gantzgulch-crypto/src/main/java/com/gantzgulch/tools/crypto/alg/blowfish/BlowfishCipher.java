package com.gantzgulch.tools.crypto.alg.blowfish;

import java.util.ArrayList;
import java.util.List;

import com.gantzgulch.tools.crypto.alg.AbstractGGCipher;

public class BlowfishCipher extends AbstractGGCipher {

    public static final List<BlowfishCipher> CIPHERS = new ArrayList<>();

    public static final BlowfishCipher BLOWFISH_ECB_NO_PADDING = new BlowfishCipher("Blowfish/ECB/NoPadding", 0, 0);
    public static final BlowfishCipher BLOWFISH_ECB_NO_PKCS5Padding = new BlowfishCipher("Blowfish/ECB/PKCS5Padding", 0, 0);


    protected BlowfishCipher(final String algorithm, final int ivSize, final int nonceSize) {

        super(algorithm, ivSize, nonceSize);

        CIPHERS.add(this);
    }

}
