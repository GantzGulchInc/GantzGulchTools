package com.gantzgulch.tools.json;

import java.io.OutputStream;

import com.fasterxml.jackson.databind.JsonNode;

public interface GGJsonWriter {

    String writeAsString(final Object value);

    JsonNode writeAsJsonNode(final Object value);

    void write(Object value, OutputStream output);
}
