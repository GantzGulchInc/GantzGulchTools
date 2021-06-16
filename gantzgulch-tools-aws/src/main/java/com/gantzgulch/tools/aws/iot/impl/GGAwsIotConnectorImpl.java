package com.gantzgulch.tools.aws.iot.impl;

import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.iot.client.AWSIotDevice;
import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.AWSIotMqttClient;
import com.amazonaws.services.iot.client.AWSIotQos;
import com.amazonaws.services.iot.client.AWSIotTimeoutException;
import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.tools.aws.exception.GGAwsException;
import com.gantzgulch.tools.aws.iot.GGAwsIotConnector;
import com.gantzgulch.tools.aws.iot.GGAwsIotTopicListener;
import com.gantzgulch.tools.aws.iot.domain.GGAwsIotShadow;
import com.gantzgulch.tools.json.GGJsonWriters;

public class GGAwsIotConnectorImpl implements GGAwsIotConnector {

    private static final GGLogger LOG = GGLogger.getLogger(GGAwsIotConnectorImpl.class);

    private final String name;
    private final String clientEndpoint;
    private final KeyStore keyStore;
    private final String keyStorePassword;

    private AWSIotMqttClient client;
    private AWSIotDevice device;

    private Map<String, GGAwsIotTopic> topicMap = new HashMap<>();

    public GGAwsIotConnectorImpl(final String name, final String clientEndpoint, final KeyStore keyStore, final String keyStorePassword) {

        this.name = name;
        this.clientEndpoint = clientEndpoint;
        this.keyStore = keyStore;
        this.keyStorePassword = keyStorePassword;

    }

    @Override
    public void connect() {

        try {
            client = new AWSIotMqttClient(clientEndpoint, name, keyStore, keyStorePassword);

            device = new AWSIotDevice(name);

            client.attach(device);

            LOG.trace("connect: client: connecting....");

            client.connect(30000, true);

            LOG.trace("connect: client.connect: connected.");

        } catch (final AWSIotException | AWSIotTimeoutException e) {
            throw new GGAwsException(e);
        }
    }

    @Override
    public void close() {

        try {
            client.disconnect();
        } catch (final AWSIotException e) {
            throw new GGAwsException(e);
        }
    }
    
    @Override
    public void updateShadow(final GGAwsIotShadow state) {

    }

    @Override
    public void send(final String topic, final Object message) {

        try {

            final String json = GGJsonWriters.STRICT.writeAsString(message);

            LOG.trace("send: sending: message: %s", json);

            client.publish(topic, AWSIotQos.QOS1, json);

        } catch (final AWSIotException e) {
            LOG.warn(e, "send: error.");
            throw new GGAwsException(e);
        }

    }

    @Override
    public void registerListener(final String topic, final GGAwsIotTopicListener listener) {

        try {
            
            final GGAwsIotTopic ggTopic = findOrCreateTopic(topic);

            ggTopic.registerListener(listener);
            
        } catch (final AWSIotException e) {
            throw new GGAwsException(e);
        }

    }

    private GGAwsIotTopic findOrCreateTopic(final String topic) throws AWSIotException {

        GGAwsIotTopic ggTopic = topicMap.get(topic);

        if (ggTopic == null) {

            ggTopic = new GGAwsIotTopic(topic);

            client.subscribe(ggTopic);

            topicMap.put(topic, ggTopic);
        }

        return ggTopic;
    }

}
