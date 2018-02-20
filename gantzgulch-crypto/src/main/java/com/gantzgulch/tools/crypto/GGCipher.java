package com.gantzgulch.tools.crypto;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;

public interface GGCipher {

    String getAlgorithm();
    
    int getIvSize();
    
    int getNonceSize();
    
    void encrypt(Key key, InputStream input, OutputStream output, byte[] iv, byte[] nonce);
    
    void decrypt(Key key, InputStream input, OutputStream output, byte[] iv, byte[] nonce);
    
    byte[] encrypt(Key key, byte[] input, byte[] iv, byte[] nonce);

    byte[] decrypt(Key key, byte[] input, byte[] iv, byte[] nonce);

}
