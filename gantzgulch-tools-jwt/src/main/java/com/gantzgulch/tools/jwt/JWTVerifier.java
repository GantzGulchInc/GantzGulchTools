package com.gantzgulch.tools.jwt;

import java.security.PublicKey;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.text.AbstractDocument.LeafElement;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.gantzgulch.tools.common.logging.GGLogger;
import com.gantzgulch.tools.jwt.domain.JWTAlgorithm;

public class JWTVerifier {

    private static final GGLogger LOG = GGLogger.getLogger(JWTVerifier.class);
    
    private final long leeway;
    
    public JWTVerifier(final long leeway) {
        this.leeway = leeway;
    }

    
    public DecodedJWT verifyAndDecode(final String token, final List<PublicKey> keys ) {
        
        final JWTAlgorithm alg = getAlgorithm(token);
        
        DecodedJWT realJwt = null;
        
        for(final PublicKey attemptKey : keys) {

            realJwt = attemptVerify(alg, attemptKey, token);
            
            if( realJwt != null ){
                return realJwt;
            }
            
        }
        
        throw new JWTVerificationException("Unable to verify token with provided keys.");
    }
    
    private JWTAlgorithm getAlgorithm(final String token) {
        
        final DecodedJWT jwt = com.auth0.jwt.JWT.decode(token);
        
        final JWTAlgorithm alg = JWTAlgorithm.find(jwt.getAlgorithm());
        
        if( alg == null ) {
            throw new JWTVerificationException("Missing or unknown alg: " + jwt.getAlgorithm());
        }
        
        return alg;
    }
    
    private DecodedJWT attemptVerify(final JWTAlgorithm alg, final PublicKey key, final String token) {
        
        try {
            
            com.auth0.jwt.JWTVerifier verifier = buildVerifier(alg,  key);
                    
            DecodedJWT realJwt = verifier.verify(token);
            
            return realJwt;
            
        }catch(final RuntimeException e) {
            LOG.debug(e, "attemptVerify: failed: %s",e);
        }
        
        return null;
    }
    
    private com.auth0.jwt.JWTVerifier buildVerifier(final JWTAlgorithm alg, final PublicKey key) {

        final Algorithm a = alg.create(key);
        
        Verification v = JWT.require(a);  //.acceptLeeway(500000000).build();

        v = v.acceptLeeway(leeway);
        
        return  v.build();
    }

}
