package com.gantzgulch.tools.crypto.alg.rsa;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.security.KeyPair;
import java.security.PublicKey;

import org.junit.Test;

import com.gantzgulch.tools.common.lang.GGStrings;
import com.gantzgulch.tools.crypto.alg.rsa.RSAKeyGenerator;
import com.gantzgulch.tools.crypto.alg.rsa.RSAReader;
import com.gantzgulch.tools.crypto.alg.rsa.RSAWriter;
import com.gantzgulch.tools.crypto.matchers.RSA;

public class RSAReaderWriterTest {

    @Test
    public void readAndWrite2048BitKeyPair_asString() throws IOException {
    
        final KeyPair expectedKeyPair = RSAKeyGenerator.generate(2048);
        assertThat(expectedKeyPair, notNullValue());
        
        final String pem = RSAWriter.writeToPEM(expectedKeyPair);
        
        final KeyPair kp = RSAReader.readKeyPair(pem);
        
        assertThat(kp, RSA.equalTo(expectedKeyPair));
        
    }

    @Test
    public void readAndWrite2048BitKeyPair_asReader() throws IOException {
    
        final KeyPair expectedKeyPair = RSAKeyGenerator.generate(2048);
        assertThat(expectedKeyPair, notNullValue());
        
        final String pem = RSAWriter.writeToPEM(expectedKeyPair);
        
        final Reader reader = new StringReader(pem);
        
        final KeyPair kp = RSAReader.readKeyPair( reader );
        
        assertThat(kp, RSA.equalTo(expectedKeyPair));
        
    }

    @Test
    public void readAndWrite2048BitKeyPair_asInputStream() throws IOException {
    
        final KeyPair expectedKeyPair = RSAKeyGenerator.generate(2048);
        assertThat(expectedKeyPair, notNullValue());
        
        final String pem = RSAWriter.writeToPEM(expectedKeyPair);
        
        final InputStream is = new ByteArrayInputStream( GGStrings.toBytes(pem) );
        
        final KeyPair kp = RSAReader.readKeyPair( is );
        
        assertThat(kp, RSA.equalTo(expectedKeyPair));
        
    }

    @Test
    public void readAndWrite4096BitKeyPair_asString() throws IOException {
    
        final KeyPair expectedKeyPair = RSAKeyGenerator.generate(4096);
        assertThat(expectedKeyPair, notNullValue());
        
        final String pem = RSAWriter.writeToPEM(expectedKeyPair);
        
        final KeyPair kp = RSAReader.readKeyPair(pem);
        
        assertThat(kp, RSA.equalTo(expectedKeyPair));
        
    }

    @Test
    public void readAndWrite4096BitKeyPair_asReader() throws IOException {
    
        final KeyPair expectedKeyPair = RSAKeyGenerator.generate(4096);
        assertThat(expectedKeyPair, notNullValue());
        
        final String pem = RSAWriter.writeToPEM(expectedKeyPair);
        
        final Reader reader = new StringReader(pem);
        
        final KeyPair kp = RSAReader.readKeyPair( reader );
        
        assertThat(kp, RSA.equalTo(expectedKeyPair));
        
    }

    @Test
    public void readAndWrite4096BitKeyPair_asInputStream() throws IOException {
    
        final KeyPair expectedKeyPair = RSAKeyGenerator.generate(4096);
        assertThat(expectedKeyPair, notNullValue());
        
        final String pem = RSAWriter.writeToPEM(expectedKeyPair);
        
        final InputStream is = new ByteArrayInputStream( GGStrings.toBytes(pem) );
        
        final KeyPair kp = RSAReader.readKeyPair( is );
        
        assertThat(kp, RSA.equalTo(expectedKeyPair));
        
    }

    
    
    
    
    @Test
    public void readAndWrite2048BitPublicKey_asString() throws IOException {
    
        final KeyPair keyPair = RSAKeyGenerator.generate(2048);
        assertThat(keyPair, notNullValue());
        
        final PublicKey expectedPublicKey = keyPair.getPublic();
        
        final String pem = RSAWriter.writeToPEM(expectedPublicKey);
        
        final PublicKey publicKey = RSAReader.readPublicKey(pem);
        
        assertThat(publicKey, RSA.equalTo(expectedPublicKey));
        
    }

    @Test
    public void readAndWrite2048BitPublicKey_asReader() throws IOException {
    
        final KeyPair keyPair = RSAKeyGenerator.generate(2048);
        assertThat(keyPair, notNullValue());
        
        final PublicKey expectedPublicKey = keyPair.getPublic();
        
        final String pem = RSAWriter.writeToPEM(expectedPublicKey);
        
        final Reader reader = new StringReader(pem);
        
        final PublicKey publicKey = RSAReader.readPublicKey( reader );
        
        assertThat(publicKey, RSA.equalTo(expectedPublicKey));
        
    }

    @Test
    public void readAndWrite2048BitPublicKey_asInputStream() throws IOException {
    
        final KeyPair keyPair = RSAKeyGenerator.generate(2048);
        assertThat(keyPair, notNullValue());
        
        final PublicKey expectedPublicKey = keyPair.getPublic();
        
        final String pem = RSAWriter.writeToPEM(expectedPublicKey);
        
        final InputStream is = new ByteArrayInputStream( GGStrings.toBytes(pem) );
        
        final PublicKey publicKey = RSAReader.readPublicKey( is );
        
        assertThat(publicKey, RSA.equalTo(expectedPublicKey));
        
    }


}
