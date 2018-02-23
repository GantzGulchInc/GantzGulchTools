package com.gantzgulch.tools.common.lang;

public final class GGArgs {

    private GGArgs() {
        throw new UnsupportedOperationException();
    }
    
    public static void notNull(final Object value, final String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(String.format("Field %s may not be null", fieldName));
        }
    }
    
    public static void notBlank(final String value, final String fieldName) {
        if (GGStrings.isBlank(value)) {
            throw new IllegalArgumentException(String.format("Field %s may not be blank", fieldName));
        }
    }
    
    public static <T> void isGreaterThan(final T value, final Comparable<T> comparison, final String field){
        
        if( value == null ){
            throw new IllegalArgumentException(String.format("Field %s must be greater than (%s) but is null", field, comparison) );
        }
        
        if( comparison.compareTo(value) >= 0 ){
            throw new IllegalArgumentException(String.format("Field %s must be greater than (%s) but is (%s)", field, comparison, value) );
        }
        
    }

}
