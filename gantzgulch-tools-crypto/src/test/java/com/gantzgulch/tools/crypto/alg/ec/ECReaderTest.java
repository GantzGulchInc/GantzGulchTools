package com.gantzgulch.tools.crypto.alg.ec;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

import org.junit.Test;

import com.gantzgulch.tools.common.codec.GGHex;
import com.gantzgulch.tools.common.lang.GGStrings;
import com.gantzgulch.tools.common.logging.GGLogger;
import com.gantzgulch.tools.crypto.GGSignatures;
import com.gantzgulch.tools.crypto.pem.PEMReader;
import com.gantzgulch.tools.crypto.pem.PEMWriter;

public class ECReaderTest {

    private static final GGLogger LOG = GGLogger.getLogger(ECReaderTest.class);
    
    public ECReaderTest() {
    }

    
    @Test
    public void testRead() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, InvalidKeyException, SignatureException {

        final byte[] pubKeyRaw = GGHex.fromHexString("04681d5cedced95395a714748b506c9413d5d4ef5684ae55acad84daf2318707bd9b42836d6c9b6f3b25cba9e3983a3f1c6b98f0316009713d5c5c34cb564615fe");
        
        final PublicKey publicKey = ECReader.readPublicKeyX963("secp256r1", pubKeyRaw);
        
        
        
        
        final byte[] signature =  GGHex.fromHexString("3045022100dbbc8514e726f44415d3433a0560e99346f7e2d0b7e23e557182d5e71efa1f0e0220723b655feb437d51dc68257f47630e7f755993a3869a00c571f87af0be8f2571");
        final byte[] input = GGStrings.toBytes("SN-0A-00000233");
        
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
