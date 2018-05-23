package com.gantzgulch.tools.crypto.alg.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.Key;

import javax.crypto.Cipher;

import com.gantzgulch.tools.common.lang.GGArgs;
import com.gantzgulch.tools.crypto.BouncyCastleState;
import com.gantzgulch.tools.crypto.GGCipher;
import com.gantzgulch.tools.crypto.GGIvNonceSpec;
import com.gantzgulch.tools.crypto.GGKeySpec;
import com.gantzgulch.tools.crypto.exception.CryptoException;
import com.gantzgulch.tools.crypto.impl.GGIvNonceSpecAny;
import com.gantzgulch.tools.crypto.impl.GGIvNonceSpecFixed;
import com.gantzgulch.tools.crypto.impl.GGIvNonceSpecNone;
import com.gantzgulch.tools.crypto.impl.GGIvNonceSpecSet;

public abstract class AbstractGGCipher implements GGCipher {

    public static final GGKeySpec KEY_128 = GGKeySpec.create(128);
    public static final GGKeySpec KEY_128_192_256 = GGKeySpec.create(128,192,256);
    
    public static final GGKeySpec KEY_RSA = GGKeySpec.create(1024, 2048, 4096);
    
    public static final GGIvNonceSpec IV_NONCE_NONE = GGIvNonceSpecNone.INSTANCE;
    public static final GGIvNonceSpec IV_NONCE_ANY = GGIvNonceSpecAny.INSTANCE;
    public static final GGIvNonceSpec IV_NONCE_96 = new GGIvNonceSpecFixed(96);
    public static final GGIvNonceSpec IV_NOCNE_128 = new GGIvNonceSpecFixed(128);
    public static final GGIvNonceSpec IV_NOCNE_96_128_192_256 = new GGIvNonceSpecSet(96, 128, 192, 256);
    public static final GGIvNonceSpec IV_NONCE_128_192_256 = new GGIvNonceSpecSet(128, 192, 256);
    
    static {
        BouncyCastleState.init();
    }

    protected final String algorithm;

    protected final GGKeySpec keySpec;
    
    protected final GGIvNonceSpec ivNonceSpec;


    public AbstractGGCipher(final String algorithm, final GGKeySpec keySpec, final GGIvNonceSpec ivNonceSpec) {
        
        GGArgs.notNull(keySpec, "keySpec");
        GGArgs.notNull(ivNonceSpec, "ivSpec");
        
        this.algorithm = algorithm;
        this.keySpec = keySpec;
        this.ivNonceSpec = ivNonceSpec;
    }

    @Override
    public String getAlgorithm() {
        return algorithm;
    }

    @Override
    public GGKeySpec getKeySpec() {
        return keySpec;
    }
    
    @Override
    public GGIvNonceSpec getIvNonceSpec() {
        return ivNonceSpec;
    }
    
    @Override
    public Cipher createCipher(final int opMode, final Key key, final byte[] ivNonce) throws GeneralSecurityException {

        final Cipher cipher = Cipher.getInstance(algorithm, BouncyCastleState.BOUNCY_CASTLE_PROVIDER);

        cipher.init(opMode, key);

        return cipher;
    }
    
    private Cipher createCipherWithChecks(final int opMode, final Key key, final byte[] ivNonce) throws GeneralSecurityException {

        ivNonceSpec.verify(ivNonce);

        return createCipher(opMode, key, ivNonce);
    }

    @Override
    public void encrypt(final Key key, final InputStream input, final OutputStream output, final byte[] ivNonce) {

        try {
            final Cipher cipher = createCipherWithChecks(Cipher.ENCRYPT_MODE, key, ivNonce);

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
    public byte[] encrypt(final Key key, final byte[] input, final byte[] ivNonce) {

        try {
            final Cipher cipher = createCipherWithChecks(Cipher.ENCRYPT_MODE, key, ivNonce);

            return cipher.doFinal(input);

        } catch (final GeneralSecurityException e) {
            throw new CryptoException(e);
        }
    }

    @Override
    public void decrypt(final Key key, final InputStream input, final OutputStream output, final byte[] ivNonce) {

        try {
            final Cipher cipher = createCipherWithChecks(Cipher.DECRYPT_MODE, key, ivNonce);

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
    public byte[] decrypt(final Key key, final byte[] input, final byte[] iv) {

        try {
            
            final Cipher cipher = createCipherWithChecks(Cipher.DECRYPT_MODE, key, iv);

            return cipher.doFinal(input);

        } catch (final GeneralSecurityException e) {
            throw new CryptoException(e);
        }
    }

    @Override
    public String toString() {
        return algorithm;
    }
    
}
