package com.gantzgulch.tools.crypto;

public interface GGNonceSpec {

    int getMinSize();

    int getMaxSize();
    
    void verify(final byte[] iv);
    
}
