package com.gantzgulch.tools.crypto;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyPair;

import com.gantzgulch.logging.core.GGLogger;

public class AbstractCipherTest {

    protected final GGLogger LOG = GGLogger.getLogger( getClass() );

    
    protected void testEncryptDecrypt(final GGCipher cipher, final Key key, final int inputSize, final int ivNonceSize) {

        LOG.info("Testing: %s with byte array.", cipher);

        final byte[] iv = GGNonces.SECURE_RANDOM.nonce(ivNonceSize);
        final byte[] input = GGNonces.SECURE_RANDOM.nonce(inputSize);

        final byte[] plain = encryptThenDecrypt(cipher, key, input, iv);

        assertThat(plain, notNullValue());

        assertThat(plain, equalTo(input));

    }

    protected void testEncryptDecrypt_streams(final GGCipher cipher, final Key key, final int inputSize, final int ivNonceSize) {

        LOG.info("Testing: %s with stream.", cipher);
        
        final byte[] iv = GGNonces.SECURE_RANDOM.nonce(ivNonceSize);
        final byte[] input = GGNonces.SECURE_RANDOM.nonce(inputSize);

        final ByteArrayInputStream is = new ByteArrayInputStream(input);
        final ByteArrayOutputStream os = new ByteArrayOutputStream(input.length);

        encryptThenDecrypt(cipher, key, is, os, iv);

        final byte[] plainBytes = os.toByteArray();
        
        assertThat(plainBytes, notNullValue());
        assertThat(plainBytes, equalTo(input));

    }

    protected void testEncryptWithPublicDecryptWithPrivate(final GGCipher cipher, final KeyPair kp1, final int inputSize, final int ivNonceSize) {
        
        LOG.info("Testing: %s with stream.", cipher);
        
        final byte[] iv = GGNonces.SECURE_RANDOM.nonce(ivNonceSize);
        final byte[] input = GGNonces.SECURE_RANDOM.nonce(inputSize);
        
        final byte[] encryptedInput = cipher.encrypt(kp1.getPublic(), input, iv);
        
        assertThat(encryptedInput, notNullValue());
        assertThat(encryptedInput, not( equalTo(input)));
        
        final byte[] decryptedInput = cipher.decrypt(kp1.getPrivate(), encryptedInput, iv);

        assertThat(decryptedInput, notNullValue());
        assertThat(decryptedInput, equalTo(input) );
        
    }

    protected void testEncryptWithPrivateDecryptWithPublic(final GGCipher cipher, final KeyPair kp1, final int inputSize, final int ivNonceSize) {
    
        LOG.info("Testing: %s with stream.", cipher);
        
        final byte[] iv = GGNonces.SECURE_RANDOM.nonce(ivNonceSize);
        final byte[] input = GGNonces.SECURE_RANDOM.nonce(inputSize);
 
        final byte[] encryptedInput = cipher.encrypt(kp1.getPrivate(), input, iv);
        
        assertThat(encryptedInput, notNullValue());
        assertThat(encryptedInput, not( equalTo(input)));
        
        final byte[] decryptedInput = cipher.decrypt(kp1.getPublic(), encryptedInput, iv);
        
        assertThat(decryptedInput, notNullValue());
        assertThat(decryptedInput, equalTo(input) );
        
    }


    protected void testEncryptWithPublicDecryptWithPrivateStreams(final GGCipher cipher, final KeyPair kp1, final int inputSize, final int ivNonceSize) {
        
        LOG.info("Testing: %s with stream.", cipher);
        
        final byte[] iv = GGNonces.SECURE_RANDOM.nonce(ivNonceSize);
        final byte[] input = GGNonces.SECURE_RANDOM.nonce(inputSize);
        
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(input);
        final ByteArrayOutputStream encryptedOutputStream = new ByteArrayOutputStream();
        
        cipher.encrypt(kp1.getPublic(), inputStream, encryptedOutputStream, iv);
        
        final byte[] encryptedInput = encryptedOutputStream.toByteArray();
        
        assertThat(encryptedInput, notNullValue());
        
        final ByteArrayInputStream encryptedInputStream = new ByteArrayInputStream(encryptedInput);
        final ByteArrayOutputStream decryptedOutputStream = new ByteArrayOutputStream();
        
        cipher.decrypt(kp1.getPrivate(), encryptedInputStream, decryptedOutputStream, iv);

        final byte[] decryptedInput = decryptedOutputStream.toByteArray();
        
        assertThat(decryptedInput, notNullValue());
        assertThat(decryptedInput, equalTo(input) );
        
    }

    protected void testEncryptWithPrivateDecryptWithPublicStreams(final GGCipher cipher, final KeyPair kp1, final int inputSize, final int ivNonceSize) {
    
        
        LOG.info("Testing: %s with stream.", cipher);
        
        final byte[] iv = GGNonces.SECURE_RANDOM.nonce(ivNonceSize);
        final byte[] input = GGNonces.SECURE_RANDOM.nonce(inputSize);
        
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(input);
        final ByteArrayOutputStream encryptedOutputStream = new ByteArrayOutputStream();
        
        cipher.encrypt(kp1.getPrivate(), inputStream, encryptedOutputStream, iv);
        
        final byte[] encryptedInput = encryptedOutputStream.toByteArray();
        
        assertThat(encryptedInput, notNullValue());
        
        final ByteArrayInputStream encryptedInputStream = new ByteArrayInputStream(encryptedInput);
        final ByteArrayOutputStream decryptedOutputStream = new ByteArrayOutputStream();
        
        cipher.decrypt(kp1.getPublic(), encryptedInputStream, decryptedOutputStream, iv);

        final byte[] decryptedInput = decryptedOutputStream.toByteArray();
        
        assertThat(decryptedInput, notNullValue());
        assertThat(decryptedInput, equalTo(input) );
    }



    protected byte[] encryptThenDecrypt(final GGCipher cipher, final Key key, final byte[] input, final byte[] iv) {
        
        final byte[] cipherText = cipher.encrypt(key, input, iv);
        
        final byte[] plainText = cipher.decrypt(key, cipherText, iv);
        
        return plainText;
    }
    
    protected void encryptThenDecrypt(final GGCipher cipher, final Key key, final InputStream is, final OutputStream os, final byte[] ivNonce) {
        
        final ByteArrayOutputStream cipherOutputStream = new ByteArrayOutputStream();
        
        cipher.encrypt(key, is, cipherOutputStream, ivNonce);
        
        final ByteArrayInputStream cipherInputStream = new ByteArrayInputStream(cipherOutputStream.toByteArray());
        
        cipher.decrypt(key, cipherInputStream, os, ivNonce);
    }
    
}
