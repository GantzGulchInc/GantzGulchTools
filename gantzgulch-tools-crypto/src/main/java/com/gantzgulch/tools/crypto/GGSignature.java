package com.gantzgulch.tools.crypto;

import java.io.InputStream;
import java.security.PrivateKey;
import java.security.PublicKey;

public interface GGSignature {

    String getAlgorithm();

    byte[] sign(PrivateKey key, InputStream input);
    
    boolean verify(PublicKey key, byte[] signature, InputStream input);

    byte[] sign(PrivateKey key, byte[] input);

    boolean verify(PublicKey key, byte[] signature, byte[] input);

}
