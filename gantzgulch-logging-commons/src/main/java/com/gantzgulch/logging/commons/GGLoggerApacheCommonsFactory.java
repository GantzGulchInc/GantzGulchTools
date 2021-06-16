package com.gantzgulch.logging.commons;

import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.logging.core.impl.GGLoggerFactory;

public class GGLoggerApacheCommonsFactory implements GGLoggerFactory {

    @Override
    public GGLogger create(final String name) {
        return new GGLoggerApacheCommons(name);
    }

    @Override
    public String toString() {
        return "LoggerFactory: Apache Commons";
    }
}
