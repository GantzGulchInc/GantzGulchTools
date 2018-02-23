package com.gantzgulch.tools.common.hash.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.gantzgulch.tools.common.codec.GGBase64;
import com.gantzgulch.tools.common.codec.GGHex;
import com.gantzgulch.tools.common.hash.GGHash;

public final class GGHashImpl implements GGHash {

    public static final List<GGHash> HASHES = new ArrayList<>(); 
            
    public static final GGHash MD2 = new GGHashImpl("MD2");
    public static final GGHash MD5 = new GGHashImpl("MD5");
    public static final GGHash SHA_1 = new GGHashImpl("SHA-1");
    public static final GGHash SHA_224 = new GGHashImpl("SHA-224");
    public static final GGHash SHA_256 = new GGHashImpl("SHA-256");
    public static final GGHash SHA_384 = new GGHashImpl("SHA-384");
    public static final GGHash SHA_512 = new GGHashImpl("SHA-512");
    public static final GGHash SHA3_256 = new GGHashImpl("SHA3-256");
    public static final GGHash SHA3_384 = new GGHashImpl("SHA3-384");
    public static final GGHash SHA3_512 = new GGHashImpl("SHA3-512");

    private final String algorithm;

    private GGHashImpl(final String algorithm) {
        this.algorithm = algorithm;
        HASHES.add(this);
    }

    @Override
    public byte[] hash(final byte[] input) {

        if (input == null) {
            return null;
        }

        final MessageDigest digest = createMessageDigest();

        return digest.digest(input);
    }

    @Override
    public String hashToHexString(final byte[] input) {

        return GGHex.toHexString(hash(input));
    }

    @Override
    public String hashToBase64String(final byte[] input) {

        return GGBase64.toBase64String(hash(input), false);
    }

    @Override
    public String hashToBase64UrlString(final byte[] input) {

        return GGBase64.toBase64String(hash(input), true);
    }

    @Override
    public String toString() {
        return algorithm;
    }
    
    private MessageDigest createMessageDigest() {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (final NoSuchAlgorithmException e) {
            throw new RuntimeException("Unable to create digest: " + algorithm, e);
        }

    }

}