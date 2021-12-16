package com.gantzgulch.logging.commons;

import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.logging.core.impl.GGLoggerFactory;

public class GGLoggerApacheCommonsFactory implements GGLoggerFactory {

    @Override
    public void initialize() {
        // Nothing to do at the moment.
    }
    
    @Override
    public GGLogger create(final String name) {
        return new GGLoggerApacheCommons(name);
    }

    @Override
    public int getPriority() {
        return 100;
    }
    
    @Override
    public String toString() {
        return "LoggerFactory: Apache Commons";
    }
}
