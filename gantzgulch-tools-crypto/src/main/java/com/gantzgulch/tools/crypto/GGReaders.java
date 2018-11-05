package com.gantzgulch.tools.crypto;

import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPublicKey;

import com.gantzgulch.tools.crypto.alg.ec.ECReader;
import com.gantzgulch.tools.crypto.alg.rsa.RSAReader;

public class GGReaders {

    
    public static GGReader<ECPublicKey> EC = new ECReader();
    
    public static GGReader<RSAPublicKey> RSA = new RSAReader();
    
    
}
