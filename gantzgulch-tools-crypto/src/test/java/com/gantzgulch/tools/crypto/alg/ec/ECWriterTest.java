package com.gantzgulch.tools.crypto.alg.ec;

import java.security.KeyPair;
import java.security.PublicKey;
import java.security.spec.ECGenParameterSpec;

import org.junit.Test;

import com.gantzgulch.tools.common.logging.GGLogger;

public class ECWriterTest {

    private static final GGLogger LOG = GGLogger.getLogger(ECWriterTest.class);
    
    private ECReader ecReader = new ECReader();
    
    public ECWriterTest() {
    }

    
    @Test
    public void testWrite() {
        
        final ECGenParameterSpec ecGenSpec = new ECGenParameterSpec("prime192v1");
        
        final KeyPair kp = ECKeyGenerator.generate( ecGenSpec ); 
        
        final String key = ECWriter.writeToPEM(kp);
        
        LOG.info("testWrite: key: \n%s", key);
        
        final String publicKey = ECWriter.writeToPEM(kp.getPublic());
        
        LOG.info("testWrite: publicKey: \n%s", publicKey);
    }


    @Test
    public void testRead() {
        
        final ECGenParameterSpec ecGenSpec = new ECGenParameterSpec("prime192v1");
        
        final KeyPair kp = ECKeyGenerator.generate( ecGenSpec ); 
        
        final String key = ECWriter.writeToPEM(kp);
        final String publicKey = ECWriter.writeToPEM(kp.getPublic());

        
        final KeyPair actualKeyPair = ecReader.readKeyPair(key);
        
        final PublicKey acutalPublicKey = ecReader.readPublicKey(publicKey);
        
        
        LOG.info("testRead: actualKeyPair: %s", actualKeyPair);
        LOG.info("testRead: actualPublicKey: %s", acutalPublicKey);
        
        
    }

}
