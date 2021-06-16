package com.gantzgulch.tools.crypto.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class GGNonceTest {


    @Rule
    public final ExpectedException exception = ExpectedException.none();
    
    @Test
    public void nonce() {
        
        final byte[] data = GGNonceImpl.RANDOM.nonce(4);
        
        assertThat(data, notNullValue());
        assertThat(data.length, equalTo(4));
        
    }
    
    @Test
    public void nonceException() {

        exception.expect(IllegalArgumentException.class);
                
        GGNonceImpl.RANDOM.nonce(-4);
        
    }
}
