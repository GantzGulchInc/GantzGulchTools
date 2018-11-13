package com.gantzgulch.tools.jwt;

import java.util.Arrays;
import java.util.List;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.gantzgulch.tools.jwt.impl.JWTVerifierImpl;

public interface JWTVerifier {

    DecodedJWT verifyAndDecode(String token);

    static JWTVerifier create(final long leewaySeconds, final List<JWTKey> keys) {
        
        return new JWTVerifierImpl(leewaySeconds, keys);
        
    }

    static JWTVerifier create(final long leewaySeconds, final JWTKey ... keys) {
        
        return new JWTVerifierImpl(leewaySeconds, Arrays.asList(keys));
        
    }
}
