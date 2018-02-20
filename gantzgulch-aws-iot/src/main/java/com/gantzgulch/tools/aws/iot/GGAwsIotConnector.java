package com.gantzgulch.tools.aws.iot;

import java.security.KeyStore;

import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.AWSIotTimeoutException;
import com.fasterxml.jackson.databind.JsonNode;
import com.gantzgulch.tools.aws.iot.domain.GGAwsIotShadow;
import com.gantzgulch.tools.aws.iot.impl.GGAwsIotConnectorImpl;

public interface GGAwsIotConnector {

    public static GGAwsIotConnector create(final String name, final String clientEndpoint, final KeyStore keyStore, final String keyStorePassword) {
        
        return new GGAwsIotConnectorImpl(name, clientEndpoint, keyStore, keyStorePassword);
    }

    void connect() throws AWSIotException, AWSIotTimeoutException;

    void updateShadow(GGAwsIotShadow state);

    void send(String topic, JsonNode message);

    void registerListener(String topic, GGAwsTopicListener listener);

}
