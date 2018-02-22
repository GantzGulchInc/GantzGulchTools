package com.gantzgulch.tools.json.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.gantzgulch.tools.json.GGJsonException;
import com.gantzgulch.tools.json.GGJsonWriter;

public final class GGJsonWriterImpl extends AbstractGGJsonImpl implements GGJsonWriter {

    public GGJsonWriterImpl(final boolean allowUncleanJson, final boolean isPretty, final boolean includeMillis) {

        super(allowUncleanJson, isPretty, includeMillis);

    }

    @Override
    public String writeAsString(final Object value) {
        
        try {

            final ObjectWriter objectWriter = mapper.writer();

            return value != null ? objectWriter.writeValueAsString(value) : null;

        } catch ( JsonProcessingException e) {
            throw new GGJsonException(e);
        }
    }


}
