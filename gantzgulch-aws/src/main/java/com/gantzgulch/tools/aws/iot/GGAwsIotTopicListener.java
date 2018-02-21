package com.gantzgulch.tools.aws.iot;

import com.fasterxml.jackson.databind.JsonNode;

public interface GGAwsIotTopicListener {


    void messageReceived(String listenTopic, String topic, final JsonNode message);


}
