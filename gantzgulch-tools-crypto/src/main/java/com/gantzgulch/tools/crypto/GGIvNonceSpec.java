package com.gantzgulch.tools.crypto;

public interface GGIvNonceSpec {

    int getMinSizeInBits();

    int getMaxSizeInBits();
    
    void verify(final byte[] iv);
    
}
