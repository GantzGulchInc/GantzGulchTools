package com.gantzgulch.tools.crypto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;

import com.gantzgulch.tools.common.logging.GGLogger;

public class AbstractCipherTest {

    protected final GGLogger LOG = GGLogger.getLogger( getClass() );

    protected byte[] encryptThenDecrypt(final GGCipher cipher, final Key key, final byte[] input, final byte[] iv, final byte[] nonce) {
        
        final byte[] cipherText = cipher.encrypt(key, input, iv, nonce);
        
        final byte[] plainText = cipher.decrypt(key, cipherText, iv, nonce);
        
        return plainText;
    }
    
    protected void encryptThenDecrypt(final GGCipher cipher, final Key key, final InputStream is, final OutputStream os, final byte[] iv, final byte[] nonce) {
        
        final ByteArrayOutputStream cipherOutputStream = new ByteArrayOutputStream();
        
        cipher.encrypt(key, is, cipherOutputStream, iv, nonce);
        
        final ByteArrayInputStream cipherInputStream = new ByteArrayInputStream(cipherOutputStream.toByteArray());
        
        cipher.decrypt(key, cipherInputStream, os, iv, nonce);
    }
    
}
