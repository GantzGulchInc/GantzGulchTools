package com.gantzgulch.tools.crypto.alg.ec;

import java.util.ArrayList;
import java.util.List;

import com.gantzgulch.tools.crypto.alg.impl.AbstractGGSignature;

public class ECSignature extends AbstractGGSignature {

    public static final List<ECSignature> SIGNATURES = new ArrayList<>();
    
    public static final ECSignature ECDSA = new ECSignature("ECDSA");

    private ECSignature(final String algorithm) {
        
        super(algorithm);
        
        SIGNATURES.add(this);
    }

}
