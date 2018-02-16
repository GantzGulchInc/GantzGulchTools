package com.gantzgulch.tools.crypto.aes;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.gantzgulch.tools.crypto.BouncyCastleState;
import com.gantzgulch.tools.crypto.CryptoException;
import com.gantzgulch.tools.crypto.GGCipher;

public enum AESCipher implements GGCipher{

    AES_OCB_NO_PADDING("AES/OCB/NoPadding"), //
    AES_OCB_PKCS5_PADDING("AES/OCB/PKCS5Padding");

    static {
        BouncyCastleState.init();
    }
    
    private final String algorithm;
    
    private AESCipher(final String algorithm) {
        this.algorithm = algorithm;
    }

    
    @Override
    public byte[] encrypt(final Key key, final byte[] input, final byte[] nonce) {

        try {
            final Cipher cipher = createCipher();

            cipher.init(Cipher.ENCRYPT_MODE, key);

            return cipher.doFinal(input);

        } catch (final NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException e) {
            throw new CryptoException(e);
        }
    }

    @Override
    public byte[] decrypt(final Key key, final byte[] input, final byte[] nonce) {

        try {
            final Cipher cipher = createCipher();

            cipher.init(Cipher.DECRYPT_MODE, key);

            return cipher.doFinal(input);

        } catch (final NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException e) {
            throw new CryptoException(e);
        }
    }

    private Cipher createCipher() throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException {
        return Cipher.getInstance(algorithm, "BC");
    }

}
