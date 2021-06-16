package com.gantzgulch.logging.core;

public class GGLoggerUtil {

    public static String repeat(final String value, final int repeat) {
        
        final StringBuilder b = new StringBuilder(value.length() * repeat);
        
        for(int i=0; i<repeat; i++) {
            b.append(value);
        }
        
        return b.toString();
    }
    
   public static String center(final String value, final int width) {
        
       return value;
       
    }
}
