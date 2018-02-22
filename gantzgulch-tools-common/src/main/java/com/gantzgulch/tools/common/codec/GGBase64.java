package com.gantzgulch.tools.common.codec;

import java.util.Base64;
import java.util.Base64.Encoder;

public final class GGBase64 {

    
    private GGBase64() {
        throw new UnsupportedOperationException();
    }
    
    public static String toBase64String(final byte[] bytes) {
        
        return toBase64String(bytes, false);
        
    }
    
    public static String toBase64String(final byte[] bytes, boolean urlFriendly) {
        
        if( bytes == null ){
            return null;
        }
        
        return getBase64Encoder(urlFriendly).encodeToString(bytes);
        
    }
    
    public static byte[] fromBase64String(final String base64){
        
        if( base64 == null ){
            return null;
        }
        
        return Base64.getDecoder().decode(base64);
    }
    
    private static Encoder getBase64Encoder(final boolean urlFriendly) {
        
        return urlFriendly ? Base64.getUrlEncoder() : Base64.getEncoder();
    }
}
