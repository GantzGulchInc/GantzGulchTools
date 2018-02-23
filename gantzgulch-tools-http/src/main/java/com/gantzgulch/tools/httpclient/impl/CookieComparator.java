package com.gantzgulch.tools.httpclient.impl;

import java.util.Comparator;

import org.apache.http.cookie.Cookie;

import com.gantzgulch.tools.common.lang.GGComparables;

public final class CookieComparator implements Comparator<Cookie> {

    @Override
    public int compare(final Cookie c1, final Cookie c2) {

        int result = GGComparables.compare(c1.getDomain(), c2.getDomain());

        result = result == 0 ? GGComparables.compare(c1.getPath(), c2.getPath()) : result;
        result = result == 0 ? GGComparables.compare(c1.getName(), c2.getName()) : result;
        result = result == 0 ? GGComparables.compare(c1.getValue(), c2.getValue()) : result;

        return result;
    }

}