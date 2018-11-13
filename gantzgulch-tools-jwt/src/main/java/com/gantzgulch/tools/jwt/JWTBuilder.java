package com.gantzgulch.tools.jwt;

import java.security.PrivateKey;
import java.util.Date;

import com.gantzgulch.tools.jwt.impl.JWTAlgorithm;
import com.gantzgulch.tools.jwt.impl.JWTBuilderImpl;

public interface JWTBuilder {

    JWTBuilder withKeyId(String keyId);

    JWTBuilder withIssuer(String issuer);

    JWTBuilder withSubject(String subject);

    JWTBuilder withAudience(String... audience);

    JWTBuilder withExpiresAt(Date expiresAt);

    JWTBuilder withNotBefore(Date notBefore);

    JWTBuilder withIssuedAt(Date issuedAt);

    JWTBuilder withJWTId(String jwtId);

    JWTBuilder withClaim(String name, Boolean value) throws IllegalArgumentException;

    JWTBuilder withClaim(String name, Integer value) throws IllegalArgumentException;

    JWTBuilder withClaim(String name, Long value) throws IllegalArgumentException;

    JWTBuilder withClaim(String name, Double value) throws IllegalArgumentException;

    JWTBuilder withClaim(String name, String value) throws IllegalArgumentException;

    JWTBuilder withClaim(String name, Date value) throws IllegalArgumentException;

    JWTBuilder withArrayClaim(String name, String[] items) throws IllegalArgumentException;

    JWTBuilder withArrayClaim(String name, Integer[] items) throws IllegalArgumentException;

    JWTBuilder withArrayClaim(String name, Long[] items) throws IllegalArgumentException;

    String createAndSign();

    static JWTBuilder create(final JWTAlgorithm alg, final byte[] secret) {

        return new JWTBuilderImpl( alg.create(secret) );
    }

    static JWTBuilder create(final JWTAlgorithm alg, final PrivateKey privateKey) {
        
        return new JWTBuilderImpl( alg.create(null, privateKey) );
    }
}
