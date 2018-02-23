package com.gantzgulch.tools.json;

import java.io.InputStream;

import com.fasterxml.jackson.databind.JsonNode;

public interface GGJsonReader {

    JsonNode read(final String json);
    
    <T> T read(final InputStream json, Class<T> clazz);

    <T> T read(final String json, Class<T> clazz);

    <T> T read(final JsonNode json, Class<T> clazz);
    
}
