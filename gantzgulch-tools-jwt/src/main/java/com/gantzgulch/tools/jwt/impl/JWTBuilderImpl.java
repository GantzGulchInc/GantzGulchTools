package com.gantzgulch.tools.jwt.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.gantzgulch.tools.jwt.JWTBuilder;

public class JWTBuilderImpl implements JWTBuilder {

    private final Algorithm alg;

    private final Map<String,Object> headerClaims = new HashMap<>();
    
    private final Builder b;

    public JWTBuilderImpl(final Algorithm alg) {
        this.alg = alg;
        this.b = JWT.create();
    }

    public JWTBuilder withKeyId(String keyId) {
        b.withKeyId(keyId);
        return this;
    }

    public JWTBuilder withIssuer(String issuer) {
        b.withIssuer(issuer);
        return this;
    }

    public JWTBuilder withSubject(String subject) {
        b.withSubject(subject);
        return this;
    }

    public JWTBuilder withAudience(String... audience) {
        b.withAudience(audience);
        return this;
    }

    public JWTBuilder withExpiresAt(Date expiresAt) {
        b.withExpiresAt(expiresAt);
        return this;
    }

    public JWTBuilder withNotBefore(Date notBefore) {
        b.withNotBefore(notBefore);
        return this;
    }

    public JWTBuilder withIssuedAt(Date issuedAt) {
        b.withIssuedAt(issuedAt);
        return this;
    }

    public JWTBuilder withJWTId(String jwtId) {
        b.withJWTId(jwtId);
        return this;
    }

    public JWTBuilder withClaim(String name, Boolean value) throws IllegalArgumentException {
        b.withClaim(name, value);
        return this;
    }

    public JWTBuilder withClaim(String name, Integer value) throws IllegalArgumentException {
        b.withClaim(name, value);
        return this;
    }

    public JWTBuilder withClaim(String name, Long value) throws IllegalArgumentException {
        b.withClaim(name, value);
        return this;
    }

    public JWTBuilder withClaim(String name, Double value) throws IllegalArgumentException {
        b.withClaim(name, value);
        return this;
    }

    public JWTBuilder withClaim(String name, String value) throws IllegalArgumentException {
        b.withClaim(name, value);
        return this;
    }

    public JWTBuilder withClaim(String name, Date value) throws IllegalArgumentException {
        b.withClaim(name, value);
        return this;
    }

    public JWTBuilder withArrayClaim(String name, String[] items) throws IllegalArgumentException {
        b.withArrayClaim(name, items);
        return this;
    }

    public JWTBuilder withArrayClaim(String name, Integer[] items) throws IllegalArgumentException {
        b.withArrayClaim(name, items);
        return this;
    }

    public JWTBuilder withArrayClaim(String name, Long[] items) throws IllegalArgumentException {
        b.withArrayClaim(name, items);
        return this;
    }

    public String createAndSign() {

        b.withHeader(headerClaims);
        
        return b.sign(alg);

    }

}
