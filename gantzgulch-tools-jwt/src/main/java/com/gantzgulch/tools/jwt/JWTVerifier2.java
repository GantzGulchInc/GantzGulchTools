package com.gantzgulch.tools.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.gantzgulch.tools.jwt.impl.JWTVerifier2Impl;

import java.security.PublicKey;
import java.util.Map;

public interface JWTVerifier2 {

    DecodedJWT verifyAndDecode(String token);

    static JWTVerifier2 create(final long leewaySeconds, final Map<String, PublicKey> keys) {
        
        return new JWTVerifier2Impl(leewaySeconds, keys);

    }

}
