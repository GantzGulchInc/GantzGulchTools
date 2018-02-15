package com.gantzgulch.tools.crypto.rsa;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Signature;

public enum RSASignatureAlgorithm {

    SHA1_RSA("SHA1withRSA"),
    SHA256_RSA("SHA256withRSA"),
    SHA384_RSA("SHA256withRSA"),
    SHA512_RSA("SHA256withRSA");
    
    private String algorithm;

    private RSASignatureAlgorithm(final String algorithm) {
        this.algorithm = algorithm;
    }
    
    public Signature createSignature() throws NoSuchAlgorithmException, NoSuchProviderException {
        return Signature.getInstance(algorithm, "BC");
    }
}
