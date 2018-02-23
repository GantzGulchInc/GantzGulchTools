package com.gantzgulch.tools.crypto.alg.aes;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;

import com.gantzgulch.tools.crypto.alg.impl.AbstractGGCipher;

public class AESAEADCipher extends AbstractGGCipher {

    public static final List<AESAEADCipher> CIPHERS = new ArrayList<>();

    public static final AESAEADCipher AES_CBC_NO_PADDING = new AESAEADCipher("AES/CBC/NoPadding", 16, 0);
    public static final AESAEADCipher AES_CCM_NO_PADDING = new AESAEADCipher("AES/CCM/NoPadding", 12, 0);
    public static final AESAEADCipher AES_EAX_NO_PADDING = new AESAEADCipher("AES/EAX/NoPadding", 12, 0);
    public static final AESAEADCipher AES_OCB_NO_PADDING = new AESAEADCipher("AES/OCB/NoPadding", 12, 0);

    protected AESAEADCipher(final String algorithm, final int ivSize, final int nonceSize) {

        super(algorithm, ivSize, nonceSize);

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
