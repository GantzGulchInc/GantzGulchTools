package com.gantzgulch.tools.http.jakarta;

import java.util.List;
import java.util.Objects;

import com.gantzgulch.tools.common.lang.GGStrings;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public final class GGHttpServletRequests {

    private GGHttpServletRequests() {
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

            if (! ips.isEmpty()) {
                return ips.get(0);
            }

        }

        return httpRequest.getRemoteAddr();

    }

    public static String getFirstHeaderValue(final HttpServletRequest request, final String name, final String defaultValue) {

        final String headerValue = request.getHeader(name);
        
        return headerValue != null ? headerValue : defaultValue;
        
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

}
