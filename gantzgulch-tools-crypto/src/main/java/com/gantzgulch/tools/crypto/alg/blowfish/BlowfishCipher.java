package com.gantzgulch.tools.crypto.alg.blowfish;

import java.util.ArrayList;
import java.util.List;

import com.gantzgulch.tools.crypto.GGIvNonceSpec;
import com.gantzgulch.tools.crypto.GGKeySpec;
import com.gantzgulch.tools.crypto.alg.impl.AbstractGGCipher;
import com.gantzgulch.tools.crypto.impl.GGKeySpecImpl;

public class BlowfishCipher extends AbstractGGCipher {

    public static final List<BlowfishCipher> CIPHERS = new ArrayList<>();

    public static final BlowfishCipher BLOWFISH_ECB_NO_PADDING = new BlowfishCipher("Blowfish/ECB/NoPadding", GGKeySpecImpl.createRanged(32, 448), IV_NONCE_NONE);
    public static final BlowfishCipher BLOWFISH_ECB_NO_PKCS5Padding = new BlowfishCipher("Blowfish/ECB/PKCS5Padding", GGKeySpecImpl.createRanged(32, 448), IV_NONCE_NONE);


    protected BlowfishCipher(final String algorithm, final GGKeySpec keySpec, final GGIvNonceSpec ivNonceSpec) {

        super(algorithm, keySpec, ivNonceSpec);

        CIPHERS.add(this);
    }

}
