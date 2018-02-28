package com.gantzgulch.tools.json;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.type.TypeFactory;

public interface GGJsonReader {

    TypeFactory getTypeFactory();
    
    JsonNode read(String json);
    
    <T> T read(InputStream json, Class<T> clazz);

    <T> T read(String json, Class<T> clazz);

    <T> T read(JsonNode json, Class<T> clazz);

    <T> T read(Path path, Class<T> clazz);
    
    
    <T> T read(InputStream json, TypeReference<T> typeRef);

    <T> T read(String json, TypeReference<T> typeRef);

    <T> T read(JsonNode json, TypeReference<T> typeRef);

    <T> T read(Path path, TypeReference<T> typeRef);

    
    <T> T read(InputStream json, JavaType javaType);

    <T> T read(String json, JavaType javaType);

    <T> T read(JsonNode json, JavaType javaType);

    <T> T read(Path path, JavaType javaType);

    
    <T> List<T> readArray(String json, Class<T> clazz);

}
