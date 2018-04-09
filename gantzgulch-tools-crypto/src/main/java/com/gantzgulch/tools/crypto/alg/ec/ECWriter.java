package com.gantzgulch.tools.crypto.alg.ec;

import java.security.KeyPair;
import java.security.PublicKey;

import com.gantzgulch.tools.common.lang.GGArgs;
import com.gantzgulch.tools.crypto.BouncyCastleState;
import com.gantzgulch.tools.crypto.alg.impl.GGKeyPairs;
import com.gantzgulch.tools.crypto.pem.PEMWriter;

public final class ECWriter {

    private static final String ALGORITHM = "ECDSA";
    
    static {
        BouncyCastleState.init();
    }

    private ECWriter() {
        throw new UnsupportedOperationException();
    }

    public static String writeToPEM(final KeyPair keyPair) {

        GGArgs.notNull(keyPair, "keyPair");

        GGKeyPairs.verifyAlgorithm(keyPair, ALGORITHM);
        
        return PEMWriter.write(keyPair);

    }

    public static String writeToPEM(final PublicKey publicKey) {

        GGArgs.notNull(publicKey, "publicKey");

        GGKeyPairs.verifyAlgorithm(publicKey, ALGORITHM);
        
        return PEMWriter.write(publicKey);

    }

}
