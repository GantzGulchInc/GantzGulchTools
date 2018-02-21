package com.gantzgulch.tools.aws.iot;

import java.security.KeyStore;

import com.gantzgulch.tools.aws.iot.domain.GGAwsIotShadow;
import com.gantzgulch.tools.aws.iot.impl.GGAwsIotConnectorImpl;

public interface GGAwsIotConnector {

    public static GGAwsIotConnector create(final String name, final String clientEndpoint, final KeyStore keyStore, final String keyStorePassword) {
        
        return new GGAwsIotConnectorImpl(name, clientEndpoint, keyStore, keyStorePassword);
    }

    void connect();

    void updateShadow(GGAwsIotShadow state);

    void send(String topic, Object message);

    void registerListener(String topic, GGAwsIotTopicListener listener);

}
