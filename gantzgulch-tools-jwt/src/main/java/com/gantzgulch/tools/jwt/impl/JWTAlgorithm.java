package com.gantzgulch.tools.jwt.impl;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;

import com.auth0.jwt.algorithms.Algorithm;
import com.google.common.base.Objects;

public enum JWTAlgorithm {

    HS256("HS256", new HS256Creator()), //
    HS384("HS384", new HS384Creator()), //
    HS512("HS512", new HS512Creator()), //
    RS256("RS256", new RS256Creator()), //
    RS384("RS384", new RS384Creator()), //
    RS512("RS512", new RS512Creator()), //
    ES256("ES256", new ECDSA256Creator()), //
    ES384("ES384", new ECDSA384Creator()), //
    ES512("ES512", new ECDSA512Creator());

    private final String alg;

    private final AlgorithmCreator creator;

    private JWTAlgorithm(final String alg, final AlgorithmCreator creator) {
        this.alg = alg;
        this.creator = creator;
    }

    public String getAlg() {
        return alg;
    }

    public Algorithm create(final byte[] key) {
        return create(key, null, null);
    }

    public Algorithm create(final PublicKey publicKey, final PrivateKey privateKey) {
        return create(null, publicKey, privateKey);
    }

    public Algorithm create(final byte[] secret, final PublicKey publicKey, final PrivateKey privateKey) {
        return creator.create(secret, publicKey, privateKey);
    }

    public static JWTAlgorithm find(final String alg) {

        return Arrays //
                .stream(values()) //
                .filter((p) -> {
                    return Objects.equal(p.alg, alg);
                }) //
                .findFirst() //
                .orElse(null);

    }

    public static interface AlgorithmCreator {

        Algorithm create(byte[] secret, PublicKey publicKey, PrivateKey privateKey);

    }

    private static class HS256Creator implements AlgorithmCreator {

        @Override
        public Algorithm create(final byte[] secret, final PublicKey publicKey, final PrivateKey privateKey) {
            return Algorithm.HMAC256(secret);
        }
    }

    private static class HS384Creator implements AlgorithmCreator {

        @Override
        public Algorithm create(final byte[] secret, final PublicKey publicKey, final PrivateKey privateKey) {
            return Algorithm.HMAC384(secret);
        }
    }

    private static class HS512Creator implements AlgorithmCreator {

        @Override
        public Algorithm create(final byte[] secret, final PublicKey publicKey, final PrivateKey privateKey) {
            return Algorithm.HMAC512(secret);
        }
    }

    private static class RS256Creator implements AlgorithmCreator {

        @Override
        public Algorithm create(final byte[] secret, final PublicKey publicKey, final PrivateKey privateKey) {
            return Algorithm.RSA256((RSAPublicKey) publicKey, (RSAPrivateKey) privateKey);
        }
    }

    private static class RS384Creator implements AlgorithmCreator {

        @Override
        public Algorithm create(final byte[] secret, final PublicKey publicKey, final PrivateKey privateKey) {
            return Algorithm.RSA384((RSAPublicKey) publicKey, (RSAPrivateKey) privateKey);
        }
    }

    private static class RS512Creator implements AlgorithmCreator {

        @Override
        public Algorithm create(final byte[] secret, final PublicKey publicKey, final PrivateKey privateKey) {
            return Algorithm.RSA512((RSAPublicKey) publicKey, (RSAPrivateKey) privateKey);
        }
    }

    private static class ECDSA256Creator implements AlgorithmCreator {

        @Override
        public Algorithm create(final byte[] secret, final PublicKey publicKey, final PrivateKey privateKey) {
            return Algorithm.ECDSA256((ECPublicKey) publicKey, (ECPrivateKey) privateKey);
        }
    }

    private static class ECDSA384Creator implements AlgorithmCreator {

        @Override
        public Algorithm create(final byte[] secret, final PublicKey publicKey, final PrivateKey privateKey) {
            return Algorithm.ECDSA384((ECPublicKey) publicKey, (ECPrivateKey) privateKey);
        }
    }

    private static class ECDSA512Creator implements AlgorithmCreator {

        @Override
        public Algorithm create(final byte[] secret, final PublicKey publicKey, final PrivateKey privateKey) {
            return Algorithm.ECDSA512((ECPublicKey) publicKey, (ECPrivateKey) privateKey);
        }
    }

}
