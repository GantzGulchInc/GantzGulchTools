package com.gantzgulch.tools.common.codec;

import java.util.Base64;

public final class GGBase64 {

    
    private GGBase64() {
        throw new UnsupportedOperationException();
    }
    
    public static String toBase64String(final byte[] bytes) {
        
        if( bytes == null ){
            return null;
        }
        
        return Base64.getEncoder().encodeToString(bytes);
        
    }
    
    public static byte[] fromBase64String(final String base64){
        
        if( base64 == null ){
            return null;
        }
        
        return Base64.getDecoder().decode(base64);
    }
}
