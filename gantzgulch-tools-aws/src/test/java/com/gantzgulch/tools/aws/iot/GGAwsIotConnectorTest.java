package com.gantzgulch.tools.aws.iot;

import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.tools.common.lang.GGSleep;
import com.gantzgulch.tools.crypto.impl.GGKeyStore;

public class GGAwsIotConnectorTest {

    private static final GGLogger LOG = GGLogger.getLogger(GGAwsIotConnectorTest.class);
    
    private final String name = "";
    private final String clientEndpoint = "";
    private final String certificateFilename = "";
    private final String privateKeyFilename = "";

    @Test
    @Ignore("This is an integration test.")
    public void connect() {
        
        final GGKeyStore keyStore = loadKeyStore();
        
        final GGAwsIotConnector iot = GGAwsIotConnector.create(name, clientEndpoint, keyStore.getKeyStore(), keyStore.getPassword());
        
        iot.connect();
        
        
        iot.registerListener("device/+/in", new GGAwsIotTopicListener() {
            
            @Override
            public void messageReceived(String listenTopic, String topic, JsonNode message) {
                LOG.debug("messageReceived: listenTopic: %s, topic: %s, message: %s", listenTopic, topic, message);
            }
        });
        
        GGSleep.sleep(600000);
    }

    private GGKeyStore loadKeyStore() {
        
        final GGKeyStore keyStore = new GGKeyStore();
        
        LOG.debug("certificateFilename: %s", certificateFilename);
        LOG.debug("keyFilename: %s", privateKeyFilename);
        
        keyStore.setCertificateEntryFromFile("alias", certificateFilename);
        keyStore.setKeyEntryFromFile("alias", privateKeyFilename, certificateFilename);
        
        
        return keyStore;
    }
}
