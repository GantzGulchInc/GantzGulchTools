package com.gantzgulch.logging.slf4j;

import org.junit.Before;

import com.gantzgulch.logging.core.GGLogger;

public class AbstractLoggingTest {
    
    @Before
    public void before() {
        GGLogger.initialize();
    }
}
