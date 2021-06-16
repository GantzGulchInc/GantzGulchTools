package com.gantzgulch.logging.log4j2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class Log4J2CommonsTest extends AbstractLoggingTest {

    @Test
    public void testLogCommons() {

        final Log log = LogFactory.getLog(Log4J2CommonsTest.class);
        
        log.info("testLogCommons: It's commons logging!");
    }
}
