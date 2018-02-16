package com.gantzgulch.tools.common.lang;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class GGIO {

    private GGIO() {
        throw new UnsupportedOperationException();
    }
    
    public static byte[] read(final InputStream is) throws IOException{
        
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        final byte[] buffer = new byte[1024 * 1024];
        
        int len = 0;
        
        while( (len = is.read(buffer)) >= 0 ){
            baos.write(buffer, 0, len);
        }

        return baos.toByteArray();
    }
}
