package com.gantzgulch.tools.httpclient5.impl;

import com.gantzgulch.tools.httpclient5.GGHttpClient5AuthType;
import com.gantzgulch.tools.httpclient5.GGHttpClient5Credentials;

public class GGHttpClient5CredentialsImpl implements GGHttpClient5Credentials {

    private final String scheme;
    private final String host;
    private final int port;
    private final GGHttpClient5AuthType authType;
    private final String username;
    private final String password;

    public GGHttpClient5CredentialsImpl(
            final String scheme,
            final String host,
            final int port,
            final GGHttpClient5AuthType authType,
            final String username,
            final String password ){
        this.scheme = scheme;

        this.host = host;
        this.port = port;
        this.authType = authType;
        this.username = username;
        this.password = password;
    }

    @Override
    public String getScheme() {
        return scheme;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public int getPort() {
        return port;
    }

    public GGHttpClient5AuthType getAuthType() {
        return authType;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
