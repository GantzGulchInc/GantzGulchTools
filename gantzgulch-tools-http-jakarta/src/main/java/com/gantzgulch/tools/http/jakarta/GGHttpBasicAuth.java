package com.gantzgulch.tools.http.jakarta;

import com.gantzgulch.tools.common.codec.GGBase64;

import java.nio.charset.StandardCharsets;

public final class GGHttpBasicAuth {

    private GGHttpBasicAuth() {
        throw new UnsupportedOperationException();
    }

    public static String computeBasicAuth(final String username, final String password) {

        final String tag = username + ":" + password;

        final byte[] tagBytes = tag.getBytes(StandardCharsets.UTF_8);

        final String encodedTag = GGBase64.toBase64String(tagBytes, false);

        return "Basic " + encodedTag;
    }


}
