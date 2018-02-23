package com.gantzgulch.tools.common.lang;

public final class Arguments {

    private Arguments() {
        throw new UnsupportedOperationException();
    }
    
    public static void isNotNull(final Object value, final String message){
        
        if( value == null ){
            throw new IllegalArgumentException(message);
        }
        
    }
    
    public static void isGreaterThan(final int value, final int comparison, final String message){
        
        if( value <= comparison){
            throw new IllegalArgumentException(message);
        }
        
    }

}