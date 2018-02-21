package com.gantzgulch.tools.aws.iot.impl;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.gantzgulch.tools.common.logging.GGLogger;

public final class GGAwsIotJson {

    private static final ObjectMapper OBJECT_MAPPER = createObjectMapper();

    @SuppressWarnings("unused")
    private static final GGLogger LOG = GGLogger.getLogger(GGAwsIotJson.class);

    private GGAwsIotJson() {
        throw new UnsupportedOperationException();
    }

    public static JsonNode parseJson(final String json) throws IOException {

        ObjectReader objectReader = OBJECT_MAPPER.reader();

        return json != null ? objectReader.readTree(json) : null;

    }

    public static <T> T parseJson(final JsonNode json, Class<T> clazz) throws IOException {

        ObjectReader objectReader = OBJECT_MAPPER.reader();

        return json != null ? objectReader.treeToValue(json, clazz) : null;

    }

    public static <T> T parseJson(final String json, Class<T> clazz) throws IOException {

        ObjectReader objectReader = OBJECT_MAPPER.readerFor(clazz);

        return json != null ? objectReader.readValue(json) : null;

    }

    public static String writeAsString(final Object value) {

        try {

            final ObjectWriter objectWriter = OBJECT_MAPPER.writer();

            return value != null ? objectWriter.writeValueAsString(value) : null;

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObjectMapper createObjectMapper() {

        final ObjectMapper mapper = new ObjectMapper();

        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        mapper.configure(JsonParser.Feature.STRICT_DUPLICATE_DETECTION, true);

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        mapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);

        mapper.setSerializationInclusion(Include.NON_NULL);

        mapper.disable(SerializationFeature.INDENT_OUTPUT);

        return mapper;
    }


}
