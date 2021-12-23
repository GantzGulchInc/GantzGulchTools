package com.gantzgulch.tools.crypto.alg.dsa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.security.KeyPair;
import java.security.PublicKey;

import org.junit.Test;

import com.gantzgulch.tools.common.lang.GGStrings;
import com.gantzgulch.tools.crypto.matchers.DSA;

public class DSAReaderWriterTest {

    private DSAReader dsaReader = new DSAReader();

    @Test
    public void readAndWrite2048BitKeyPair_asString() throws IOException {

        final KeyPair expectedKeyPair = DSAKeyGenerator.generate(2048);
        assertThat(expectedKeyPair, notNullValue());

        System.out.println("expectedKeyPair: " + expectedKeyPair);

        final String pem = DSAWriter.writeToPEM(expectedKeyPair);

        System.out.println("pem: " + pem);

        final KeyPair kp = dsaReader.readKeyPair(pem);

        System.out.println("kp: " + kp);

        assertThat(kp, notNullValue());

        assertThat(kp, DSA.equalTo(expectedKeyPair));

    }

    @Test
    public void readAndWrite2048BitKeyPair_asReader() throws IOException {

        final KeyPair expectedKeyPair = DSAKeyGenerator.generate(2048);
        assertThat(expectedKeyPair, notNullValue());

        final String pem = DSAWriter.writeToPEM(expectedKeyPair);

        final Reader reader = new StringReader(pem);

        final KeyPair kp = dsaReader.readKeyPair(reader);

        assertThat(kp, DSA.equalTo(expectedKeyPair));

    }

    @Test
    public void readAndWrite2048BitKeyPair_asInputStream() throws IOException {

        final KeyPair expectedKeyPair = DSAKeyGenerator.generate(2048);
        assertThat(expectedKeyPair, notNullValue());

        final String pem = DSAWriter.writeToPEM(expectedKeyPair);

        final InputStream is = new ByteArrayInputStream(GGStrings.toBytes(pem));

        final KeyPair kp = dsaReader.readKeyPair(is);

        assertThat(kp, DSA.equalTo(expectedKeyPair));

    }

    @Test
    public void readAndWrite1024BitKeyPair_asString() throws IOException {

        final KeyPair expectedKeyPair = DSAKeyGenerator.generate(1024);
        assertThat(expectedKeyPair, notNullValue());

        final String pem = DSAWriter.writeToPEM(expectedKeyPair);

        final KeyPair kp = dsaReader.readKeyPair(pem);

        assertThat(kp, DSA.equalTo(expectedKeyPair));

    }

    @Test
    public void readAndWrite1024BitKeyPair_asReader() throws IOException {

        final KeyPair expectedKeyPair = DSAKeyGenerator.generate(1024);
        assertThat(expectedKeyPair, notNullValue());

        final String pem = DSAWriter.writeToPEM(expectedKeyPair);

        final Reader reader = new StringReader(pem);

        final KeyPair kp = dsaReader.readKeyPair(reader);

        assertThat(kp, DSA.equalTo(expectedKeyPair));

    }

    @Test
    public void readAndWrite1024BitKeyPair_asInputStream() throws IOException {

        final KeyPair expectedKeyPair = DSAKeyGenerator.generate(1024);
        assertThat(expectedKeyPair, notNullValue());

        final String pem = DSAWriter.writeToPEM(expectedKeyPair);

        final InputStream is = new ByteArrayInputStream(GGStrings.toBytes(pem));

        final KeyPair kp = dsaReader.readKeyPair(is);

        assertThat(kp, DSA.equalTo(expectedKeyPair));

    }

    @Test
    public void readAndWrite2048BitPublicKey_asString() throws IOException {

        final KeyPair keyPair = DSAKeyGenerator.generate(2048);
        assertThat(keyPair, notNullValue());

        final PublicKey expectedPublicKey = keyPair.getPublic();

        final String pem = DSAWriter.writeToPEM(expectedPublicKey);

        final PublicKey publicKey = dsaReader.readPublicKey(pem);

        assertThat(publicKey, DSA.equalTo(expectedPublicKey));

    }

    @Test
    public void readAndWrite2048BitPublicKey_asReader() throws IOException {

        final KeyPair keyPair = DSAKeyGenerator.generate(2048);
        assertThat(keyPair, notNullValue());

        final PublicKey expectedPublicKey = keyPair.getPublic();

        final String pem = DSAWriter.writeToPEM(expectedPublicKey);

        final Reader reader = new StringReader(pem);

        final PublicKey publicKey = dsaReader.readPublicKey(reader);

        assertThat(publicKey, DSA.equalTo(expectedPublicKey));

    }

    @Test
    public void readAndWrite2048BitPublicKey_asInputStream() throws IOException {

        final KeyPair keyPair = DSAKeyGenerator.generate(2048);
        assertThat(keyPair, notNullValue());

        final PublicKey expectedPublicKey = keyPair.getPublic();

        final String pem = DSAWriter.writeToPEM(expectedPublicKey);

        final InputStream is = new ByteArrayInputStream(GGStrings.toBytes(pem));

        final PublicKey publicKey = dsaReader.readPublicKey(is);

        assertThat(publicKey, DSA.equalTo(expectedPublicKey));

    }

}
