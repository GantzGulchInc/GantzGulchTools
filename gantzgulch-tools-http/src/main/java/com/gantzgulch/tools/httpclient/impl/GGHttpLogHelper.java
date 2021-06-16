package com.gantzgulch.tools.httpclient.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;

import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.tools.common.lang.GGStrings;

public class GGHttpLogHelper {

    private static final GGLogger LOG = GGLogger.getLogger(GGHttpLogHelper.class);
    
    public GGHttpLogHelper() {
    }
    
    public void logResponse(final Object source, final String process, final String content, final HttpResponse response, final HttpClientContext context) {

        final String nonNullProcess = GGStrings.defaultIfBlank(process, "<unknown>");
        
        final String shortenedProcess = GGStrings.left(nonNullProcess, 128);

        final String abbreviateProcess = shortenedProcess.length() == nonNullProcess.length() ? shortenedProcess : shortenedProcess + "... ";

        if (response != null) {

            LOG.trace(abbreviateProcess + ": response:");
            LOG.trace(abbreviateProcess + ":     statusLine: " + response.getStatusLine());
            LOG.trace(abbreviateProcess + ":     statusLine.statusCode: " + response.getStatusLine().getStatusCode());

            for (final Header header : response.getAllHeaders()) {
                LOG.trace(abbreviateProcess + ":     header: " + header);
            }

        }

        if (context != null) {

            LOG.trace(abbreviateProcess + ": context: " + context);

            if (context.getRedirectLocations() != null) {
                for (final URI uri : context.getRedirectLocations()) {
                    LOG.trace("%s:     redirect: %s", abbreviateProcess, uri);
                }
            }

            final CookieStore cookieStore = context.getCookieStore();
            if (cookieStore != null) {

                final List<Cookie> cookies = new ArrayList<>(cookieStore.getCookies());
                Collections.sort(cookies, new CookieComparator());

                for (final Cookie cookie : cookies) {
                    LOG.trace("%s:     cookie: domain: %s, path: %s, name: %s, value: %s, expire: %tc", abbreviateProcess, cookie.getDomain(), cookie.getPath(),
                            cookie.getName(), cookie.getValue(), cookie.getExpiryDate());
                }
            }

        }
        
        if( content != null ){
            LOG.trace(abbreviateProcess + ": content: \n %s", content);
        }

    }

}
