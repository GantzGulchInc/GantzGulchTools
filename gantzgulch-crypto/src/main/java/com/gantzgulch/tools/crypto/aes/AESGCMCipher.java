package com.gantzgulch.tools.crypto.aes;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;

import com.gantzgulch.tools.crypto.BouncyCastleState;
import com.gantzgulch.tools.crypto.CryptoException;
import com.gantzgulch.tools.crypto.GGCipher;

public enum AESGCMCipher implements GGCipher{

    AES_GCM_NO_PADDING("AES/GCM/NoPadding", 12, 16);

    static {
        BouncyCastleState.init();
    }
    
    private final String algorithm;
    
    private final int nonceLength;

    private final int tagLength;

    private AESGCMCipher(final String algorithm, final int nonceLength, final int tagLength) {
        this.algorithm = algorithm;
        this.nonceLength = nonceLength;
        this.tagLength = tagLength;
    }

    public int getNonceLength() {
        return nonceLength;
    }
    
    @Override
    public byte[] encrypt(final Key key, final byte[] input, final byte[] nonce) {

        try {
            final Cipher cipher = createCipher();

            final GCMParameterSpec spec = new GCMParameterSpec(tagLength * 8, nonce);
            
            cipher.init(Cipher.ENCRYPT_MODE, key, spec);

            return cipher.doFinal(input);

        } catch (final NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException | InvalidAlgorithmParameterException e) {
            throw new CryptoException(e);
        }
    }

    @Override
    public byte[] decrypt(final Key key, final byte[] input, final byte[] nonce) {

        try {
            final Cipher cipher = createCipher();

            final GCMParameterSpec spec = new GCMParameterSpec(tagLength * 8, nonce);
            
            cipher.init(Cipher.DECRYPT_MODE, key, spec);

            return cipher.doFinal(input);

        } catch (final NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException | InvalidAlgorithmParameterException e) {
            throw new CryptoException(e);
        }
    }

    @Override
    public String toString() {
        return algorithm;
    }
    
    private Cipher createCipher() throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException {
        return Cipher.getInstance(algorithm, "BC");
    }

}
