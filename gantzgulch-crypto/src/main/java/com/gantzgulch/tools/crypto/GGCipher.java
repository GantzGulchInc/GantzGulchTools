package com.gantzgulch.tools.crypto;

import java.security.Key;

public interface GGCipher {

    byte[] encrypt(Key key, byte[] input, byte[] nonce);

    byte[] decrypt(Key key, byte[] input, byte[] nonce);

}
