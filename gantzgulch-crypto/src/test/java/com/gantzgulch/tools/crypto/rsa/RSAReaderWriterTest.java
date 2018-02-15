package com.gantzgulch.tools.crypto.rsa;

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

import com.gantzgulch.tools.common.lang.Strings;
import com.gantzgulch.tools.crypto.matchers.RSA;

public class RSAReaderWriterTest {

    private RSAGenerator rsaGenerator = new RSAGenerator();
    private RSAWriter rsaWriter = new RSAWriter();
    private RSAReader rsaReader = new RSAReader();
    
    @Test
    public void readAndWrite2048BitKeyPair_asString() throws IOException {
    
        final KeyPair expectedKeyPair = rsaGenerator.generate(2048);
        assertThat(expectedKeyPair, notNullValue());
        
        final String pem = rsaWriter.write(expectedKeyPair);
        
        final KeyPair kp = rsaReader.readKeyPair(pem);
        
        assertThat(kp, RSA.equalTo(expectedKeyPair));
        
    }

    @Test
    public void readAndWrite2048BitKeyPair_asReader() throws IOException {
    
        final KeyPair expectedKeyPair = rsaGenerator.generate(2048);
        assertThat(expectedKeyPair, notNullValue());
        
        final String pem = rsaWriter.write(expectedKeyPair);
        
        final Reader reader = new StringReader(pem);
        
        final KeyPair kp = rsaReader.readKeyPair( reader );
        
        assertThat(kp, RSA.equalTo(expectedKeyPair));
        
    }

    @Test
    public void readAndWrite2048BitKeyPair_asInputStream() throws IOException {
    
        final KeyPair expectedKeyPair = rsaGenerator.generate(2048);
        assertThat(expectedKeyPair, notNullValue());
        
        final String pem = rsaWriter.write(expectedKeyPair);
        
        final InputStream is = new ByteArrayInputStream( Strings.toBytes(pem) );
        
        final KeyPair kp = rsaReader.readKeyPair( is );
        
        assertThat(kp, RSA.equalTo(expectedKeyPair));
        
    }

    @Test
    public void readAndWrite4096BitKeyPair_asString() throws IOException {
    
        final KeyPair expectedKeyPair = rsaGenerator.generate(4096);
        assertThat(expectedKeyPair, notNullValue());
        
        final String pem = rsaWriter.write(expectedKeyPair);
        
        final KeyPair kp = rsaReader.readKeyPair(pem);
        
        assertThat(kp, RSA.equalTo(expectedKeyPair));
        
    }

    @Test
    public void readAndWrite4096BitKeyPair_asReader() throws IOException {
    
        final KeyPair expectedKeyPair = rsaGenerator.generate(4096);
        assertThat(expectedKeyPair, notNullValue());
        
        final String pem = rsaWriter.write(expectedKeyPair);
        
        final Reader reader = new StringReader(pem);
        
        final KeyPair kp = rsaReader.readKeyPair( reader );
        
        assertThat(kp, RSA.equalTo(expectedKeyPair));
        
    }

    @Test
    public void readAndWrite4096BitKeyPair_asInputStream() throws IOException {
    
        final KeyPair expectedKeyPair = rsaGenerator.generate(4096);
        assertThat(expectedKeyPair, notNullValue());
        
        final String pem = rsaWriter.write(expectedKeyPair);
        
        final InputStream is = new ByteArrayInputStream( Strings.toBytes(pem) );
        
        final KeyPair kp = rsaReader.readKeyPair( is );
        
        assertThat(kp, RSA.equalTo(expectedKeyPair));
        
    }

    
    
    
    
    @Test
    public void readAndWrite2048BitPublicKey_asString() throws IOException {
    
        final KeyPair keyPair = rsaGenerator.generate(2048);
        assertThat(keyPair, notNullValue());
        
        final PublicKey expectedPublicKey = keyPair.getPublic();
        
        final String pem = rsaWriter.write(expectedPublicKey);
        
        final PublicKey publicKey = rsaReader.readPublicKey(pem);
        
        assertThat(publicKey, RSA.equalTo(expectedPublicKey));
        
    }

    @Test
    public void readAndWrite2048BitPublicKey_asReader() throws IOException {
    
        final KeyPair keyPair = rsaGenerator.generate(2048);
        assertThat(keyPair, notNullValue());
        
        final PublicKey expectedPublicKey = keyPair.getPublic();
        
        final String pem = rsaWriter.write(expectedPublicKey);
        
        final Reader reader = new StringReader(pem);
        
        final PublicKey publicKey = rsaReader.readPublicKey( reader );
        
        assertThat(publicKey, RSA.equalTo(expectedPublicKey));
        
    }

    @Test
    public void readAndWrite2048BitPublicKey_asInputStream() throws IOException {
    
        final KeyPair keyPair = rsaGenerator.generate(2048);
        assertThat(keyPair, notNullValue());
        
        final PublicKey expectedPublicKey = keyPair.getPublic();
        
        final String pem = rsaWriter.write(expectedPublicKey);
        
        final InputStream is = new ByteArrayInputStream( Strings.toBytes(pem) );
        
        final PublicKey publicKey = rsaReader.readPublicKey( is );
        
        assertThat(publicKey, RSA.equalTo(expectedPublicKey));
        
    }


}
