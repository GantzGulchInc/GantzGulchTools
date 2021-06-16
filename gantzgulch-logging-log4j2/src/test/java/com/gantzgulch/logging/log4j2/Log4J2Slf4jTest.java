package com.gantzgulch.logging.log4j2;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4J2Slf4jTest extends AbstractLoggingTest {

    @Test
    public void testLogSlf4j() {

        final Logger log = LoggerFactory.getLogger(Log4J2Slf4jTest.class);

        log.info("testLogSlf4j: It's SLF4J!");
    }
}
