package com.gantzgulch.tools.jwt.impl;

import java.util.List;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.tools.jwt.JWTKey;
import com.gantzgulch.tools.jwt.JWTVerifier;

public class JWTVerifierImpl implements JWTVerifier {

    private static final GGLogger LOG = GGLogger.getLogger(JWTVerifierImpl.class);

    private final long leewaySeconds;

    private final List<JWTKey> keys;

    public JWTVerifierImpl(final long leewaySeconds, final List<JWTKey> keys) {
        this.leewaySeconds = leewaySeconds;
        this.keys = keys;
    }

    public DecodedJWT verifyAndDecode(final String token) {

        final JWTAlgorithm alg = getAlgorithm(token);

        DecodedJWT realJwt = null;

        for (final JWTKey key : keys) {

            realJwt = attemptVerify(alg, key, token);

            if (realJwt != null) {
                return realJwt;
            }

        }

        throw new JWTVerificationException("Unable to verify token with provided keys.");
    }

    private JWTAlgorithm getAlgorithm(final String token) {

        final DecodedJWT jwt = JWT.decode(token);

        final JWTAlgorithm alg = JWTAlgorithm.find(jwt.getAlgorithm());

        if (alg == null) {
            throw new JWTVerificationException("Missing or unknown alg: " + jwt.getAlgorithm());
        }

        return alg;
    }

    private DecodedJWT attemptVerify(final JWTAlgorithm alg, final JWTKey key, final String token) {

        try {

            com.auth0.jwt.JWTVerifier verifier = buildVerifier(alg, key);

            DecodedJWT realJwt = verifier.verify(token);

            return realJwt;

        } catch (final RuntimeException e) {
            LOG.debug(e, "attemptVerify: failed: %s", e);
        }

        return null;
    }

    private com.auth0.jwt.JWTVerifier buildVerifier(final JWTAlgorithm alg, final JWTKey key) {

        final Algorithm a = alg.create(key.getSecret(), key.getPublicKey(), key.getPrivateKey());

        Verification v = JWT.require(a);

        v = v.acceptLeeway(leewaySeconds);

        return v.build();
    }

}
