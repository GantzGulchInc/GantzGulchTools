package com.gantzgulch.tools.aws.iot.impl;

import java.security.KeyStore;

import com.amazonaws.services.iot.client.AWSIotDevice;
import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.AWSIotMqttClient;
import com.amazonaws.services.iot.client.AWSIotTimeoutException;
import com.fasterxml.jackson.databind.JsonNode;
import com.gantzgulch.tools.aws.iot.GGAwsIotConnector;
import com.gantzgulch.tools.aws.iot.GGAwsTopicListener;
import com.gantzgulch.tools.aws.iot.domain.GGAwsIotShadow;
import com.gantzgulch.tools.common.logging.GGLogger;

public class GGAwsIotConnectorImpl implements GGAwsIotConnector {

    private static final GGLogger LOG = GGLogger.getLogger(GGAwsIotConnectorImpl.class);
    
    private static final String DEFAULT_OUT_TOPIC_TEMPLATE = "device/%s/out";
    private static final String DEFAULT_IN_TOPIC_TEMPLATE = "device/%s/in";

    private final String name;
    private final String clientEndpoint;
    private final KeyStore keyStore;
    private final String keyStorePassword;
    
    private String inTopic;
    private String outTopic;
    
    private AWSIotMqttClient client;
    private AWSIotDevice device;
    private GGAwsIotTopic ggInTopic;

    public GGAwsIotConnectorImpl(final String name, final String clientEndpoint, final KeyStore keyStore, final String keyStorePassword) {
        
        this.name = name;
        this.clientEndpoint = clientEndpoint;
        this.keyStore = keyStore;
        this.keyStorePassword = keyStorePassword;
        
    }

    protected String computeOutTopicName() {
        return String.format(DEFAULT_OUT_TOPIC_TEMPLATE, name);
    }
    
    protected String computeInTopicName() {
        return String.format(DEFAULT_IN_TOPIC_TEMPLATE, name);
    }
    
    @Override
    public void connect() throws AWSIotException, AWSIotTimeoutException {
    
        this.inTopic = computeInTopicName();
        this.outTopic = computeOutTopicName();
        
        client = new AWSIotMqttClient(clientEndpoint, name, keyStore, keyStorePassword);

        device = new AWSIotDevice(name);
        
        client.attach(device);

        LOG.trace("connect: client: connecting....");

        client.connect(30000, true);

        LOG.trace("connect: client.connect: called");

        ggInTopic = new GGAwsIotTopic(this, inTopic);

        LOG.trace("connect: client.subscribing...");

        client.subscribe(ggInTopic);

        LOG.trace("connect: client.subscribed.");
        
    }

    @Override
    public void updateShadow(final GGAwsIotShadow state) {
        
    }

    @Override
    public void send(final String topic, final JsonNode message) {
        
    }

    @Override
    public void registerListener(final String topic, final GGAwsTopicListener listener) {
        
    }
    
    protected void dispatch(final String topic, final JsonNode message) {
        
        
    }

}
