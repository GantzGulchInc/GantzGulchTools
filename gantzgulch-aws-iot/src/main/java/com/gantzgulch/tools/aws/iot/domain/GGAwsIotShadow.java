package com.gantzgulch.tools.aws.iot.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class GGAwsIotShadow {

    @JsonProperty("reported")
    private JsonNode reported;
    
    public GGAwsIotShadow() {
    }
    
    public GGAwsIotShadow(final JsonNode reported) {
        this.setReported(reported);
    }

    public JsonNode getReported() {
        return reported;
    }

    public void setReported(JsonNode reported) {
        this.reported = reported;
    }
}
