package com.gantzgulch.tools.json;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

import java.io.InputStream;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;

public class GGJsonTest {

    private static final String RESOURCE = "/data/ggjson.json";
    
    private GGJsonReader reader = GGJsonReaders.STRICT;

    @Test
    public void testAttributes() {

        final InputStream is = getClass().getResourceAsStream(RESOURCE);

        final JsonNode node = reader.read(is, JsonNode.class);
        
        assertThat(GGJson.getAttributeAsInt(node, "int", 0), equalTo(1));
        assertThat(GGJson.getAttributeAsInt(node, "int_none", -1), equalTo(-1));
        
        assertThat(GGJson.getAttributeAsInteger(node, "obj1", "int"), equalTo(11));
        assertThat(GGJson.getAttributeAsInteger(node, "obj1", "int_none"), nullValue());
        assertThat(GGJson.getAttributeAsInteger(node, "obj1", "subObj", "another"), nullValue());
        
        assertThat(GGJson.getAttributeAsInteger(node, "obj2", "int"), equalTo(0));
        
        
    }

}

/*

{
    "id": "1",
    "string": "string1",
    "int": 1,
    "obj1": {
        "id": "1.1",
        "string": "string1.1",
        "int": 11
    },
    "obj2": {
        "id": "2.1",
        "string": "string2.1",
        "int": null
    },
    "obj3": {
        "id": "2.1",
        "string": "string2.1"
    }
    
}
*/