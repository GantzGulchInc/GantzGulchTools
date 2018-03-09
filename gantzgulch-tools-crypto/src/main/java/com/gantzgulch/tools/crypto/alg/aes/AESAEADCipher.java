package com.gantzgulch.tools.crypto.alg.aes;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;

import com.gantzgulch.tools.crypto.GGIvSpec;
import com.gantzgulch.tools.crypto.GGKeySpec;
import com.gantzgulch.tools.crypto.GGNonceSpec;
import com.gantzgulch.tools.crypto.alg.impl.AbstractGGCipher;

public class AESAEADCipher extends AbstractGGCipher {

    public static final List<AESAEADCipher> CIPHERS = new ArrayList<>();

    public static final AESAEADCipher AES_CBC_NO_PADDING = new AESAEADCipher("AES/CBC/NoPadding", KEY_128_192_256, IV_128, NONCE_NONE);

    public static final AESAEADCipher AES_CBC_PKCS7_PADDING = new AESAEADCipher("AES/CBC/PKCS7Padding", KEY_128_192_256, IV_128, NONCE_NONE);
    
    public static final AESAEADCipher AES_CCM_NO_PADDING = new AESAEADCipher("AES/CCM/NoPadding", KEY_128_192_256, IV_96, NONCE_NONE);
    
    public static final AESAEADCipher AES_EAX_NO_PADDING = new AESAEADCipher("AES/EAX/NoPadding", KEY_128_192_256, IV_96, NONCE_NONE);

    public static final AESAEADCipher AES_OCB_NO_PADDING = new AESAEADCipher("AES/OCB/NoPadding", KEY_128_192_256, IV_ANY, NONCE_NONE);

    protected AESAEADCipher(final String algorithm, final GGKeySpec keySpec, final GGIvSpec ivSpec, final GGNonceSpec nonceSpec) {

        super(algorithm, keySpec, ivSpec, nonceSpec);

        CIPHERS.add(this);
    }

    @Override
    protected Cipher createCipher(final int opMode, final Key key, final byte[] iv, final byte[] nonce) throws GeneralSecurityException {

        final Cipher cipher = Cipher.getInstance(algorithm, "BC");

        final IvParameterSpec ivSpec = new IvParameterSpec(iv);

        cipher.init(opMode, key, ivSpec);

        return cipher;
    }

}
