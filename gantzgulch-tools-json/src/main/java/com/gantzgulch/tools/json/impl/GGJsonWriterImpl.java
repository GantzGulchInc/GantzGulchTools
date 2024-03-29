package com.gantzgulch.tools.json.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.gantzgulch.tools.json.GGJsonException;
import com.gantzgulch.tools.json.GGJsonWriter;

public final class GGJsonWriterImpl extends AbstractGGJsonImpl implements GGJsonWriter {

    public GGJsonWriterImpl(final boolean isPretty, final boolean useISO8601Dates) {

        super(false, isPretty, useISO8601Dates, null);

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

    @Override
    public JsonNode writeAsJsonNode(final Object value) {
        
        if( value == null ){
            return null;
        }

        return mapper.valueToTree(value);
    }

    @Override
    public byte[] writeAsBytes(final Object value, final String charsetName) {
        return writeAsBytes(value, Charset.forName(charsetName));
    }
    
    @Override
    public byte[] writeAsBytes(Object value, Charset charset) {
        
        
        try {

            final ObjectWriter objectWriter = mapper.writer();

            return value != null ? objectWriter.writeValueAsString(value).getBytes(charset) : null;

        } catch ( JsonProcessingException e) {
            throw new GGJsonException(e);
        }
    }
    
    @Override
    public void write(final Object value, final OutputStream outputStream) {
        
        try {

            final ObjectWriter objectWriter = mapper.writer();

            if( value != null ){
                objectWriter.writeValue(outputStream, value);
            }

        } catch (final IOException e) {
            throw new GGJsonException(e);
        }
    }
    
    @Override
    public void write(final Object value, final Path path) {

        try( final OutputStream os = Files.newOutputStream(path) ){
            write(value, os);
        }catch(final IOException e){
            throw new GGJsonException(e);
        }
    }


}
