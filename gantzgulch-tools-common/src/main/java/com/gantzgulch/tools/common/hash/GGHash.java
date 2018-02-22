package com.gantzgulch.tools.common.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.gantzgulch.tools.common.codec.GGBase64;
import com.gantzgulch.tools.common.codec.GGHex;

public enum GGHash {

    MD2("MD2"), //
    MD5("MD5"), //
    SHA_1("SHA-1"), //
    SHA_224("SHA-224"), //
    SHA_256("SHA-256"), //
    SHA_384("SHA-384"), //
    SHA_512("SHA-512"), //
    SHA3_256("SHA3-256"), //
    SHA3_384("SHA3-384"), //
    SHA3_512("SHA3-512");

    private final String algorithm;

    private GGHash(final String algorithm) {
        this.algorithm = algorithm;
    }

    public byte[] hash(final byte[] input) {

        if (input == null) {
            return null;
        }

        final MessageDigest digest = create();

        return digest.digest(input);
    }

    public String hashToHexString(final byte[] input) {

        return GGHex.toHexString(hash(input));
    }

    public String hashToBase64String(final byte[] input) {

        return GGBase64.toBase64String(hash(input), false);
    }

    public String hashToBase64UrlString(final byte[] input) {

        return GGBase64.toBase64String(hash(input), true);
    }

    private MessageDigest create() {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (final NoSuchAlgorithmException e) {
            throw new RuntimeException("Unable to create digest: " + algorithm, e);
        }

    }

}