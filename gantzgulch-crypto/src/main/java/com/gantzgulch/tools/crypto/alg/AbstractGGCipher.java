package com.gantzgulch.tools.crypto.alg;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.Key;

import javax.crypto.Cipher;

import com.gantzgulch.tools.crypto.BouncyCastleState;
import com.gantzgulch.tools.crypto.GGCipher;
import com.gantzgulch.tools.crypto.exception.CryptoException;

public abstract class AbstractGGCipher implements GGCipher {

    static {
        BouncyCastleState.init();
    }

    protected final String algorithm;

    protected final int ivSize;

    protected final int nonceSize;

    public AbstractGGCipher(final String algorithm, final int ivSize, final int nonceSize) {
        this.algorithm = algorithm;
        this.ivSize = ivSize;
        this.nonceSize = nonceSize;
    }

    @Override
    public String getAlgorithm() {
        return algorithm;
    }

    @Override
    public int getIvSize() {
        return ivSize;
    }

    @Override
    public int getNonceSize() {
        return nonceSize;
    }

    protected abstract Cipher createCipher(final int opMode, final Key key, final byte[] iv, final byte[] nonce) throws GeneralSecurityException;

    private Cipher createCipherWithChecks(final int opMode, final Key key, final byte[] iv, final byte[] nonce) throws GeneralSecurityException {

        if (getIvSize() != size(iv)) {
            throw new IllegalArgumentException(String.format("Algorithm: %s, Expected an iv of size: %d get %d.", algorithm, getNonceSize(), size(nonce)));
        }

        if (getNonceSize() != size(nonce)) {
            throw new IllegalArgumentException(String.format("Algorithm: %s, Expected a nonce of size: %d get %d.", algorithm, getNonceSize(), size(nonce)));
        }

        return createCipher(opMode, key, iv, nonce);
    }

    @Override
    public void encrypt(final Key key, final InputStream input, final OutputStream output, final byte[] iv, final byte[] nonce) {

        try {
            final Cipher cipher = createCipherWithChecks(Cipher.ENCRYPT_MODE, key, iv, nonce);

            final byte[] buffer = new byte[64 * 1024];
            byte[] eBuffer;

            int len = 0;

            while ((len = input.read(buffer)) > 0) {

                eBuffer = cipher.update(buffer, 0, len);

                if (eBuffer != null) {
                    output.write(eBuffer);
                }
            }

            eBuffer = cipher.doFinal(buffer, 0, 0);

            output.write(eBuffer);

        } catch (final GeneralSecurityException | IOException e) {
            throw new CryptoException(e);
        }

    }

    @Override
    public byte[] encrypt(final Key key, final byte[] input, final byte[] iv, final byte[] nonce) {

        try {
            final Cipher cipher = createCipherWithChecks(Cipher.ENCRYPT_MODE, key, iv, nonce);

            return cipher.doFinal(input);

        } catch (final GeneralSecurityException e) {
            throw new CryptoException(e);
        }
    }

    @Override
    public void decrypt(final Key key, final InputStream input, final OutputStream output, final byte[] iv, final byte[] nonce) {

        try {
            final Cipher cipher = createCipherWithChecks(Cipher.DECRYPT_MODE, key, iv, nonce);

            final byte[] buffer = new byte[64 * 1024];
            byte[] eBuffer;

            int len = 0;

            while ((len = input.read(buffer)) > 0) {

                eBuffer = cipher.update(buffer, 0, len);

                if (eBuffer != null) {
                    output.write(eBuffer);

                }
            }

            eBuffer = cipher.doFinal(buffer, 0, 0);

            output.write(eBuffer);

        } catch (final GeneralSecurityException | IOException e) {
            throw new CryptoException(e);
        }

    }

    @Override
    public byte[] decrypt(final Key key, final byte[] input, final byte[] iv, final byte[] nonce) {

        try {
            
            final Cipher cipher = createCipherWithChecks(Cipher.DECRYPT_MODE, key, iv, nonce);

            return cipher.doFinal(input);

        } catch (final GeneralSecurityException e) {
            throw new CryptoException(e);
        }
    }

    @Override
    public String toString() {
        return algorithm;
    }
    
    private int size(final byte[] data) {
        return data != null ? data.length : 0;
    }


}
