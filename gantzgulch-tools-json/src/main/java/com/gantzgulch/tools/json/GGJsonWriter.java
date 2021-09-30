package com.gantzgulch.tools.json;

import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.JsonNode;

public interface GGJsonWriter {

    String writeAsString(Object value);

    JsonNode writeAsJsonNode(Object value);
    
    byte[] writeAsBytes(Object value, String charsetName);
    
    byte[] writeAsBytes(Object value, Charset charset);

    void write(Object value, OutputStream output);

    void write(Object value, Path path);
}
