package com.gantzgulch.tools.aws.iot.impl;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.iot.client.AWSIotMessage;
import com.amazonaws.services.iot.client.AWSIotTopic;
import com.fasterxml.jackson.databind.JsonNode;
import com.gantzgulch.tools.aws.iot.GGAwsIotTopicListener;
import com.gantzgulch.tools.common.json.GGJsonReader;
import com.gantzgulch.tools.common.logging.GGLogger;

public class GGAwsIotTopic extends AWSIotTopic implements Closeable {

    private static final GGLogger LOG = GGLogger.getLogger(GGAwsIotTopic.class);

    private final List<GGAwsIotTopicListener> listeners = new ArrayList<>();

    public GGAwsIotTopic(final String topic) {
        super(topic);
    }

    public void registerListener(final GGAwsIotTopicListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
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

        final String messageTopic = message.getTopic();
        final String messagePayload = message.getStringPayload();

        LOG.trace("onMessage: topic: %s", messageTopic);
        LOG.trace("onMessage: payload: %s", messagePayload);

        try {

            final JsonNode node = GGJsonReader.STRICT.read(message.getStringPayload());

            dispatch(topic, messageTopic, node);

        } catch (final RuntimeException e) {
            LOG.warn(e, "Error parsing json: %s", messagePayload);
        }
    }

    @Override
    public void close() throws IOException {

    }

    private void dispatch(final String topic, final String messageTopic, final JsonNode node) {

        for (final GGAwsIotTopicListener l : listeners) {
            try {
                l.messageReceived(topic, messageTopic, node);
            } catch (final RuntimeException e) {
                LOG.warn(e, "Error dispatching message.");
            }
        }
    }

}
