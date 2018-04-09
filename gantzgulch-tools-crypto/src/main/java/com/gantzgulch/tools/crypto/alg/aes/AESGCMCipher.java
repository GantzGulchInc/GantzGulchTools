package com.gantzgulch.tools.crypto.alg.aes;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;

import com.gantzgulch.tools.crypto.GGIvNonceSpec;
import com.gantzgulch.tools.crypto.GGKeySpec;
import com.gantzgulch.tools.crypto.alg.impl.AbstractGGCipher;

public class AESGCMCipher extends AbstractGGCipher {

    public static final List<AESGCMCipher> CIPHERS = new ArrayList<>();
    

    public static final AESGCMCipher AES_GCM_NO_PADDING = new AESGCMCipher("AES/GCM/NoPadding", KEY_128_192_256, IV_NONCE_96, 16);

    private final int tagLength;

    private AESGCMCipher(final String algorithm, final GGKeySpec keySpec, final GGIvNonceSpec ivNonceSpec, final int tagLength) {
        
        super(algorithm, keySpec, ivNonceSpec);
        
        this.tagLength = tagLength;
        
        CIPHERS.add(this);
    }

    @Override
    protected Cipher createCipher(final int opMode, final Key key, final byte[] ivNonce) throws GeneralSecurityException {
        
        final Cipher cipher = Cipher.getInstance(algorithm, "BC");

        final GCMParameterSpec spec = new GCMParameterSpec(tagLength * 8, ivNonce);
        
        cipher.init(opMode, key, spec);
        
        return cipher;
    }
    
}
