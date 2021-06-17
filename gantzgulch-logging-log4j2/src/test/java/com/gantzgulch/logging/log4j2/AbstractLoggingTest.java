package com.gantzgulch.logging.log4j2;

import org.junit.Before;

import com.gantzgulch.logging.core.GGLogger;

public class AbstractLoggingTest {
    
    @Before
    public void before() {
        System.out.println("Before....");
        GGLogger.initialize();
    }
}
