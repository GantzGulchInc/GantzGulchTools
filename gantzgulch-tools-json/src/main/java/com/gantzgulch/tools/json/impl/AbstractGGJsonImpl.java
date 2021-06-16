package com.gantzgulch.tools.json.impl;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.gantzgulch.logging.core.GGLogger;

public abstract class AbstractGGJsonImpl {

    protected final GGLogger LOG = GGLogger.getLogger(getClass());
    
    protected final ObjectMapper mapper;

    public AbstractGGJsonImpl(final boolean allowUncleanJson, final boolean isPretty, final boolean useISO8601Dates) {

        mapper = new ObjectMapper();

        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, allowUncleanJson);

        mapper.configure(JsonParser.Feature.STRICT_DUPLICATE_DETECTION, ! allowUncleanJson);

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, ! allowUncleanJson);

        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        mapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);

        mapper.setSerializationInclusion(Include.NON_NULL);

        if( isPretty ){
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
        }else{
            mapper.disable(SerializationFeature.INDENT_OUTPUT);
        }

        if( useISO8601Dates ){
            mapper.setDateFormat(new ISO8601WithMillisFormat());
        }


    }

}
