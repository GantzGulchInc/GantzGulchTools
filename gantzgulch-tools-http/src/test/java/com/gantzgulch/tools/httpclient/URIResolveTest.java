package com.gantzgulch.tools.httpclient;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIUtils;
import org.junit.Test;

import com.gantzgulch.logging.core.GGLogger;

public class URIResolveTest {

    private static final GGLogger LOG = GGLogger.getLogger(URIResolveTest.class);

    @Test
    public void resolveUris() throws URISyntaxException {

        final URI base = new URI("http://www.gantzgulch.com/api/test");

        assertUri(URIUtils.resolve(base, "/rest/path?option=1"), "http://www.gantzgulch.com/rest/path?option=1");
        assertUri(URIUtils.resolve(base, "?option=2"), "http://www.gantzgulch.com/api/test?option=2");
        assertUri(URIUtils.resolve(base, "http://www.google.com/search?q=GantzGulch"), "http://www.google.com/search?q=GantzGulch");
        assertUri(URIUtils.resolve(base, "deep/path?option=1"), "http://www.gantzgulch.com/api/deep/path?option=1");

    }
    
    private void assertUri(final URI uri, final String expected) {
        
        LOG.debug("assertURi: uri: %s, expected: %s", uri, expected);
        
        assertThat(uri.toString(), equalTo(expected));
    }
}
