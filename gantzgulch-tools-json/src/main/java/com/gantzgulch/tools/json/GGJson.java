package com.gantzgulch.tools.json;

import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.TypeFactory;

public final class GGJson {

    private static final ObjectMapper mapper = new ObjectMapper();

    private GGJson() {
        throw new UnsupportedOperationException();
    }

    public static <T> TypeReference<T> typeRef() {
        return new TypeReference<T>() {
        };
    }

    public static TypeFactory typeFactory() {
        return mapper.getTypeFactory();
    }

    public static ObjectNode createObjectNode() {
        return mapper.createObjectNode();
    }

    public static ArrayNode createArrayNode() {
        return mapper.createArrayNode();
    }

    public static String getAttribute(final JsonNode node, final String name) {

        if (node == null) {
            return null;
        }

        final JsonNode propertyNode = node.get(name);

        return propertyNode != null ? propertyNode.textValue() : null;

    }

    public static String getAttributeAsText(final JsonNode jsonNode, final String fieldName) {

        return findNode(jsonNode, fieldName) //
                .map(JsonNode::asText) //
                .orElse(null);

    }

    public static int getAttributeAsInt(final JsonNode jsonNode, final String fieldName, final int defaultValue) {

        return findNode(jsonNode, fieldName) //
                .map(JsonNode::asInt) //
                .orElse(defaultValue);

    }

    public static String getAttributeAsText(final JsonNode jsonNode, String... nodeNames) {

        return findNode(jsonNode, nodeNames) //
                .map(JsonNode::asText) //
                .orElse(null);

    }

    public static Integer getAttributeAsInteger(final JsonNode jsonNode, String... nodeNames) {

        return findNode(jsonNode, nodeNames) //
                .map(JsonNode::asInt) //
                .orElse(null);
    }

    public static JsonNode createIntNode(final int value) {

        return new IntNode(value);
    }

    public static Optional<JsonNode> findNode(final JsonNode node, final String... nodeNames) {

        JsonNode currentNode = node;

        for (final String nodeName : nodeNames) {

            if (currentNode == null) {
                break;
            }

            currentNode = currentNode.get(nodeName);

        }

        return Optional.ofNullable(currentNode);
    }

}
