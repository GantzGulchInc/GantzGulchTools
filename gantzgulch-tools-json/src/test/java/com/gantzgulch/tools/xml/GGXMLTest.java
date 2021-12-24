package com.gantzgulch.tools.xml;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class GGXMLTest {


    private Document doc;
    
    @Before
    public void begin() throws ParserConfigurationException, SAXException, IOException {
        
        doc = GGXML.parseXML(getClass().getResourceAsStream("/data/data.xml"));
        
    }

    @Test
    public void testFindElementNulls() {
        
        assertThat(doc, notNullValue());
     
        assertThat( GGXML.findFirstNode((Node)null, (String[])null), nullValue());
        assertThat( GGXML.findFirstNode((Node)null, "first", "second"), nullValue());
        assertThat( GGXML.findFirstNode(doc.getChildNodes().item(0), (String[])null), nullValue());
    }
    
    @Test
    public void testFindElementNode() {
        
        assertThat(doc, notNullValue());
        assertThat( GGXML.findFirstNode(doc, "root"), notNullValue());
        assertThat( GGXML.findFirstNode(doc, "root", "data1"), notNullValue());
        assertThat( GGXML.findFirstNode(doc, "root", "data1", "first"), notNullValue());
        assertThat( GGXML.findFirstNode(doc, "root", "data1", "third"), notNullValue());
        
        assertThat( GGXML.findFirstNode(doc, "root", "data1", "first").getTextContent(), equalTo("1st"));
        
        assertThat( GGXML.findFirstNode(doc, "root", "data1", "third").getTextContent(), equalTo("3rd"));
        
    }
    
    @Test
    public void testFindElementNodeList() {
        
        assertThat(doc, notNullValue());
        assertThat( GGXML.findFirstNode(doc.getChildNodes(), "root"), notNullValue());
        assertThat( GGXML.findFirstNode(doc.getChildNodes(), "root", "data1"), notNullValue());
        assertThat( GGXML.findFirstNode(doc.getChildNodes(), "root", "data1", "first"), notNullValue());
        assertThat( GGXML.findFirstNode(doc.getChildNodes(), "root", "data1", "third"), notNullValue());
        
        assertThat( GGXML.findFirstNode(doc.getChildNodes(), "root", "data1", "first").getTextContent(), equalTo("1st"));
        
        assertThat( GGXML.findFirstNode(doc.getChildNodes(), "root", "data1", "third").getTextContent(), equalTo("3rd"));
        
    }

}
