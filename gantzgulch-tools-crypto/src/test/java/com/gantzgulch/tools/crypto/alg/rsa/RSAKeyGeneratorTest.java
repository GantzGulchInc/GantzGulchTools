package com.gantzgulch.tools.crypto.alg.rsa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.junit.Test;

public class RSAKeyGeneratorTest {

    @Test
    public void generate2048() {
    
        final KeyPair kp = RSAKeyGenerator.generate(2048);
        
        assertThat(kp, notNullValue());
        assertThat(kp.getPrivate().getAlgorithm(), equalTo("RSA"));
        assertThat(kp.getPrivate().getFormat(), equalTo("PKCS#8"));
        
        final RSAPrivateKey rsaPrivateKey = (RSAPrivateKey)kp.getPrivate();
        assertThat(rsaPrivateKey, notNullValue());
        assertThat(rsaPrivateKey.getModulus().bitLength(), equalTo(2048));
        
        final RSAPublicKey rsaPublicKey = (RSAPublicKey)kp.getPublic();
        assertThat(rsaPublicKey, notNullValue());
    }

    @Test
    public void generate4096() {
    
        final KeyPair kp = RSAKeyGenerator.generate(4096);
        
        assertThat(kp, notNullValue());
        assertThat(kp.getPrivate().getAlgorithm(), equalTo("RSA"));
        assertThat(kp.getPrivate().getFormat(), equalTo("PKCS#8"));

        final RSAPrivateKey rsaPrivateKey = (RSAPrivateKey)kp.getPrivate();
        assertThat(rsaPrivateKey, notNullValue());
        assertThat(rsaPrivateKey.getModulus().bitLength(), equalTo(4096));

        final RSAPublicKey rsaPublicKey = (RSAPublicKey)kp.getPublic();
        assertThat(rsaPublicKey, notNullValue());
    }
}
