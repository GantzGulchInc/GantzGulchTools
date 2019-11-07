package com.gantzgulch.tools.crypto;

import java.io.InputStream;
import java.io.OutputStream;

public interface GGOpenSSL {

    void encrypt(byte[] password, InputStream is, OutputStream os);

    void decrypt(byte[] password, InputStream is, OutputStream os);

}
