package com.gantzgulch.tools.crypto.aes;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.security.SecureRandom;

import javax.crypto.SecretKey;

import org.junit.Test;

import com.gantzgulch.tools.common.logging.GGLogger;
import com.gantzgulch.tools.crypto.GGCipher;

public class AESGCMCipherTest {

    private static final GGLogger LOG = GGLogger.getLogger(AESGCMCipherTest.class);
    
    private SecureRandom rnd = new SecureRandom();

    @Test
    public void testAll() {

        for (final AESGCMCipher c : AESGCMCipher.values()) {

            encryptWithPublicDecryptWithPrivate(c);
        }
    }

    private void encryptWithPublicDecryptWithPrivate(final GGCipher cipher) {

        LOG.info("Testing: %s", cipher);
        
        final SecretKey kp1 = AESGenerator.generate(128);
        assertThat(kp1, notNullValue());

        final byte[] nonce = new byte[16];
        final byte[] input = new byte[2048 >> 3 - 12];

        rnd.nextBytes(nonce);
        rnd.nextBytes(input);

        final byte[] encryptedInput = cipher.encrypt(kp1, input, nonce);
        assertThat(encryptedInput, notNullValue());

        final byte[] decryptedInput = cipher.decrypt(kp1, encryptedInput, nonce);
        assertThat(decryptedInput, notNullValue());

        assertThat(decryptedInput, equalTo(input));

    }

}
