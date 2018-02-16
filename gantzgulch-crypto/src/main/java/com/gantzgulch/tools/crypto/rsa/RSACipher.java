package com.gantzgulch.tools.crypto.rsa;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.gantzgulch.tools.crypto.CryptoException;
import com.gantzgulch.tools.crypto.GGCipher;

public enum RSACipher implements GGCipher{

    RSA_NONE_OAEPWithMD5AndMGF1Padding("RSA/NONE/OAEPWithMD5AndMGF1Padding"), //
    RSA_NONE_OAEPWithSHA_1AndMGF1Padding("RSA/NONE/OAEPWithSHA1AndMGF1Padding"), //
    RSA_NONE_OAEPWithSHA_224AndMGF1Padding("RSA/NONE/OAEPWithSHA224AndMGF1Padding"), //
    RSA_NONE_OAEPWithSHA_256AndMGF1Padding("RSA/NONE/OAEPWithSHA256AndMGF1Padding"), //
    RSA_NONE_OAEPWithSHA_384AndMGF1Padding("RSA/NONE/OAEPWithSHA384AndMGF1Padding"), //
    RSA_NONE_OAEPWithSHA_512AndMGF1Padding("RSA/NONE/OAEPWithSHA512AndMGF1Padding");

    private final String algorithm;

    private RSACipher(final String algorithm) {
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
