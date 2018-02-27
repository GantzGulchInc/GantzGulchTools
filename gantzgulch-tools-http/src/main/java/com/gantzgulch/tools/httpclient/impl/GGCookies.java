package com.gantzgulch.tools.httpclient.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;

public final class GGCookies {

    private GGCookies() {
        throw new UnsupportedOperationException();
    }

    public static Cookie findCookie(final CookieStore cookieStore, final String cookieName) {

        if (cookieStore != null) {

            for (final Cookie cookie : cookieStore.getCookies()) {
                if (Objects.equals(cookieName, cookie.getName())) {
                    return cookie;
                }
            }

        }

        return null;
    }

    public static Cookie findCookie(final HttpClientContext localContext, final String cookieName) {

        if (localContext != null) {
            return findCookie(localContext.getCookieStore(), cookieName);
        }

        return null;
    }

    public static String getCookieValue(final CookieStore cookieStore, final String cookieName) {

        final Cookie cookie = findCookie(cookieStore, cookieName);
        return cookie != null ? cookie.getValue() : null;
    }

    public static String getCookieValue(final HttpClientContext localContext, final String cookieName) {

        if (localContext != null) {
            return getCookieValue(localContext.getCookieStore(), cookieName);
        }

        return null;
    }

    public static Cookie createCookie(final String cookieName, final String value, final String domain, final Date expiryDate) {

        final BasicClientCookie cookie = new BasicClientCookie(cookieName, value);

        cookie.setDomain(domain);
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setExpiryDate(expiryDate);

        cookie.setAttribute(ClientCookie.PATH_ATTR, "/");
        cookie.setAttribute(ClientCookie.DOMAIN_ATTR, domain);

        return cookie;

    }

    public static void setCookie(final CookieStore cookieStore, final String cookieName, String value, String domain, Date expiryDate) {

        final Cookie cookie = createCookie(cookieName, value, domain, expiryDate);

        if (cookieStore != null) {
            cookieStore.addCookie(cookie);
        }
    }

    public static void setCookie(final HttpClientContext localContext, final String cookieName, String value, String domain, Date expiryDate) {

        if (localContext != null) {

            setCookie(localContext.getCookieStore(), cookieName, value, domain, expiryDate);
        }
    }

    public static void updateCookie(final HttpClientContext localContext, final String cookieName, final String newValue) {

        final Cookie cookie = findCookie(localContext, cookieName);

        if (cookie != null) {
            setCookie(localContext, cookieName, newValue, cookie.getDomain(), cookie.getExpiryDate());
        }

    }

    public static Map<String, String> getCookies(final CookieStore cookieStore) {

        final Map<String, String> cookieMap = new HashMap<>();

        if (cookieStore != null) {
            for (final Cookie cookie : cookieStore.getCookies()) {
                cookieMap.put(cookie.getName(), cookie.getValue());
            }
        }

        return cookieMap;
    }
}
