package com.gantzgulch.tools.json;

import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import com.gantzgulch.tools.common.logging.GGLogger;
import com.gantzgulch.tools.gg.domain.GGUser;

public class GGJsonWriterTest {

    private static final GGLogger LOG = GGLogger.getLogger(GGJsonWriterTest.class);
    
    private GGJsonWriter writer = GGJsonWriters.PRETTY_ISO8601;
    private GGJsonReader reader = GGJsonReaders.STRICT_ISO8601;

    private GGUser user = new GGUser("54d89013-a7d9-46b3-940b-cf4a1cb78c1d", "First", "Last", "user@gantzgulch.com");

    @Test
    public void writeAsString() {

        final String json = writer.writeAsString(user);
    
        LOG.info("json: %s", json);
        
        final GGUser actualUser = reader.read(json, GGUser.class);
        
        assertThat(actualUser, GGUser.isUser(user));
    }
}

