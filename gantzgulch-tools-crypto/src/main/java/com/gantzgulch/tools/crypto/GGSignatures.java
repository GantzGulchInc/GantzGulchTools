package com.gantzgulch.tools.crypto;

import com.gantzgulch.tools.crypto.alg.rsa.RSASignature;

public final class GGSignatures {

    public static final RSASignature SHA1_RSA = RSASignature.SHA1_RSA;
    public static final RSASignature SHA256_RSA = RSASignature.SHA256_RSA;
    public static final RSASignature SHA256_RSA_MGF1 = RSASignature.SHA256_RSA_MGF1;
    public static final RSASignature SHA384_RSA = RSASignature.SHA384_RSA;
    public static final RSASignature SHA384_RSA_MGF1 = RSASignature.SHA384_RSA_MGF1;
    public static final RSASignature SHA512_RSA = RSASignature.SHA512_RSA;
    public static final RSASignature SHA512_RSA_MGF1 = RSASignature.SHA512_RSA_MGF1;
    
    private GGSignatures() {
        throw new UnsupportedOperationException();
    }
}
