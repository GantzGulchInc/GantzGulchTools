package com.gantzgulch.tools.jwt.domain;

import java.security.PublicKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPublicKey;

import com.auth0.jwt.algorithms.Algorithm;
import com.google.common.base.Objects;

public enum JWTAlgorithm {

    RS256("RS256", new RS256Creator()),
    RS384("RS384", new RS384Creator()),
    RS512("RS512", new RS512Creator()),
    ES256("ES256", new ECDSA256Creator()),
    ES384("ES384", new ECDSA384Creator()),
    ES512("ES512", new ECDSA512Creator());
    
    private final String alg;
    private final AlgorithmCreator creator;
    
    private JWTAlgorithm(final String alg, final AlgorithmCreator creator){
        this.alg = alg;
        this.creator = creator;
    }
    
    public String getAlg() {
        return alg;
    }
    
    public Algorithm create(final PublicKey key) {
        return creator.create(key);
    }
    
    public static JWTAlgorithm find(final String alg){
        
        for(JWTAlgorithm a : values()){
            
            if( Objects.equal(a.alg, alg) ){
                return a;
            }
        }
        
        return null;
    }
    
    public static interface AlgorithmCreator {
        
        Algorithm create(PublicKey key);
        
    }
    
    private static class RS256Creator implements AlgorithmCreator {
        @Override
        public Algorithm create(final PublicKey key) {
            return Algorithm.RSA256((RSAPublicKey) key, null);
        }
    }

    private static class RS384Creator implements AlgorithmCreator {
        @Override
        public Algorithm create(final PublicKey key) {
            return Algorithm.RSA384((RSAPublicKey) key, null);
        }
    }
    
    private static class RS512Creator implements AlgorithmCreator {
        @Override
        public Algorithm create(final PublicKey key) {
            return Algorithm.RSA512((RSAPublicKey) key, null);
        }
    }
    
    private static class ECDSA256Creator implements AlgorithmCreator {
        @Override
        public Algorithm create(final PublicKey key) {
            return Algorithm.ECDSA256((ECPublicKey) key, null);
        }
    }

    private static class ECDSA384Creator implements AlgorithmCreator {
        @Override
        public Algorithm create(final PublicKey key) {
            return Algorithm.ECDSA384((ECPublicKey) key, null);
        }
    }
    
    private static class ECDSA512Creator implements AlgorithmCreator {
        @Override
        public Algorithm create(final PublicKey key) {
            return Algorithm.ECDSA512((ECPublicKey) key, null);
        }
    }
    
}
