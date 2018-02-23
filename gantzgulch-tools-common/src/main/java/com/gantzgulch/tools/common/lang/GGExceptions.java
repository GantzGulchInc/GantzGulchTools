package com.gantzgulch.tools.common.lang;

public final class GGExceptions {

    private GGExceptions() {
        throw new UnsupportedOperationException();
    }

    public static String createMessageStack(final Throwable e){
        
        final StringBuilder b = new StringBuilder();
        
        if( e != null ){
            
            Throwable local = e;
            
            while( local != null ){
                
                if( b.length() != 0 ){
                    b.append(" ==> ");
                }
                
                b.append(e.getMessage());
                
                local = local.getCause();
            }
            
        }
        
        return b.toString();
    }
}
