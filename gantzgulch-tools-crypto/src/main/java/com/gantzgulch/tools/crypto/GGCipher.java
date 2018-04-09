package com.gantzgulch.tools.crypto;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;

public interface GGCipher {

    String getAlgorithm();
    
    GGKeySpec getKeySpec();
    
    GGIvNonceSpec getIvNonceSpec();
    
    void encrypt(Key key, InputStream input, OutputStream output, byte[] ivNonce);
    
    void decrypt(Key key, InputStream input, OutputStream output, byte[] ivNonce);
    
    byte[] encrypt(Key key, byte[] input, byte[] ivNonce);

    byte[] decrypt(Key key, byte[] input, byte[] ivNonce);

}
