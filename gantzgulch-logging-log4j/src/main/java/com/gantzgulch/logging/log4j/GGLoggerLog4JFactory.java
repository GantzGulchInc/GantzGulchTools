package com.gantzgulch.logging.log4j;

import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.logging.core.impl.GGLoggerFactory;

public class GGLoggerLog4JFactory implements GGLoggerFactory {

    @Override
    public GGLogger create(final String name) {
        return new GGLoggerLog4J(name);
    }

    @Override
    public void initialize() {
        // Nothing to do at the moment.
    }
    
    @Override
    public String toString() {
        return "LoggerFactory: Log4j";
    }

}
