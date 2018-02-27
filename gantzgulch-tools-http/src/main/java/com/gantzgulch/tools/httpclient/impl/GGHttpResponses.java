package com.gantzgulch.tools.httpclient.impl;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.util.EntityUtils;

import com.gantzgulch.tools.common.lang.GGUtf8;
import com.gantzgulch.tools.httpclient.exception.GGHttpClientException;

public final class GGHttpResponses {

    private GGHttpResponses() {
        throw new UnsupportedOperationException();
    }

    public static int getStatusCode(final HttpResponse httpResponse) {

        int statusCode = 0;

        if (httpResponse != null) {

            final StatusLine statusLine = httpResponse.getStatusLine();

            if (statusLine != null) {

                statusCode = statusLine.getStatusCode();

            }
        }

        return statusCode;

    }

    public static String getStringContent(final HttpResponse response) {

        if (response != null) {

            final HttpEntity httpEntity = response.getEntity();

            if (httpEntity != null) {

                try {
                    return EntityUtils.toString(httpEntity, GGUtf8.CHARSET);
                } catch (final IOException e) {
                    throw new GGHttpClientException(e);
                }

            }

        }

        return null;
    }

    public static void consume(final HttpResponse response) {

        if (response != null) {

            EntityUtils.consumeQuietly(response.getEntity());

        }
    }

    public static String getFirstHeaderValue(final HttpResponse response, final String name) {

        final Header header = response.getFirstHeader(name);

        return header != null ? header.getValue() : null;

    }

}
