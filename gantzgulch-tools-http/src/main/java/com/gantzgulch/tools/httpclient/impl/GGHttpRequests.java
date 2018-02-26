package com.gantzgulch.tools.httpclient.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import com.gantzgulch.tools.common.lang.GGStrings;
import com.gantzgulch.tools.common.lang.GGUtf8;

public final class GGHttpRequests {

    private GGHttpRequests() {
        throw new UnsupportedOperationException();
    }

    public static String computeRemoteAddress(final HttpServletRequest httpRequest) {

        String remoteAddr = httpRequest.getHeader("X-Real-IP");

        if (GGStrings.isNotBlank(remoteAddr)) {
            return remoteAddr;
        }

        remoteAddr = httpRequest.getHeader("X-Forwarded-For");

        if (GGStrings.isNotBlank(remoteAddr)) {

            final List<String> ips = GGStrings.splitAndClean(remoteAddr, ',');

            if (ips.size() > 0) {
                return ips.get(0);
            }

        }

        return httpRequest.getRemoteAddr();

    }

    public static String getFirstHeaderValue(final HttpRequest request, final String name, final String defaultValue) {

        final Header header = request.getFirstHeader(name);

        return header != null ? header.getValue() : defaultValue;

    }

    public static String getFirstCookieValue(//
            final HttpServletRequest request, //
            final String cookieName) {

        for (final Cookie cookie : request.getCookies()) {
            if (Objects.equals(cookieName, cookie.getName())) {
                return cookie.getValue();
            }
        }

        return null;
    }

    public static void setHeaders(final HttpRequest request, final Map<String, String> headers) {

        if (request != null && headers != null) {

            headers.forEach((name, value) -> {

                request.setHeader(name, value);

                // testLogger.trace("setHeaders: addingHeader: %s:%s", name,
                // value);

            });
        }

    }

    public static String createQueryString(final Map<String, String> parameters) {

        final List<NameValuePair> parmList = new ArrayList<>();

        if (parameters != null) {
            parameters.forEach((k, v) -> {
                parmList.add(new BasicNameValuePair(k, v));
            });
        }

        return URLEncodedUtils.format(parmList, GGUtf8.NAME);

    }

}
