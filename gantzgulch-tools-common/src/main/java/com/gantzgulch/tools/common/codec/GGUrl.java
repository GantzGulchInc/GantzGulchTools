package com.gantzgulch.tools.common.codec;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.gantzgulch.tools.common.lang.GGUtf8;

public final class GGUrl {

    private GGUrl() {
        throw new UnsupportedOperationException();
    }

    public static String urlEncode(final String value) {
        
        if( value == null ){
            return null;
        }
        
        try {
            
            return URLEncoder.encode(value, GGUtf8.NAME);
            
        } catch (final UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
