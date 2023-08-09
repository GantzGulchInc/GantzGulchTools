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

            if (ips.size() > 0) {
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

//    public static void setHeaders(final HttpServletRequest request, final Map<String, String> headers) {
//
//        if (request != null && headers != null) {
//
//            headers.forEach((name, value) -> {
//
//                request.setHeader(name, value);
//
//            });
//        }
//
//    }

//    public static String createQueryString(final Map<String, String> parameters) {
//
//        final List<NameValuePair> parmList = new ArrayList<>();
//
//        if (parameters != null) {
//            parameters.forEach((k, v) -> {
//                parmList.add(new BasicNameValuePair(k, v));
//            });
//        }
//
//        return URLEncodedUtils.format(parmList, GGUtf8.NAME);
//
//    }

}
