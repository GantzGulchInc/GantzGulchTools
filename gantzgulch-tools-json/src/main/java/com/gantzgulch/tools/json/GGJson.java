package com.gantzgulch.tools.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public final class GGJson {

    private static final ObjectMapper mapper = new ObjectMapper();

    private GGJson() {
        throw new UnsupportedOperationException();
    }

    public static ObjectNode createObjectNode() {
        return mapper.createObjectNode();
    }

    public static ArrayNode createArrayNode() {
        return mapper.createArrayNode();
    }

    public static String getAttribute(final JsonNode node, final String name) {

        if( node == null ){
            return null;
        }
        
        final JsonNode propertyNode = node.get(name);

        return propertyNode != null ? propertyNode.textValue() : null;

    }

    public static String getAttributeAsText(final JsonNode jsonNode, final String fieldName) {

        final JsonNode fieldNode = jsonNode != null ? jsonNode.get(fieldName) : null;

        return fieldNode != null ? fieldNode.asText() : null;

    }

    public static int getAttributeAsInt(final JsonNode jsonNode, final String fieldName, final int defaultValue) {

        final JsonNode fieldNode = jsonNode != null ? jsonNode.get(fieldName) : null;

        return fieldNode != null ? fieldNode.asInt() : defaultValue;

    }

    public static String getAttributeAsText(final JsonNode jsonNode, String... nodeNames) {

        JsonNode currentNode = jsonNode;

        for (final String nodeName : nodeNames) {

            if (currentNode != null) {
                currentNode = currentNode.get(nodeName);
            }

        }

        return currentNode != null ? currentNode.asText() : "";
    }

    public static JsonNode createIntNode(final int value) {
        
        return new IntNode(value);
    }


}
