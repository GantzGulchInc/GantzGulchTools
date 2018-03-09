package com.gantzgulch.tools.crypto.password;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.gantzgulch.tools.common.codec.GGHex;

public class PBKDF2HashTest {

    public static final String PB_HASH = "100:f2cd242f567f1cb0c4160127f997ea77:6efff7c912cad6162a0cfcbdb60c3c3392cddef4976dd3c7e05cc1dcf1a85a259607d39c37ec5ed744752de47dc8408d5364465f5b65bbfceee06c43da2887cb";
    public static final int ITERATIONS = 100;
    public static final byte[] SALT = GGHex.fromHexString("f2cd242f567f1cb0c4160127f997ea77");
    public static final byte[] HASH = GGHex.fromHexString("6efff7c912cad6162a0cfcbdb60c3c3392cddef4976dd3c7e05cc1dcf1a85a259607d39c37ec5ed744752de47dc8408d5364465f5b65bbfceee06c43da2887cb");

    @Test
    public void parse() {
        
        final PBKDF2Hash pbHash = new PBKDF2Hash(PB_HASH);
 
        assertThat(pbHash, notNullValue());
        
        assertThat(pbHash.getIterations(), equalTo(ITERATIONS));
        assertThat(pbHash.getSalt(), equalTo(SALT));
        assertThat(pbHash.getHash(), equalTo(HASH));
        
    }
    
    @Test
    public void format() {
        
        final PBKDF2Hash pbHash = new PBKDF2Hash(ITERATIONS, SALT, HASH);
 
        assertThat(pbHash, notNullValue());
        
        assertThat(pbHash.toStandardFormat(), equalTo(PB_HASH));
        
    }
}
