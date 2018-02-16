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

public class RSASignerTest {

    private final RSAGenerator rsaGenerator = new RSAGenerator();
    private final RSAReader rsaReader = new RSAReader();
    private final RSASigner rsaSigner = new RSASigner();

    private final byte[] VALUE = GGStrings.toBytes("This is a random set of data 0123456789abcdefghijklmnopqrstuvwxyz");

    private final KeyPair KEY_PAIR = rsaGenerator.generate(2048);

    @Test
    public void signSha1_valid() throws IOException, GeneralSecurityException {

        final byte[] signature = rsaSigner.sign(RSASignatureAlgorithm.SHA1_RSA, VALUE, KEY_PAIR.getPrivate());

        assertThat(signature, notNullValue());

        final boolean verify = rsaSigner.verify(RSASignatureAlgorithm.SHA1_RSA, signature, VALUE, KEY_PAIR.getPublic());

        assertThat(verify, is(true));

        final byte[] signature2 = rsaSigner.sign(RSASignatureAlgorithm.SHA1_RSA, VALUE, KEY_PAIR.getPrivate());

        assertThat(signature2, notNullValue());

    }

    @Test
    public void signSha1_invalid() throws IOException, GeneralSecurityException {

        final byte[] signature = rsaSigner.sign(RSASignatureAlgorithm.SHA1_RSA, VALUE, KEY_PAIR.getPrivate());

        assertThat(signature, notNullValue());

        signature[0] = (byte) (signature[0] ^ 0xFF);

        final boolean verify = rsaSigner.verify(RSASignatureAlgorithm.SHA1_RSA, signature, VALUE, KEY_PAIR.getPublic());

        assertThat(verify, is(false));
    }

    @Test
    public void verifyOpensslSha256Signature() throws IOException, GeneralSecurityException {

        final KeyPair kp = rsaReader.readKeyPair(getClass().getResourceAsStream("/test_data/openssl_rsa_2048_A.pem"));

        final byte[] data = GGIO.read(getClass().getResourceAsStream("/test_data/data_01.dat"));

        final byte[] signature = GGIO.read(getClass().getResourceAsStream("/test_data/data_01.signed.openssl_rsa_2048_A.dat"));

        final boolean verified = rsaSigner.verify(RSASignatureAlgorithm.SHA256_RSA, signature, data, kp.getPublic());

        assertThat(verified, is(true));

    }
}
