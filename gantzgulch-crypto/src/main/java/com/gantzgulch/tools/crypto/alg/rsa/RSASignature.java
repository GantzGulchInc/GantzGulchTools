package com.gantzgulch.tools.crypto.alg.rsa;

import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.ArrayList;
import java.util.List;

import com.gantzgulch.tools.crypto.alg.AbstractGGSignature;

public class RSASignature extends AbstractGGSignature {

    public static final List<RSASignature> SIGNATURES = new ArrayList<>();
    
    public static final RSASignature SHA1_RSA = new RSASignature("SHA1withRSA");
    public static final RSASignature SHA256_RSA = new RSASignature("SHA256withRSA");
    public static final RSASignature SHA256_RSA_MGF1 = new RSASignature("SHA256withRSAandMGF1");
    public static final RSASignature SHA384_RSA = new RSASignature("SHA384withRSA");
    public static final RSASignature SHA384_RSA_MGF1 = new RSASignature("SHA384withRSAandMGF1");
    public static final RSASignature SHA512_RSA = new RSASignature("SHA512withRSA");
    public static final RSASignature SHA512_RSA_MGF1 = new RSASignature("SHA512withRSAandMGF1");

    private RSASignature(final String algorithm) {
        
        super(algorithm);
        
        SIGNATURES.add(this);
    }

    @Override
    protected Signature createSignature(final PrivateKey key) throws GeneralSecurityException {

        final Signature sig = Signature.getInstance(algorithm, "BC");

        sig.initSign(key);
        
        return sig;
    }
    
    @Override
    protected Signature createSignature(final PublicKey key) throws GeneralSecurityException {

        final Signature sig = Signature.getInstance(algorithm, "BC");

        sig.initVerify(key);
        
        return sig;
    }
    
}
