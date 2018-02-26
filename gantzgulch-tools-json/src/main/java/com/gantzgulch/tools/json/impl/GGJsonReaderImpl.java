package com.gantzgulch.tools.json.impl;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectReader;
import com.gantzgulch.tools.json.GGJsonException;
import com.gantzgulch.tools.json.GGJsonReader;

public final class GGJsonReaderImpl extends AbstractGGJsonImpl implements GGJsonReader {

    public GGJsonReaderImpl(final boolean allowUncleanJson, final boolean useISO8601Dates) {

        super(allowUncleanJson, false, useISO8601Dates);
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

        try {

            ObjectReader objectReader = mapper.readerFor(clazz);

            return json != null ? objectReader.readValue(json) : null;

        } catch (final IOException e) {
            throw new GGJsonException(e);
        }
    }

    @Override
    public <T> T read(final String json, final Class<T> clazz) {

        try {

            ObjectReader objectReader = mapper.readerFor(clazz);

            return json != null ? objectReader.readValue(json) : null;

        } catch (final IOException e) {
            throw new GGJsonException(e);
        }

    }

    @Override
    public <T> T read(final JsonNode json, final Class<T> clazz) {

        try {

            ObjectReader objectReader = mapper.reader();

            return json != null ? objectReader.treeToValue(json, clazz) : null;

        } catch (final IOException e) {
            throw new GGJsonException(e);
        }
    }

    @Override
    public <T> T read(final InputStream json, final TypeReference<T> typeRef) {

        try {

            ObjectReader objectReader = mapper.readerFor(typeRef);

            return json != null ? objectReader.readValue(json) : null;

        } catch (final IOException e) {
            throw new GGJsonException(e);
        }
    }

    @Override
    public <T> T read(final String json, final TypeReference<T> typeRef) {

        try {

            ObjectReader objectReader = mapper.readerFor(typeRef);

            return json != null ? objectReader.readValue(json) : null;

        } catch (final IOException e) {
            throw new GGJsonException(e);
        }

    }

    @Override
    public <T> T read(final JsonNode json, final TypeReference<T> typeRef) {

        try {

            ObjectReader objectReader = mapper.readerFor(typeRef);

            return json != null ? objectReader.readValue(json) : null;

        } catch (final IOException e) {
            throw new GGJsonException(e);
        }
    }

}
