package com.gantzgulch.tools.crypto.alg.impl;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

import com.gantzgulch.tools.crypto.BouncyCastleState;
import com.gantzgulch.tools.crypto.GGSignature;
import com.gantzgulch.tools.crypto.exception.CryptoException;

public abstract class AbstractGGSignature implements GGSignature {

    static {
        BouncyCastleState.init();
    }

    protected final String algorithm;
    
    public AbstractGGSignature(final String algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public String getAlgorithm() {
        return algorithm;
    }
    
    protected Signature createSignature(final PrivateKey key) throws GeneralSecurityException {

        final Signature sig = Signature.getInstance(algorithm, "BC");

        sig.initSign(key);
        
        return sig;
    }
    
    protected Signature createSignature(final PublicKey key) throws GeneralSecurityException {

        final Signature sig = Signature.getInstance(algorithm, "BC");

        sig.initVerify(key);
        
        return sig;
    }
    

    @Override
    public byte[] sign(final PrivateKey key, final InputStream input) {

        try {
            final Signature cipher = createSignature(key);

            final byte[] buffer = new byte[64 * 1024];

            int len = 0;

            while ((len = input.read(buffer)) > 0) {

                cipher.update(buffer, 0, len);

            }

            return cipher.sign();

        } catch (final GeneralSecurityException | IOException e) {
            throw new CryptoException(e);
        }

    }

    @Override
    public byte[] sign(final PrivateKey key, final byte[] input) {

        try {
            final Signature cipher = createSignature(key);

            cipher.update(input);
            
            return cipher.sign();

        } catch (final GeneralSecurityException e) {
            throw new CryptoException(e);
        }
    }

    @Override
    public boolean verify(final PublicKey key, final byte[] signature, final InputStream input) {

        try {
            final Signature cipher = createSignature(key);

            final byte[] buffer = new byte[64 * 1024];

            int len = 0;

            while ((len = input.read(buffer)) > 0) {

                cipher.update(buffer, 0, len);

            }

            return cipher.verify(signature);

        } catch (final GeneralSecurityException | IOException e) {
            throw new CryptoException(e);
        }

    }

    @Override
    public boolean verify(final PublicKey key, final byte[] signature, final byte[] input) {

        try {
            final Signature cipher = createSignature(key);

            cipher.update(input);
            
            return cipher.verify(signature);

        } catch (final GeneralSecurityException e) {
            throw new CryptoException(e);
        }
    }

    @Override
    public String toString() {
        return algorithm;
    }


}
