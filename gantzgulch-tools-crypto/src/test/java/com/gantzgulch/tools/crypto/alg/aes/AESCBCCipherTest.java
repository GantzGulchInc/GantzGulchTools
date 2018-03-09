package com.gantzgulch.tools.crypto.alg.aes;

import java.security.Key;

import org.junit.Test;

import com.gantzgulch.tools.crypto.AbstractCipherTest;
import com.gantzgulch.tools.crypto.GGCipher;

public class AESCBCCipherTest extends AbstractCipherTest {

    public static final GGCipher CIPHER = AESAEADCipher.AES_CBC_NO_PADDING;
    public static final GGCipher CIPHER_PKCS7_PADDING = AESAEADCipher.AES_CBC_PKCS7_PADDING;;
    
    @Test
    public void test128() {

        final Key key = AESKeyGenerator.generate(128);

        testEncryptDecrypt(CIPHER, key, 1024, 16, 0);
        testEncryptDecrypt_streams(CIPHER, key, 1024, 16, 0);
    }


    @Test
    public void test192() {

        final Key key = AESKeyGenerator.generate(192);

        testEncryptDecrypt(CIPHER, key, 1024, 16, 0);
        testEncryptDecrypt_streams(CIPHER, key, 1024, 16, 0);
    }

    @Test
    public void test256() {

        final Key key = AESKeyGenerator.generate(256);

        testEncryptDecrypt(CIPHER, key, 1024, 16, 0);
        testEncryptDecrypt_streams(CIPHER, key, 1024, 16, 0);
    }

    @Test
    public void test128WithPadding() {

        final Key key = AESKeyGenerator.generate(128);

        testEncryptDecrypt(CIPHER_PKCS7_PADDING, key, 1024, 16, 0);
        testEncryptDecrypt_streams(CIPHER_PKCS7_PADDING, key, 1024, 16, 0);

        testEncryptDecrypt(CIPHER_PKCS7_PADDING, key, 1345, 16, 0);
        testEncryptDecrypt_streams(CIPHER_PKCS7_PADDING, key, 1345, 16, 0);
    }


    @Test
    public void test192WithPadding() {

        final Key key = AESKeyGenerator.generate(192);

        testEncryptDecrypt(CIPHER_PKCS7_PADDING, key, 1024, 16, 0);
        testEncryptDecrypt_streams(CIPHER_PKCS7_PADDING, key, 1024, 16, 0);

        testEncryptDecrypt(CIPHER_PKCS7_PADDING, key, 1345, 16, 0);
        testEncryptDecrypt_streams(CIPHER_PKCS7_PADDING, key, 1345, 16, 0);
    }

    @Test
    public void test256WithPadding() {

        final Key key = AESKeyGenerator.generate(256);

        testEncryptDecrypt(CIPHER_PKCS7_PADDING, key, 1024, 16, 0);
        testEncryptDecrypt_streams(CIPHER_PKCS7_PADDING, key, 1024, 16, 0);

        testEncryptDecrypt(CIPHER_PKCS7_PADDING, key, 1345, 16, 0);
        testEncryptDecrypt_streams(CIPHER_PKCS7_PADDING, key, 1345, 16, 0);
    }

}
