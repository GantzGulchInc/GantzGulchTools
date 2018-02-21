package com.gantzgulch.tools.common.json;

import java.io.InputStream;

import com.fasterxml.jackson.databind.JsonNode;
import com.gantzgulch.tools.common.json.impl.GGJsonReaderImpl;

public interface GGJsonReader {

    public static GGJsonReader STRICT = new GGJsonReaderImpl(false, false, false);
    
    public static GGJsonReader STRICT_MILLIS = new GGJsonReaderImpl(false, false, true);
    
    public static GGJsonReader STRICT_PRETTY = new GGJsonReaderImpl(false, true, false);
    
    public static GGJsonReader LOOSE = new GGJsonReaderImpl(true, false, false);
    
    public static GGJsonReader LOOSE_PRETTY = new GGJsonReaderImpl(true, true, false);

    JsonNode read(final String json);
    
    <T> T read(final InputStream json, Class<T> clazz);

    <T> T read(final String json, Class<T> clazz);

    <T> T read(final JsonNode json, Class<T> clazz);
    
}
