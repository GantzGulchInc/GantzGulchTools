package com.gantzgulch.tools.json;

import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.gantzgulch.tools.gg.domain.GGUser;

public class GGJsonWriterTest {

    private GGJsonWriter writer = GGJsonWriters.PRETTY;
    private GGJsonReader reader = GGJsonReaders.STRICT;

    private GGUser user = new GGUser("54d89013-a7d9-46b3-940b-cf4a1cb78c1d", "First", "Last", "user@gantzgulch.com");

    @Test
    public void writeAsString() {

        final String json = writer.writeAsString(user);
        
        final GGUser actualUser = reader.read(json, GGUser.class);
        
        assertThat(actualUser, GGUser.isUser(user));
    }
}

