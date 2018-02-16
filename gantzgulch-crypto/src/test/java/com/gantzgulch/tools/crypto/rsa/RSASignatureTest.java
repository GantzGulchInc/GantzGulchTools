package com.gantzgulch.tools.crypto.rsa;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyPair;

import org.junit.Test;

import com.gantzgulch.tools.common.lang.GGIO;
import com.gantzgulch.tools.common.lang.GGStrings;

public class RSASignatureTest {

    private final byte[] VALUE = GGStrings.toBytes("This is a random set of data 0123456789abcdefghijklmnopqrstuvwxyz");

    private final KeyPair KEY_PAIR = RSAGenerator.generate(2048);

    @Test
    public void signAndVerifySha1_valid() throws IOException, GeneralSecurityException {

        final byte[] signature = RSASignature.SHA1_RSA.sign(VALUE, KEY_PAIR.getPrivate());

        assertThat(signature, notNullValue());

        final boolean verify = RSASignature.SHA1_RSA.verify(signature, VALUE, KEY_PAIR.getPublic());

        assertThat(verify, is(true));

        final byte[] signature2 = RSASignature.SHA1_RSA.sign(VALUE, KEY_PAIR.getPrivate());

        assertThat(signature2, notNullValue());

    }

    @Test
    public void signAndVerifySha256_valid() throws IOException, GeneralSecurityException {

        final byte[] signature = RSASignature.SHA256_RSA.sign(VALUE, KEY_PAIR.getPrivate());

        assertThat(signature, notNullValue());

        final boolean verify = RSASignature.SHA256_RSA.verify(signature, VALUE, KEY_PAIR.getPublic());

        assertThat(verify, is(true));

        final byte[] signature2 = RSASignature.SHA256_RSA.sign(VALUE, KEY_PAIR.getPrivate());

        assertThat(signature2, notNullValue());

    }

    @Test
    public void signAndVerifySha384_valid() throws IOException, GeneralSecurityException {

        final byte[] signature = RSASignature.SHA384_RSA.sign(VALUE, KEY_PAIR.getPrivate());

        assertThat(signature, notNullValue());

        final boolean verify = RSASignature.SHA384_RSA.verify(signature, VALUE, KEY_PAIR.getPublic());

        assertThat(verify, is(true));

        final byte[] signature2 = RSASignature.SHA384_RSA.sign(VALUE, KEY_PAIR.getPrivate());

        assertThat(signature2, notNullValue());

    }

    @Test
    public void signAndVerifySha512_valid() throws IOException, GeneralSecurityException {

        final byte[] signature = RSASignature.SHA512_RSA.sign(VALUE, KEY_PAIR.getPrivate());

        assertThat(signature, notNullValue());

        final boolean verify = RSASignature.SHA512_RSA.verify(signature, VALUE, KEY_PAIR.getPublic());

        assertThat(verify, is(true));

        final byte[] signature2 = RSASignature.SHA512_RSA.sign(VALUE, KEY_PAIR.getPrivate());

        assertThat(signature2, notNullValue());

    }

    @Test
    public void signSha1_invalid() throws IOException, GeneralSecurityException {

        final byte[] signature = RSASignature.SHA1_RSA.sign(VALUE, KEY_PAIR.getPrivate());

        assertThat(signature, notNullValue());

        signature[0] = (byte) (signature[0] ^ 0xFF);

        final boolean verify = RSASignature.SHA1_RSA.verify(signature, VALUE, KEY_PAIR.getPublic());

        assertThat(verify, is(false));
    }

    @Test
    public void verifyOpensslSha256Signature() throws IOException, GeneralSecurityException {

        final KeyPair kp = RSAReader.readKeyPair(getClass().getResourceAsStream("/test_data/openssl_rsa_2048_A.pem"));

        final byte[] data = GGIO.read(getClass().getResourceAsStream("/test_data/data_01.dat"));

        final byte[] signature = GGIO.read(getClass().getResourceAsStream("/test_data/data_01.signed.openssl_rsa_2048_A.dat"));

        final boolean verified = RSASignature.SHA256_RSA.verify(signature, data, kp.getPublic());

        assertThat(verified, is(true));

    }
}
