package com.gantzgulch.tools.crypto.rsa;

import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

import com.gantzgulch.tools.crypto.BouncyCastleState;

public class RSASigner {

    static {
        BouncyCastleState.init();
    }

    public RSASigner() {
    }

    public byte[] sign(final RSASignatureAlgorithm alg, final byte[] value, final PrivateKey privateKey) throws GeneralSecurityException {
        
        final Signature signature = alg.createSignature();
        
        signature.initSign(privateKey);
        
        signature.update(value);
        
        return signature.sign();
        
    }
    
    public boolean verify(final RSASignatureAlgorithm alg, final byte[] signatureValue, final byte[] value, final PublicKey publicKey) throws GeneralSecurityException {
        
        final Signature signature = alg.createSignature();
        
        signature.initVerify(publicKey);
        
        signature.update(value);
        
        return signature.verify(signatureValue);
        
    }
    
}
