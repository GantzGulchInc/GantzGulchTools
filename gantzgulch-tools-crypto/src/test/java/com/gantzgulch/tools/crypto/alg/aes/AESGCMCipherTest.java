package com.gantzgulch.tools.crypto.alg.aes;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.crypto.SecretKey;

import org.junit.Before;
import org.junit.Test;

import com.gantzgulch.tools.crypto.AbstractCipherTest;
import com.gantzgulch.tools.crypto.GGCipher;
import com.gantzgulch.tools.crypto.GGNonces;

public class AESGCMCipherTest extends AbstractCipherTest {

    private SecretKey key = AESKeyGenerator.generate(128);
    
    @Before
    public void before() {

    }
    
    @Test
    public void testAll() {

        for (final AESGCMCipher c : AESGCMCipher.CIPHERS) {

            encryptDecrypt(c);
            encryptDecrypt_streams(c);
        }
    }

    private void encryptDecrypt(final GGCipher cipher) {

        LOG.info("Testing: %s with byte array.", cipher);
        
        final byte[] input = GGNonces.SECURE_RANDOM.nonce(4096);
        
        final byte[] nonce = GGNonces.SECURE_RANDOM.nonce(cipher.getNonceSize());

        final byte[] plain = encryptThenDecrypt(cipher, key, input, null, nonce);

        assertThat(plain, equalTo(input));

    }

    private void encryptDecrypt_streams(final GGCipher cipher) {

        LOG.info("Testing: %s with stream.", cipher);
        
        final byte[] input = GGNonces.SECURE_RANDOM.nonce(1024 * 1024 * 4);

        final byte[] nonce = GGNonces.SECURE_RANDOM.nonce(cipher.getNonceSize());

        final ByteArrayInputStream is = new ByteArrayInputStream(input);
        final ByteArrayOutputStream os = new ByteArrayOutputStream(input.length);

        encryptThenDecrypt(cipher, key, is, os, null, nonce);
        
        final byte[] plainBytes = os.toByteArray();
        
        assertThat(plainBytes, equalTo(input));

    }

}
