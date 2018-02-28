package com.gantzgulch.tools.json;

import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.gantzgulch.tools.common.lang.GGIO;
import com.gantzgulch.tools.gg.domain.GGUser;

public class GGJsonReaderTest {

    private static final String USER_RESOURCE = "/data/user.json";
    
    private GGJsonReader reader = GGJsonReaders.STRICT;

    private GGUser user = new GGUser("54d89013-a7d9-46b3-940b-cf4a1cb78c1d", "First", "Last", "user@gantzgulch.com");

    @Test
    public void readObjectInputStream_withClass() {

        final InputStream is = getClass().getResourceAsStream(USER_RESOURCE);

        final GGUser result = reader.read(is, GGUser.class);
        
        assertThat(result, GGUser.isUser(user));
    }

    @Test
    public void readObjectStream_withClass() throws IOException {

        final String json = GGIO.readResourceToString(getClass(), USER_RESOURCE);

        final GGUser result = reader.read(json, GGUser.class);
        
        assertThat(result, GGUser.isUser(user));
    }

    @Test
    public void readObjectInputStream_withTypeReference() {

        final InputStream is = getClass().getResourceAsStream(USER_RESOURCE);

        final TypeReference<GGUser> typeRef = new TypeReference<GGUser>() {};
        
        final GGUser result = reader.read(is, typeRef);
        
        assertThat(result, GGUser.isUser(user));
    }

    @Test
    public void readObjectStream_withTypeReference() throws IOException {

        final String json = GGIO.readResourceToString(getClass(), USER_RESOURCE);

        final TypeReference<GGUser> typeRef = new TypeReference<GGUser>() {};
        
        final GGUser result = reader.read(json, typeRef);
        
        assertThat(result, GGUser.isUser(user));
    }
    
}
