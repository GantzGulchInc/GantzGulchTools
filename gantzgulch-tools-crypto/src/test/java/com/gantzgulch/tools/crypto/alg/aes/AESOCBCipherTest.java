package com.gantzgulch.tools.crypto.alg.aes;

import java.security.Key;

import org.junit.Test;

import com.gantzgulch.tools.crypto.AbstractCipherTest;
import com.gantzgulch.tools.crypto.GGCipher;

public class AESOCBCipherTest extends AbstractCipherTest {

    public static final GGCipher CIPHER = AESAEADCipher.AES_OCB_NO_PADDING;

    @Test
    public void test128() {

        final Key key = AESKeyGenerator.generate(128);

        testEncryptDecrypt(CIPHER, key, 1024, 12, 0);
        testEncryptDecrypt_streams(CIPHER, key, 1024, 1, 0);

        testEncryptDecrypt(CIPHER, key, 1345, 12, 0);
        testEncryptDecrypt_streams(CIPHER, key, 1345, 1, 0);

        
        
        testEncryptDecrypt(CIPHER, key, 1024, 12, 0);
        testEncryptDecrypt_streams(CIPHER, key, 1024, 15, 0);

        testEncryptDecrypt(CIPHER, key, 1345, 12, 0);
        testEncryptDecrypt_streams(CIPHER, key, 1345, 15, 0);
    }


    @Test
    public void test192() {

        final Key key = AESKeyGenerator.generate(192);

        testEncryptDecrypt(CIPHER, key, 1024, 12, 0);
        testEncryptDecrypt_streams(CIPHER, key, 1024, 1, 0);

        testEncryptDecrypt(CIPHER, key, 1345, 12, 0);
        testEncryptDecrypt_streams(CIPHER, key, 1345, 1, 0);

        
        testEncryptDecrypt(CIPHER, key, 1024, 12, 0);
        testEncryptDecrypt_streams(CIPHER, key, 1024, 15, 0);

        testEncryptDecrypt(CIPHER, key, 1345, 12, 0);
        testEncryptDecrypt_streams(CIPHER, key, 1345, 15, 0);
    }

    @Test
    public void test256() {

        final Key key = AESKeyGenerator.generate(256);

        testEncryptDecrypt(CIPHER, key, 1024, 12, 0);
        testEncryptDecrypt_streams(CIPHER, key, 1024, 1, 0);

        testEncryptDecrypt(CIPHER, key, 1345, 12, 0);
        testEncryptDecrypt_streams(CIPHER, key, 1345, 1, 0);

        
        testEncryptDecrypt(CIPHER, key, 1024, 12, 0);
        testEncryptDecrypt_streams(CIPHER, key, 1024, 15, 0);

        testEncryptDecrypt(CIPHER, key, 1345, 12, 0);
        testEncryptDecrypt_streams(CIPHER, key, 1345, 15, 0);
    }


}
