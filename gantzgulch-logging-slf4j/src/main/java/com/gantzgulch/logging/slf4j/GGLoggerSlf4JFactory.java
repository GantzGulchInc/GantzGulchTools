package com.gantzgulch.logging.slf4j;

import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.logging.core.impl.GGLoggerFactory;

public class GGLoggerSlf4JFactory implements GGLoggerFactory {

    @Override
    public GGLogger create(final String name) {
        return new GGLoggerSLF4J(name);
    }

    @Override
    public String toString() {
        return "LoggerFactory: SLF4J";
    }

}
