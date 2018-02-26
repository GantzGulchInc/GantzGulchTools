package com.gantzgulch.tools.json;

import java.io.InputStream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

public interface GGJsonReader {

    JsonNode read(String json);
    
    <T> T read(InputStream json, Class<T> clazz);

    <T> T read(String json, Class<T> clazz);

    <T> T read(JsonNode json, Class<T> clazz);

    <T> T read(InputStream json, TypeReference<T> typeRef);

    <T> T read(String json, TypeReference<T> typeRef);

    <T> T read(JsonNode json, TypeReference<T> typeRef);
    
}
