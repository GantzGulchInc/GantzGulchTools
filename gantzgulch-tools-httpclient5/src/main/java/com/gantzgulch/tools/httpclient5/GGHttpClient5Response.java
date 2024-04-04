package com.gantzgulch.tools.httpclient5;

public interface GGHttpClient5Response<T> {

    int getCode();

    T getContent();

}
