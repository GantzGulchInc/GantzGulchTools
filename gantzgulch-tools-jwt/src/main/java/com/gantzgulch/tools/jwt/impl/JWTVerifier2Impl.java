package com.gantzgulch.tools.jwt.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.tools.common.lang.GGPair;
import com.gantzgulch.tools.jwt.JWTVerifier2;

import java.security.PublicKey;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class JWTVerifier2Impl implements JWTVerifier2 {

    private static final GGLogger LOG = GGLogger.getLogger(JWTVerifier2Impl.class);

    private final long leewaySeconds;

    private final Map<String, PublicKey> keys;

    public JWTVerifier2Impl(final long leewaySeconds, final Map<String, PublicKey> keys) {
        this.leewaySeconds = leewaySeconds;
        this.keys = keys != null ? new HashMap<>(keys) : Collections.emptyMap();
    }

    public DecodedJWT verifyAndDecode(final String token) {

        final GGPair<String, JWTAlgorithm> keyAndAlg = getKeyIdAndAlgorithm(token);

        final String keyId = keyAndAlg.getKey();
        final JWTAlgorithm alg = keyAndAlg.getValue();
        final PublicKey publicKey = keys.get(keyId);

        if (publicKey == null) {
            throw new JWTVerificationException("Unknown key ID: " + keyAndAlg.getKey());
        }

        final DecodedJWT realJwt = attemptVerify(keyAndAlg.getValue(), publicKey, token);

        if (realJwt == null) {
            throw new JWTVerificationException("Unable to verify token with provided key ID.");
        }

        return realJwt;
    }

    private GGPair<String, JWTAlgorithm> getKeyIdAndAlgorithm(final String token) {

        final DecodedJWT jwt = JWT.decode(token);

        final JWTAlgorithm alg = JWTAlgorithm.find(jwt.getAlgorithm());
        final String keyId = jwt.getKeyId();

        if (alg == null) {
            throw new JWTVerificationException("Missing or unknown alg: " + jwt.getAlgorithm());
        }

        return new GGPair<>(keyId, alg);
    }

    private DecodedJWT attemptVerify(final JWTAlgorithm alg, final PublicKey publicKey, final String token) {

        try {

            com.auth0.jwt.JWTVerifier verifier = buildVerifier(alg, publicKey);

            return verifier.verify(token);

        } catch (final RuntimeException e) {
            LOG.debug(e, "attemptVerify: failed: %s", e);
        }

        return null;
    }

    private com.auth0.jwt.JWTVerifier buildVerifier(final JWTAlgorithm alg, final PublicKey publicKey) {

        final Algorithm a = alg.create(null, publicKey, null);

        return JWT.require(a)
                .acceptLeeway(leewaySeconds)
                .build();
    }

}
