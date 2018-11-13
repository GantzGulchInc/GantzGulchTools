package com.gantzgulch.tools.jwt;

import java.security.PrivateKey;
import java.security.PublicKey;

public class JWTKey {

    private final byte[] secret;
    private final PublicKey publicKey;
    private final PrivateKey privateKey;

    public JWTKey(final byte[] secret) {
        this(secret, null, null);
    }

    public JWTKey(final PublicKey publicKey) {
        this(null, publicKey, null);
    }

    public JWTKey(final PrivateKey privateKey) {
        this(null, null, privateKey);
    }

    public JWTKey(final byte[] secret, final PublicKey publicKey, final PrivateKey privateKey) {
        this.secret = secret;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public byte[] getSecret() {
        return secret;
    }
    
    public PublicKey getPublicKey() {
        return publicKey;
    }
    
    public PrivateKey getPrivateKey() {
        return privateKey;
    }
}
