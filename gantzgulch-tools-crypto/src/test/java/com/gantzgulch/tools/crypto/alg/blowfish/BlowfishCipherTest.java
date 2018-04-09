package com.gantzgulch.tools.crypto.alg.blowfish;

import java.security.Key;

import org.junit.Test;

import com.gantzgulch.tools.crypto.AbstractCipherTest;
import com.gantzgulch.tools.crypto.GGCipher;

public class BlowfishCipherTest extends AbstractCipherTest {

    public static final GGCipher CIPHER = BlowfishCipher.BLOWFISH_ECB_NO_PADDING;
    public static final GGCipher CIPHER_PADDING = BlowfishCipher.BLOWFISH_ECB_NO_PKCS5Padding;

    @Test
    public void test128() {

        for (int keySize = 32; keySize < 449; keySize++) {

            final Key key = BlowfishKeyGenerator.generate(keySize);

            testEncryptDecrypt(CIPHER, key, 1024, 0);
        }

    }

    @Test
    public void test128Padding() {

        final Key key = BlowfishKeyGenerator.generate(128);

        testEncryptDecrypt(CIPHER_PADDING, key, 1024, 0);
        testEncryptDecrypt(CIPHER_PADDING, key, 1345, 0);

    }

    @Test
    public void test256() {

        final Key key = BlowfishKeyGenerator.generate(256);

        testEncryptDecrypt(CIPHER, key, 1024, 0);

    }

    @Test
    public void test256Padding() {

        final Key key = BlowfishKeyGenerator.generate(256);

        testEncryptDecrypt(CIPHER_PADDING, key, 1024, 0);
        testEncryptDecrypt(CIPHER_PADDING, key, 1345, 0);

    }

}
