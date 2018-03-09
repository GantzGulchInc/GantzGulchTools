package com.gantzgulch.tools.crypto.alg.aes;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;

import com.gantzgulch.tools.crypto.GGKeySpec;
import com.gantzgulch.tools.crypto.GGNonceSpec;
import com.gantzgulch.tools.crypto.alg.impl.AbstractGGCipher;
import com.gantzgulch.tools.crypto.impl.GGIvSpecNone;

public class AESGCMCipher extends AbstractGGCipher {

    public static final List<AESGCMCipher> CIPHERS = new ArrayList<>();
    

    public static final AESGCMCipher AES_GCM_NO_PADDING = new AESGCMCipher("AES/GCM/NoPadding", KEY_128_192_256, NONCE_12, 16);

    private final int tagLength;

    private AESGCMCipher(final String algorithm, final GGKeySpec keySpec, final GGNonceSpec nonceSpec, final int tagLength) {
        
        super(algorithm, keySpec, GGIvSpecNone.INSTANCE, nonceSpec);
        
        this.tagLength = tagLength;
        
        CIPHERS.add(this);
    }

    @Override
    protected Cipher createCipher(final int opMode, final Key key, final byte[] iv, final byte[] nonce) throws GeneralSecurityException {
        
        final Cipher cipher = Cipher.getInstance(algorithm, "BC");

        final GCMParameterSpec spec = new GCMParameterSpec(tagLength * 8, nonce);
        
        cipher.init(opMode, key, spec);
        
        return cipher;
    }
    
}
