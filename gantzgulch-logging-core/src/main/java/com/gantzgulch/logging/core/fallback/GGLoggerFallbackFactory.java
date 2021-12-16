package com.gantzgulch.logging.core.fallback;

import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.logging.core.impl.GGLoggerFactory;

public class GGLoggerFallbackFactory implements GGLoggerFactory {

    @Override
    public void initialize() {
        
        System.out.println("-==============================================-");
        System.out.println("- WARNING: No logging library found.           -");
        System.out.println("-          Reverting to fallback library.      -");
        System.out.println("-==============================================-");
        
    }

    @Override
    public GGLogger create(final String name) {
        return new GGLoggerFallback(name);
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }
    
    @Override
    public String toString() {
        return "LoggerFactory: GG Fallback logger.";
    }
}
