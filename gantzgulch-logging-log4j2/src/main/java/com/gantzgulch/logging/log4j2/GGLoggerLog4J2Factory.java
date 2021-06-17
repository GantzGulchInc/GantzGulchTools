package com.gantzgulch.logging.log4j2;

import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.logging.core.impl.GGLoggerFactory;

public class GGLoggerLog4J2Factory implements GGLoggerFactory {

    @Override
    public void initialize() {
        System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
    }
    
    @Override
    public GGLogger create(final String name) {
        return new GGLoggerLog4j2(name);
    }

    @Override
    public String toString() {
        return "LoggerFactory: Log4j2";
    }

}
