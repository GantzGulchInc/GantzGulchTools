package com.gantzgulch.tools.crypto.alg.aes;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;

import com.gantzgulch.tools.crypto.BouncyCastleState;
import com.gantzgulch.tools.crypto.GGIvNonceSpec;
import com.gantzgulch.tools.crypto.GGKeySpec;
import com.gantzgulch.tools.crypto.alg.impl.AbstractGGCipher;

public class AESAEADCipher extends AbstractGGCipher {

    public static final List<AESAEADCipher> CIPHERS = new ArrayList<>();

    public static final AESAEADCipher AES_CBC_NO_PADDING = new AESAEADCipher("AES/CBC/NoPadding", KEY_128_192_256, IV_NOCNE_128);

    public static final AESAEADCipher AES_CBC_PKCS7_PADDING = new AESAEADCipher("AES/CBC/PKCS7Padding", KEY_128_192_256, IV_NOCNE_128);
    
    public static final AESAEADCipher AES_CCM_NO_PADDING = new AESAEADCipher("AES/CCM/NoPadding", KEY_128_192_256, IV_NONCE_96);
    
    public static final AESAEADCipher AES_EAX_NO_PADDING = new AESAEADCipher("AES/EAX/NoPadding", KEY_128_192_256, IV_NONCE_96 );

    public static final AESAEADCipher AES_OCB_NO_PADDING = new AESAEADCipher("AES/OCB/NoPadding", KEY_128_192_256, IV_NONCE_ANY);

    protected AESAEADCipher(final String algorithm, final GGKeySpec keySpec, final GGIvNonceSpec ivNonceSpec) {

        super(algorithm, keySpec, ivNonceSpec);

        CIPHERS.add(this);
    }

    @Override
    public Cipher createCipher(final int opMode, final Key key, final byte[] ivNonce) throws GeneralSecurityException {

        final Cipher cipher = Cipher.getInstance(algorithm, BouncyCastleState.BOUNCY_CASTLE_PROVIDER);

        final IvParameterSpec ivSpec = new IvParameterSpec(ivNonce);

        cipher.init(opMode, key, ivSpec);

        return cipher;
    }

}
