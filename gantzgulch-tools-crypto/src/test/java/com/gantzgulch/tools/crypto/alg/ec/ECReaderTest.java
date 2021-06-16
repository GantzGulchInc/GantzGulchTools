package com.gantzgulch.tools.crypto.alg.ec;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

import org.junit.Test;

import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.tools.common.codec.GGHex;
import com.gantzgulch.tools.common.lang.GGStrings;
import com.gantzgulch.tools.crypto.GGSignatures;
import com.gantzgulch.tools.crypto.pem.PEMReader;
import com.gantzgulch.tools.crypto.pem.PEMWriter;

public class ECReaderTest {

    private static final GGLogger LOG = GGLogger.getLogger(ECReaderTest.class);
    
    private ECReader ecReader = new ECReader();
    
    public ECReaderTest() {
    }

    
    @Test
    public void testRead() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, InvalidKeyException, SignatureException {

        final byte[] pubKeyRaw = GGHex.fromHexString("04681d5cedced95395a714748b506c9413d5d4ef5684ae55acad84daf2318707bd9b42836d6c9b6f3b25cba9e3983a3f1c6b98f0316009713d5c5c34cb564615fe");
        
        final PublicKey publicKey = ecReader.readPublicKeyX963("secp256r1", pubKeyRaw);
        
        
        
        
        final byte[] signature =  GGHex.fromHexString("304502202bfd4062195e8c37548db5e44f3b5e61a4888558278f2383ef8e4c7bee9e1a91022100debcffe1b610b1e2e078367de6c5e5876fc60051ed267cff0726c2329ff2dd75");
        final byte[] input = GGStrings.toBytes("T01-8a018414a992487180b2630efbe13d56");
        
        final boolean v = GGSignatures.SHA1_ECDSA.verify(publicKey, signature, input);

        LOG.info("testRead: GGVerified: %s", v);
        
        assertThat(v, is(true));
        
        
        
        final String pem = PEMWriter.write(publicKey);
        
        LOG.info("testRead: PEM: \n%s", pem);

        
        final PublicKey pk = PEMReader.readPublicKey(pem);
        
        LOG.info("testRead: o: %s", pk);
        LOG.info("testReadL o.class: %s", pk.getClass());
        
    }



}
