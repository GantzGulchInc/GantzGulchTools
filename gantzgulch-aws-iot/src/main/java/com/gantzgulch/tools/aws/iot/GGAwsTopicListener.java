package com.gantzgulch.tools.aws.iot;

import com.fasterxml.jackson.databind.JsonNode;

public interface GGAwsTopicListener {


    void topicReceived(String topic, final JsonNode message);


}
