package com.gantzgulch.logging.slf4j;

import java.util.logging.Logger;

import org.junit.Test;

public class Log4J2JulTest extends AbstractLoggingTest {

    @Test
    public void testLogJul() {
        
        final Logger log = Logger.getLogger(Log4J2JulTest.class.getName());
        
        log.info("testLogJul - It's JUL!");
    }
}
