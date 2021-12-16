package com.gantzgulch.logging.core;

import org.junit.Test;

public class FallbackTest {

    @Test
    public void testFallback() {
        
        final GGLogger LOG = GGLogger.getLogger(FallbackTest.class);
        
        LOG.warn("Fallback!");
        
    }
}
