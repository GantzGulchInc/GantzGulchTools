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
    

    public static final AESGCMCipher AES_GCM_NO_PADDING = new AESGCMCipher("AES/GCM/NoPadding", KEY_128_192_256, IV_NONCE_96, 128);

    private final int tagLengthBits;

    private AESGCMCipher(final String algorithm, final GGKeySpec keySpec, final GGIvNonceSpec ivNonceSpec, final int tagLengthBits) {
        
        super(algorithm, keySpec, ivNonceSpec);
        
        this.tagLengthBits = tagLengthBits;
        
        CIPHERS.add(this);
    }

    @Override
    public Cipher createCipher(final int opMode, final Key key, final byte[] ivNonce) throws GeneralSecurityException {
        
        final Cipher cipher = Cipher.getInstance(algorithm, "BC");

        final GCMParameterSpec spec = new GCMParameterSpec(tagLengthBits, ivNonce);
        
        cipher.init(opMode, key, spec);
        
        return cipher;
    }
    
}
