package com.gantzgulch.tools.httpclient5.impl;

import com.gantzgulch.tools.httpclient5.GGURIBuilder;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GGURIBuilderImpl implements GGURIBuilder {

    private String scheme = null;
    private String host = null;
    private int port = -1;
    private String path = null;
    private Map<String, String> queryParameters = new HashMap<>();

    @Override
    public GGURIBuilder withScheme(final String scheme) {
        this.scheme = scheme;
        return this;
    }

    @Override
    public GGURIBuilder withHost(final String host) {
        this.host = host;
        return this;
    }

    @Override
    public GGURIBuilder withPort(final int port) {
        this.port = port;
        return this;
    }

    @Override
    public GGURIBuilder withPath(final String path) {
        this.path = path;
        return this;
    }

    @Override
    public GGURIBuilder withPath(final String path, final Object... arguments) {
        this.path = String.format(path, arguments);
        return this;
    }

    @Override
    public GGURIBuilder withQueryParameter(final String name, final String value) {
        this.queryParameters.put(name, value);
        return this;
    }

    @Override
    public GGURIBuilder withQueryParameters(final Map<String,String> values) {

        if( values != null){
            this.queryParameters.putAll(values);
        }

        return this;
    }

    @Override
    public URI build() {

        final URIBuilder uriBuilder = new URIBuilder();

        uriBuilder.setScheme(scheme);
        uriBuilder.setHost(host);
        uriBuilder.setPort(port);
        uriBuilder.setPath(path);

        uriBuilder.setParameters(
                queryParameters
                        .entrySet()
                        .stream()
                        .map(e -> new BasicNameValuePair(e.getKey(), e.getValue()))
                        .collect(Collectors.toList()));

        try {

            return uriBuilder.build();

        } catch (final URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
