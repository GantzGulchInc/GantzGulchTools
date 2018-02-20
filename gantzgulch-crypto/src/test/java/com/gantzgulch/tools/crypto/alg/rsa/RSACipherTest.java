package com.gantzgulch.tools.crypto.alg.rsa;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.security.KeyPair;

import org.junit.Before;
import org.junit.Test;

import com.gantzgulch.tools.crypto.AbstractCipherTest;
import com.gantzgulch.tools.crypto.GGCipher;
import com.gantzgulch.tools.crypto.GGNonce;
import com.gantzgulch.tools.crypto.alg.rsa.RSACipher;
import com.gantzgulch.tools.crypto.alg.rsa.RSAKeyGenerator;

public class RSACipherTest extends AbstractCipherTest {

    private final KeyPair kp1 = RSAKeyGenerator.generate(4096);

    private byte[] input;
    
    @Before
    public void before(){
    
        input = GGNonce.SECURE_RANDOM.nonce(128);
    }
    
    @Test
    public void testAll() {
        
        for(final RSACipher c : RSACipher.CIPHERS) {
         
            encryptWithPublicDecryptWithPrivate(c);
            encryptWithPrivateDecryptWithPublic(c);
        }
    }
    
    private void encryptWithPublicDecryptWithPrivate(final GGCipher cipher) {
    
        final byte[] encryptedInput = cipher.encrypt(kp1.getPublic(), input, null, null);
        assertThat(encryptedInput, notNullValue());
        
        final byte[] decryptedInput = cipher.decrypt(kp1.getPrivate(), encryptedInput, null, null);
        assertThat(decryptedInput, notNullValue());
        
        assertThat(decryptedInput, equalTo(input) );
        
    }

    private void encryptWithPrivateDecryptWithPublic(final GGCipher cipher) {
    
        final byte[] encryptedInput = cipher.encrypt(kp1.getPrivate(), input, null, null);
        assertThat(encryptedInput, notNullValue());
        
        final byte[] decryptedInput = cipher.decrypt(kp1.getPublic(), encryptedInput, null, null);
        assertThat(decryptedInput, notNullValue());
        
        assertThat(decryptedInput, equalTo(input) );
        
    }

}
