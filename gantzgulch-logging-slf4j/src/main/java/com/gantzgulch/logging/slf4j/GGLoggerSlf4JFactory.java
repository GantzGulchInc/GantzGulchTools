package com.gantzgulch.logging.slf4j;

import org.slf4j.bridge.SLF4JBridgeHandler;

import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.logging.core.impl.GGLoggerFactory;

public class GGLoggerSlf4JFactory implements GGLoggerFactory {

    @Override
    public void initialize() {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }
    
    @Override
    public GGLogger create(final String name) {
        return new GGLoggerSLF4J(name);
    }

    @Override
    public int getPriority() {
        return 50;
    }
    
    @Override
    public String toString() {
        return "LoggerFactory: SLF4J";
    }

}
