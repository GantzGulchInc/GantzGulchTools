package com.gantzgulch.tools.httpclient5.impl;

import com.gantzgulch.tools.httpclient5.GGHttpClient5Response;

public class GGHttpClient5ResponseImpl<T> implements GGHttpClient5Response<T> {

    private final int code;

    private final T content;

    public GGHttpClient5ResponseImpl(final int code, final T content) {
        this.code = code;
        this.content = content;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public T getContent() {
        return content;
    }

    @Override
    public String toString() {
        return String.format("Response[code=%d, content=%s]", code, content);
    }
}
