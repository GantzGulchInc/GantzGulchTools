package com.gantzgulch.logging.log4j2;

public class AbstractLoggingTest {

    public void before() {
        System.out.println("Before!");
        GGLoggerLog4j2.initSystem();
    }
    
}
