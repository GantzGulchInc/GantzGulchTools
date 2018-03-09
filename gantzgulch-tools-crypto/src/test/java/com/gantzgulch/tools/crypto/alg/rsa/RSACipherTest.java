package com.gantzgulch.tools.crypto.alg.rsa;

import java.security.KeyPair;

import org.junit.Test;

import com.gantzgulch.tools.crypto.AbstractCipherTest;
import com.gantzgulch.tools.crypto.GGCipher;

public class RSACipherTest extends AbstractCipherTest {

    @Test
    public void test1024() {
        
        final KeyPair keyPair = RSAKeyGenerator.generate(1024);
        
        testEncryptWithPublicDecryptWithPrivate(RSACipher.RSA_NONE_OAEPWithMD5AndMGF1Padding, keyPair, 94, 0, 0);
        testEncryptWithPublicDecryptWithPrivate(RSACipher.RSA_NONE_OAEPWithSHA_1AndMGF1Padding, keyPair, 86, 0, 0);
        testEncryptWithPublicDecryptWithPrivate(RSACipher.RSA_NONE_OAEPWithSHA_224AndMGF1Padding, keyPair, 70, 0, 0);
        testEncryptWithPublicDecryptWithPrivate(RSACipher.RSA_NONE_OAEPWithSHA_256AndMGF1Padding, keyPair, 60, 0, 0);
        testEncryptWithPublicDecryptWithPrivate(RSACipher.RSA_NONE_OAEPWithSHA_384AndMGF1Padding, keyPair, 30, 0, 0);
    }
    
    @Test
    public void test2048() {
        
        final KeyPair keyPair = RSAKeyGenerator.generate(2048);
        
        for(final GGCipher cipher : RSACipher.CIPHERS){
            testEncryptWithPublicDecryptWithPrivate(cipher, keyPair, 115, 0, 0);
            testEncryptWithPrivateDecryptWithPublic(cipher, keyPair, 115, 0, 0);
            testEncryptWithPublicDecryptWithPrivateStreams(cipher, keyPair, 115, 0, 0);
            testEncryptWithPrivateDecryptWithPublicStreams(cipher, keyPair, 115, 0, 0);
        }
    }
    
    @Test
    public void test4096() {
        
        final KeyPair keyPair = RSAKeyGenerator.generate(4096);
        
        for(final GGCipher cipher : RSACipher.CIPHERS){
            testEncryptWithPublicDecryptWithPrivate(cipher, keyPair, 128, 0, 0);
            testEncryptWithPrivateDecryptWithPublic(cipher, keyPair, 128, 0, 0);
            testEncryptWithPublicDecryptWithPrivateStreams(cipher, keyPair, 128, 0, 0);
            testEncryptWithPrivateDecryptWithPublicStreams(cipher, keyPair, 128, 0, 0);
        }
    }
    
    
}
