package com.gantzgulch.tools.reqres.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gantzgulch.tools.domain.AbstractJsonObject;

public class DataResponse<T> extends AbstractJsonObject {

    @JsonProperty("data")
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
}
