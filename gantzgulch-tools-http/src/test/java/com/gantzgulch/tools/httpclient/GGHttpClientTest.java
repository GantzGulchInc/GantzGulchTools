package com.gantzgulch.tools.httpclient;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.net.URI;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.protocol.HttpClientContext;
import org.junit.Test;

import com.gantzgulch.tools.common.logging.GGLogger;
import com.gantzgulch.tools.httpclient.impl.GGHttpClientImpl;
import com.gantzgulch.tools.httpclient.impl.GGHttpResponses;

public class GGHttpClientTest {

    private static final GGLogger LOG = GGLogger.getLogger(GGHttpClientTest.class);
    
    private final GGHttpClient httpClient = new GGHttpClientImpl(null, false, 30000, 30000);

    @Test
    public void getGoogle() throws IOException {
        
        final HttpClientContext ctx = new HttpClientContext();
        
        final URI uri = URI.create("https://www.google.com");
        
        try( final CloseableHttpResponse r = httpClient.get(uri, null, ctx) ){
            
            final String body = GGHttpResponses.getStringContent(r);
            
            assertThat(body, notNullValue());
            
    
            LOG.debug("body: %s", body);
        }
    }
}
