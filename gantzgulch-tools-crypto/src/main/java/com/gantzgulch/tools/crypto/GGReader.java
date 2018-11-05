package com.gantzgulch.tools.crypto;

import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Path;
import java.security.KeyPair;
import java.security.PublicKey;

public interface GGReader<T extends PublicKey> {

    
    public KeyPair readKeyPair(final String pem);

    public KeyPair readKeyPair(final InputStream is);

    public KeyPair readKeyPair(final Reader reader);

    public KeyPair readKeyPair(final Path path);


    public T readPublicKey(final String pem);

    public T readPublicKey(final InputStream is);

    public T readPublicKey(final Reader reader);

    public T readPublicKey(final Path path);
    
}
