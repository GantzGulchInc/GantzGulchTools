package com.gantzgulch.tools.crypto.alg.chacha20;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.security.Provider;
import java.security.Security;
import java.util.Enumeration;

import org.junit.Ignore;
import org.junit.Test;

import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.tools.crypto.BouncyCastleState;

public class ChaChaTest {

    private static final GGLogger LOG = GGLogger.getLogger(ChaChaTest.class);
    
    @Test
    public void testCrypt() {
        
        // final String ALGO = "ChaCha20-Poly1305";
        
        
    }
    
    @Test
    @Ignore
    public void basicTest() throws IOException {
    
        final Writer fw = new FileWriter("/tmp/prov.txt");
        final PrintWriter pw = new PrintWriter(fw);
        
        BouncyCastleState.init();
        
        Provider providers[] = Security.getProviders();
        
        for(final Provider provider : providers) {
        
            LOG.info("Provider ....: %s", provider);
        
            pw.format("Provider ....: %s\n", provider);
            
            for(Enumeration<Object> e = provider.keys(); e.hasMoreElements();) {
                
                final Object o = e.nextElement();
                
                LOG.info("    %s", o);
                
                pw.format("    %s\n", o);
            }
        
        
        }
        
        pw.flush();
        pw.close();
        
        
    }
}
