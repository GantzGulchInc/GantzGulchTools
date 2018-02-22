package com.gantzgulch.tools.crypto.alg.rsa;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyPair;

import org.junit.Test;

import com.gantzgulch.tools.common.codec.GGHex;
import com.gantzgulch.tools.common.lang.GGStrings;
import com.gantzgulch.tools.common.logging.GGLogger;
import com.gantzgulch.tools.crypto.alg.rsa.RSAKeyGenerator;
import com.gantzgulch.tools.crypto.alg.rsa.RSASignature;

public class RSASignatureTest {

    private static final GGLogger LOG = GGLogger.getLogger(RSASignatureTest.class);
    
    private final byte[] VALUE = GGStrings.toBytes("This is a random set of data 0123456789abcdefghijklmnopqrstuvwxyz");

    private final KeyPair KEY_PAIR = RSAKeyGenerator.generate(2048);

    @Test
    public void testAll() throws IOException, GeneralSecurityException {
        
        for(final RSASignature sig : RSASignature.SIGNATURES) {

            signAndVerify(sig);
            signAndVerify_badSignature(sig);
            
        }
    }
    
    public void signAndVerify(final RSASignature sig) throws IOException, GeneralSecurityException {

        LOG.debug("signAndVerify: Algorithm: %s", sig.getAlgorithm());
        
        final byte[] signature = sig.sign(KEY_PAIR.getPrivate(), VALUE);

        assertThat(signature, notNullValue());

        LOG.debug("signAndVerify: signature: %s", GGHex.toHexString(signature));
        
        final boolean verify = sig.verify(KEY_PAIR.getPublic(), signature, VALUE);

        assertThat(verify, is(true));

    }

    public void signAndVerify_badSignature(final RSASignature sig) throws IOException, GeneralSecurityException {

        LOG.debug("signAndVerify_badSignature: Algorithm: %s", sig.getAlgorithm());
        
        final byte[] signature = sig.sign(KEY_PAIR.getPrivate(), VALUE);

        assertThat(signature, notNullValue());

        signature[4] = (byte)(signature[4] ^ 0xFF);
        
        final boolean verify = sig.verify(KEY_PAIR.getPublic(), signature, VALUE);

        assertThat(verify, is(false));

    }

}
