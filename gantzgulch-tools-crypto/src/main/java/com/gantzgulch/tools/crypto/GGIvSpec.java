package com.gantzgulch.tools.crypto;

public interface GGIvSpec {

    int getMinSizeInBits();

    int getMaxSizeInBits();
    
    void verify(final byte[] iv);
    
}
