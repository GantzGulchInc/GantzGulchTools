package com.gantzgulch.tools.crypto;

import com.gantzgulch.tools.crypto.alg.ec.ECSignature;
import com.gantzgulch.tools.crypto.alg.rsa.RSASignature;

public final class GGSignatures {

    public static final GGSignature SHA1_RSA = RSASignature.SHA1_RSA;
    public static final GGSignature SHA256_RSA = RSASignature.SHA256_RSA;
    public static final GGSignature SHA256_RSA_MGF1 = RSASignature.SHA256_RSA_MGF1;
    public static final GGSignature SHA384_RSA = RSASignature.SHA384_RSA;
    public static final GGSignature SHA384_RSA_MGF1 = RSASignature.SHA384_RSA_MGF1;
    public static final GGSignature SHA512_RSA = RSASignature.SHA512_RSA;
    public static final GGSignature SHA512_RSA_MGF1 = RSASignature.SHA512_RSA_MGF1;
    
    public static final GGSignature SHA1_ECDSA = ECSignature.SHA1_ECDSA;
    public static final GGSignature SHA256_ECDSA = ECSignature.SHA256_ECDSA;
    public static final GGSignature SHA384_ECDSA = ECSignature.SHA384_ECDSA;
    public static final GGSignature SHA512_ECDSA = ECSignature.SHA512_ECDSA;
    
    private GGSignatures() {
        throw new UnsupportedOperationException();
    }
}
