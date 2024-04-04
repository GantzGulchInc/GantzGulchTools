package com.gantzgulch.tools.httpclient5;

import com.gantzgulch.tools.httpclient5.impl.GGURIBuilderImpl;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.protocol.HttpClientContext;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;

import java.io.Closeable;
import java.net.URI;
import java.util.List;
import java.util.Map;

public interface GGURIBuilder {

    GGURIBuilder withScheme(String scheme);
    GGURIBuilder withHost(String host);
    GGURIBuilder withPort(int port);
    GGURIBuilder withPath(String path);
    GGURIBuilder withPath(String path, Object ... arguments);
    GGURIBuilder withQueryParameter(String name, String value);
    GGURIBuilder withQueryParameters(Map<String,String> values);

    URI build();

    static GGURIBuilder create() {
        return new GGURIBuilderImpl();
    }

}
