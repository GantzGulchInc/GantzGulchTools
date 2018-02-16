package com.gantzgulch.tools.crypto.rsa;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.security.KeyPair;
import java.security.SecureRandom;

import org.junit.Test;

import com.gantzgulch.tools.crypto.GGCipher;

public class RSACipherTest {

    private SecureRandom rnd = new SecureRandom();

    @Test
    public void testAll() {
        
        for(final RSACipher c : RSACipher.values()) {
         
            encryptWithPublicDecryptWithPrivate(c);
            encryptWithPrivateDecryptWithPublic(c);
        }
    }
    
    private void encryptWithPublicDecryptWithPrivate(final GGCipher cipher) {
    
        final KeyPair kp1 = RSAGenerator.generate(2048);
        assertThat(kp1, notNullValue());

        final byte[] input = new byte[ 2048 >> 3 - 12];

        rnd.nextBytes(input);
        
        final byte[] encryptedInput = cipher.encrypt(kp1.getPublic(), input, null);
        assertThat(encryptedInput, notNullValue());
        
        final byte[] decryptedInput = cipher.decrypt(kp1.getPrivate(), encryptedInput, null);
        assertThat(decryptedInput, notNullValue());
        
        assertThat(decryptedInput, equalTo(input) );
        
    }

    private void encryptWithPrivateDecryptWithPublic(final GGCipher cipher) {
    
        final KeyPair kp1 = RSAGenerator.generate(2048);
        assertThat(kp1, notNullValue());

        final byte[] input = new byte[2048 >> 3 - 12];

        rnd.nextBytes(input);
        
        final byte[] encryptedInput = cipher.encrypt(kp1.getPrivate(), input, null);
        assertThat(encryptedInput, notNullValue());
        
        final byte[] decryptedInput = cipher.decrypt(kp1.getPublic(), encryptedInput, null);
        assertThat(decryptedInput, notNullValue());
        
        assertThat(decryptedInput, equalTo(input) );
        
    }

}
