package com.gantzgulch.tools.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.gantzgulch.tools.common.lang.GGStrings;

public final class GGXML {

   
    public static Document parse(final String xml) throws ParserConfigurationException, SAXException, IOException {
        return parseXML(GGStrings.toBytes(xml));
    }
    
    public static Document parseXML(final byte[] xml) throws ParserConfigurationException, SAXException, IOException {
        return parseXML( new ByteArrayInputStream(xml));
    }
    
    public static Document parseXML(final InputStream xml) throws ParserConfigurationException, SAXException, IOException {

        final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        final DocumentBuilder db = dbf.newDocumentBuilder();
        return db.parse(xml);
    }


    public static String findFirstNodeValue(final Node node, final String ... nodeNames) {
        
        final Node foundNode = findFirstNode(node, nodeNames);
        
        return foundNode != null ? foundNode.getTextContent() : null;
    }

    public static String findFirstNodeValue(final NodeList nodeList, final String ... nodeNames) {
        
        final Node foundNode = findFirstNode(nodeList, nodeNames);
        
        return foundNode != null ? foundNode.getTextContent() : null;
    }

    public static Node findFirstNode(final Node node, final String nodeName) {

        if( node == null || nodeName == null) {
            return null;
        }
        
        return findFirstNode(node.getChildNodes(), nodeName);
        
    }
    
    
    public static Node findFirstNode(final Node node, final String ... nodeNames) {

        if( node == null || nodeNames == null || nodeNames.length == 0 ) {
            return null;
        }
        
        final Node foundNode = findFirstNode(node.getChildNodes(), nodeNames[0]);
        
        if( foundNode == null || nodeNames.length == 1 ) {
            return foundNode;
        }
        
        return findFirstNode( foundNode, Arrays.copyOfRange(nodeNames, 1, nodeNames.length));
        
    }
    
    
    public static Node findFirstNode(final NodeList nodeList, final String nodeName) {

        if( nodeList == null || nodeName == null ) {
            return null;
        }
        
        final int len = nodeList.getLength();
        
        for(int i=0; i<len; i++) {
            
            final Node node = nodeList.item(i);
            
            if( Objects.equals(node.getNodeName(), nodeName) ) {
                return node;
            }
            
        }
        
        return null;
    }

    public static Node findFirstNode(final NodeList nodeList, final String ... nodeNames) {
        
        if( nodeList == null || nodeNames == null || nodeNames.length == 0 ) {
            return null;
        }

        final Node foundNode = findFirstNode(nodeList, nodeNames[0]);
        
        if( foundNode == null || nodeNames.length == 1) {
            return foundNode;
        }

        return findFirstNode( foundNode.getChildNodes(), Arrays.copyOfRange(nodeNames, 1, nodeNames.length));
    }

}
