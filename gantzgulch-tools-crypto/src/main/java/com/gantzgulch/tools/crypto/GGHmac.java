package com.gantzgulch.tools.crypto;

public interface GGHmac {

    String getAlgorithm();
    
    byte[] sign(byte[] secret, byte[] input);

}
