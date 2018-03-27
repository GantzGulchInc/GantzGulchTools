package com.gantzgulch.tools.httpclient.impl;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.message.BasicNameValuePair;

import com.gantzgulch.tools.common.lang.GGLists;
import com.gantzgulch.tools.common.lang.GGUtf8;

public final class GGHttpUtils {

    private GGHttpUtils() {
        throw new UnsupportedOperationException();
    }

    public static URI getLastRedirect(final HttpClientContext context) {

        return GGLists.last(context.getRedirectLocations());
    }

    public static List<NameValuePair> toNameValuePairList(final Map<String, String> parameters) {

        final List<NameValuePair> params = new ArrayList<>();

        for (final Map.Entry<String, String> e : parameters.entrySet()) {
            params.add(new BasicNameValuePair(e.getKey(), e.getValue()));
        }

        return params;
    }

    public static String urlEncode(final String value) {
        
        try {
            return URLEncoder.encode(value, GGUtf8.NAME);
        } catch (final UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
