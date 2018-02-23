package com.gantzgulch.tools.json;

import java.io.InputStream;

import com.fasterxml.jackson.databind.JsonNode;
import com.gantzgulch.tools.json.impl.GGJsonReaderImpl;

public interface GGJsonReader {

    public static GGJsonReader STRICT = new GGJsonReaderImpl(false, false);
    
    public static GGJsonReader STRICT_ISO8601 = new GGJsonReaderImpl(false, true);
    
    public static GGJsonReader LOOSE = new GGJsonReaderImpl(true, false);
    
    public static GGJsonReader LOOSE_ISO8601 = new GGJsonReaderImpl(true, true);

    JsonNode read(final String json);
    
    <T> T read(final InputStream json, Class<T> clazz);

    <T> T read(final String json, Class<T> clazz);

    <T> T read(final JsonNode json, Class<T> clazz);
    
}
