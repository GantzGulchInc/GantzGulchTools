package com.gantzgulch.logging.core.impl;

public class GGLoggerUtil {

    public static String repeat(final String value, final int repeat) {
        
        final StringBuilder b = new StringBuilder(value.length() * repeat);
        
        for(int i=0; i<repeat; i++) {
            b.append(value);
        }
        
        return b.toString();
    }
    
   public static String center(final String value, final int width) {

       final int remaining = width - value.length();
       
       int padLeft = Math.max(0, remaining / 2 );
       
       int padRight = Math.max(0, remaining - padLeft);
       
       return repeat(" ", padLeft) + value + repeat(" ", padRight);
       
    }
}
