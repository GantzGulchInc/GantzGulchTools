package com.gantzgulch.tools.aws.iot.impl;

import java.io.Closeable;
import java.io.IOException;

import com.amazonaws.services.iot.client.AWSIotMessage;
import com.amazonaws.services.iot.client.AWSIotTopic;
import com.gantzgulch.tools.common.logging.GGLogger;

public class GGAwsIotTopic extends AWSIotTopic implements Closeable {

    private static final GGLogger LOG = GGLogger.getLogger(GGAwsIotTopic.class);
    
    private final GGAwsIotConnectorImpl iotConnector;

    public GGAwsIotTopic(final GGAwsIotConnectorImpl iotConnector, final String topic) {
        super(topic);
        this.iotConnector = iotConnector;
    }

    @Override
    public void onFailure() {
        LOG.debug("onFailure: Called.");
    }

    @Override
    public void onSuccess() {
        LOG.debug("onSuccess: Called.");
        
    }
    
    @Override
    public void onTimeout() {
        LOG.debug("onTimeout: Called.");
    }
    
    @Override
    public void onMessage(final AWSIotMessage message) {
        
        LOG.trace("onMessage: topic: %s", message.getTopic());
        LOG.trace("onMessage: payload: %s", message.getStringPayload());
        
    }
    
    @Override
    public void close() throws IOException {
        
    }

}
