package com.gantzgulch.logging.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class Log4J2Test extends AbstractLoggingTest {

    @Test
    public void testLog4J2() {

        final Logger log = LogManager.getLogger(Log4J2Test.class);

        log.info("testLog4J2 - It's Log4J2!");
    }
}
