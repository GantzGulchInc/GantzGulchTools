package com.gantzgulch.tools.common.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public enum HashAlgorithm {
    
    MD2("MD2"), //
    MD5("MD5"), //
    SHA_1("SHA-1"), //
    SHA_224("SHA-224"), //
    SHA_256("SHA-256"), //
    SHA_384("SHA-384"), //
    SHA_512("SHA-512");
    
    private final String algorithm;

    private HashAlgorithm(final String algorithm) {
        this.algorithm = algorithm;
    }
    
    public MessageDigest create() {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (final NoSuchAlgorithmException e) {
            throw new RuntimeException("Unable to create digest: " + algorithm, e);
        }
        
    }
    
}