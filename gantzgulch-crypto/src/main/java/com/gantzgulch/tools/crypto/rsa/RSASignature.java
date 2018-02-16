package com.gantzgulch.tools.crypto.rsa;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

import com.gantzgulch.tools.crypto.CryptoException;

public enum RSASignature {

    SHA1_RSA("SHA1withRSA"), //
    SHA256_RSA("SHA256withRSA"), //
    SHA384_RSA("SHA256withRSA"), //
    SHA512_RSA("SHA256withRSA");

    private String algorithm;

    private RSASignature(final String algorithm) {
        this.algorithm = algorithm;
    }

    public byte[] sign(final byte[] value, final PrivateKey privateKey) {

        try {
            final Signature signature = createSignature();

            signature.initSign(privateKey);

            signature.update(value);

            return signature.sign();

        } catch (final NoSuchAlgorithmException | NoSuchProviderException | InvalidKeyException | SignatureException e) {
            throw new CryptoException(e);
        }

    }

    public boolean verify(final byte[] signatureValue, final byte[] value, final PublicKey publicKey) {

        try {

            final Signature signature = createSignature();

            signature.initVerify(publicKey);

            signature.update(value);

            return signature.verify(signatureValue);

        } catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidKeyException | SignatureException e) {
            throw new CryptoException(e);
        }
    }

    private Signature createSignature() throws NoSuchAlgorithmException, NoSuchProviderException {
        return Signature.getInstance(algorithm, "BC");
    }

}
