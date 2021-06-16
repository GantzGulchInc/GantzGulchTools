package com.gantzgulch.tools.crypto.alg.ec;

import java.security.KeyPair;
import java.security.spec.ECGenParameterSpec;

import org.junit.Test;

import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.tools.common.codec.GGHex;
import com.gantzgulch.tools.common.lang.GGStrings;

public class ECSignatureTest {

    private static final GGLogger LOG = GGLogger.getLogger(ECSignatureTest.class);
    
    private static final byte[] INPUT = GGStrings.toBytes("This is a test message.");
    
    public ECSignatureTest() {
    }

    
    @Test
    public void testSignature() {
        
        final ECGenParameterSpec ecGenSpec = new ECGenParameterSpec("prime192v1");
        
        final KeyPair kp = ECKeyGenerator.generate( ecGenSpec ); 
        
        
        final byte[] signature = ECSignature.SHA1_ECDSA.sign(kp.getPrivate(), INPUT);
        
        LOG.info("testSignature: signature: %s", GGHex.toHexString(signature));
        
        final boolean verified = ECSignature.SHA1_ECDSA.verify(kp.getPublic(), signature, INPUT);
        
        LOG.info("testSignature: verified: %s", verified);
    }
}
