package com.gantzgulch.tools.crypto.alg.ec;

import java.util.ArrayList;
import java.util.List;

import com.gantzgulch.tools.crypto.alg.impl.AbstractGGSignature;

public class ECSignature extends AbstractGGSignature {

    public static final List<ECSignature> SIGNATURES = new ArrayList<>();
    
    public static final ECSignature SHA1_ECDSA = new ECSignature("SHA1withECDSA");
    public static final ECSignature SHA256_ECDSA = new ECSignature("SHA256withECDSA");
    
    private ECSignature(final String algorithm) {
        
        super(algorithm);
        
        SIGNATURES.add(this);
    }

}
