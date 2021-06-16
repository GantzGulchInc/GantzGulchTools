package com.gantzgulch.tools.crypto.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.tools.common.codec.GGBase64;
import com.gantzgulch.tools.common.lang.GGStrings;

public class OpenSslBase64InputStream extends InputStream {

 public static final GGLogger LOG = GGLogger.getLogger(OpenSslBase64InputStream.class);
    
    private BufferedReader reader;

    private byte[] current;
    private int currentIndex;
    
    public OpenSslBase64InputStream(final Reader reader) {
        this.reader = new BufferedReader(reader);
    }

    @Override
    public int read() throws IOException {
        
        if( bufferEmpty() ) {
            
            if( ! fillBuffer() ){
                
                return -1;
                
            }
        }
        
        final int c = current[currentIndex++] & 0xFF;
        
        return c;
    }

    private boolean bufferEmpty() {
        
        if( current == null ){
            return true;
        }
        
        return currentIndex == current.length;
    }
    
    private boolean fillBuffer() throws IOException {
        
        while(true) {
            
            final String data = reader.readLine();

            if( data == null ){
                return false;
            }
            
            if( GGStrings.isBlank(data) ){
                continue;
            }
            
            current = GGBase64.fromBase64String(data, false);
            currentIndex = 0;

            return true;
        }
        
    }

}
