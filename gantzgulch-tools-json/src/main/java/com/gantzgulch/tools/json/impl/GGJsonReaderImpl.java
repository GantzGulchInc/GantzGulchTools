package com.gantzgulch.tools.json.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.gantzgulch.tools.json.GGJsonException;
import com.gantzgulch.tools.json.GGJsonReader;

public final class GGJsonReaderImpl extends AbstractGGJsonImpl implements GGJsonReader {

    public GGJsonReaderImpl(final boolean allowUncleanJson, final boolean useISO8601Dates) {

        super(allowUncleanJson, false, useISO8601Dates);
    }

    @Override
    public TypeFactory getTypeFactory() {
        
        return mapper.getTypeFactory();
    }
    
    @Override
    public JsonNode read(final String json) {

        try {

            ObjectReader objectReader = mapper.reader();

            return json != null ? objectReader.readTree(json) : null;

        } catch (final IOException e) {
            throw new GGJsonException(e);
        }

    }

    @Override
    public <T> T read(final InputStream json, final Class<T> clazz) {

        return read(json, mapper.getTypeFactory().constructType(clazz) );

    }

    @Override
    public <T> T read(final String json, final Class<T> clazz) {

        return read(json, mapper.getTypeFactory().constructType(clazz) );

    }

    @Override
    public <T> List<T> readArray(final String json, final Class<T> clazz) {

        return read(json, mapper.getTypeFactory().constructCollectionType(List.class, clazz)) ;

    }

    @Override
    public <T> T read(final JsonNode json, final Class<T> clazz) {

        return read(json, mapper.getTypeFactory().constructType(clazz) );
        
    }

    @Override
    public <T> T read(final Path path, final Class<T> clazz) {

        if (path == null) {
            return null;
        }

        try (final InputStream is = Files.newInputStream(path)) {
            return read(is, clazz);
        } catch (final IOException e) {
            throw new GGJsonException(e);
        }
    }

    
    
    @Override
    public <T> T read(final InputStream json, final TypeReference<T> typeRef) {

        return read(json, mapper.getTypeFactory().constructType(typeRef));

    }

    @Override
    public <T> T read(final String json, final TypeReference<T> typeRef) {

        return read(json, mapper.getTypeFactory().constructType(typeRef));

    }

    @Override
    public <T> T read(final JsonNode json, final TypeReference<T> typeRef) {

        return read(json, mapper.getTypeFactory().constructType(typeRef));

    }

    @Override
    public <T> T read(final Path path, final TypeReference<T> typeRef) {

        if (path == null) {
            return null;
        }

        try (final InputStream is = Files.newInputStream(path)) {
            return read(is, typeRef);
        } catch (final IOException e) {
            throw new GGJsonException(e);
        }
    }

    
    
    
    
    @Override
    public <T> T read(final InputStream json, final JavaType javaType) {

        try {

            ObjectReader objectReader = mapper.readerFor(javaType);

            return json != null ? objectReader.readValue(json) : null;

        } catch (final IOException e) {
            throw new GGJsonException(e);
        }
    }

    @Override
    public <T> T read(final String json, final JavaType javaType) {

        try {

            ObjectReader objectReader = mapper.readerFor(javaType);

            return json != null ? objectReader.readValue(json) : null;

        } catch (final IOException e) {
            throw new GGJsonException(e);
        }

    }

    @Override
    public <T> T read(final JsonNode json, final JavaType javaType) {

        try {

            ObjectReader objectReader = mapper.readerFor(javaType);

            return json != null ? objectReader.readValue(json) : null;

        } catch (final IOException e) {
            throw new GGJsonException(e);
        }
    }

    @Override
    public <T> T read(final Path path, final JavaType javaType) {

        if (path == null) {
            return null;
        }

        try (final InputStream is = Files.newInputStream(path)) {
            return read(is, javaType);
        } catch (final IOException e) {
            throw new GGJsonException(e);
        }
    }

}
