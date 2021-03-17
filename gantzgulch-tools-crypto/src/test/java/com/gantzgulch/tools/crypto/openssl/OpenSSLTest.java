package com.gantzgulch.tools.crypto.openssl;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

import com.gantzgulch.tools.common.lang.GGIO;
import com.gantzgulch.tools.crypto.GGOpenSSL;
import com.gantzgulch.tools.crypto.GGOpenSSLs;

public class OpenSSLTest {

    @Test
    public void test_aes_256_cbc_decrypt() throws IOException {

        testOpenSSL("973jd9s73j297k", "/test_data/openssl/plainText.txt", "/test_data/openssl/openSsl-aes-256-cbc", GGOpenSSLs.AES_256_CBC);

    }

    @Test
    public void test_aes_128_cbc_decrypt() throws IOException {

        testOpenSSL("973jd9s73j297k", "/test_data/openssl/plainText.txt", "/test_data/openssl/openSsl-aes-128-cbc", GGOpenSSLs.AES_128_CBC);

    }

    @Test
    public void test_aes_256_cbc_encrypt() throws IOException {

        final byte[] password = "973jd9s73j297k".getBytes(StandardCharsets.UTF_8);

        try (final InputStream is = getClass().getResourceAsStream("/test_data/openssl/plainText.txt");
                final OutputStream os = new FileOutputStream("/tmp/data.enc")) {

            GGOpenSSLs.AES_256_CBC.encrypt(password, is, os);

        }

    }

    private void testOpenSSL(//
            final String passwordString, //
            final String plainTextResource, //
            final String encryptedResource, //
            final GGOpenSSL ggOpenSSL) throws IOException {

        final byte[] expected = GGIO.read(getClass().getResourceAsStream(plainTextResource));

        try (final InputStream is = getClass().getResourceAsStream(encryptedResource)) {

            final ByteArrayOutputStream baos = new ByteArrayOutputStream();

            final byte[] password = passwordString.getBytes(StandardCharsets.UTF_8);

            ggOpenSSL.decrypt(password, is, baos);

            assertThat(baos.size(), equalTo(expected.length));

            assertThat(expected, equalTo(baos.toByteArray()));

        }

    }
}
