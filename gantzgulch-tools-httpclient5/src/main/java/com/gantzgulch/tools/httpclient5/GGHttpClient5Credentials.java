package com.gantzgulch.tools.httpclient5;

public interface GGHttpClient5Credentials {

    String getScheme();
    String getHost();
    int getPort();
    GGHttpClient5AuthType getAuthType();
    String getUsername();
    String getPassword();

}
