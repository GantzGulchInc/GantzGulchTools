package com.gantzgulch.tools.common.codec;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import com.gantzgulch.tools.common.lang.GGStrings;

public final class GGBase64 {

    private GGBase64() {
        throw new UnsupportedOperationException();
    }

    /**
     * Convert an array of bytes to Base 64, return null if bytes is null.
     * 
     * @param bytes to be converted to Base 64.
     * @return Base 64 encoding of bytes
     */
    public static String toBase64String(final byte[] bytes) {

        return toBase64String(bytes, false);

    }

    /**
     * Convert a string to Base 64 by first converting it to UTF-8 bytes.
     * 
     * @param string the value to be converted.
     * @return Base 64 encoding of bytes
     */
    public static String toBase64String(final String string) {

        return toBase64String(GGStrings.toBytes(string), false);

    }

    public static String toBase64String(final byte[] bytes, boolean urlFriendly) {

        if (bytes == null) {
            return null;
        }

        return getBase64Encoder(urlFriendly).encodeToString(bytes);

    }

    public static String toBase64String(final String string, boolean urlFriendly) {

        return toBase64String(GGStrings.toBytes(string));
    }

    public static byte[] fromBase64String(final String base64, boolean urlFriendly) {

        if (base64 == null) {
            return null;
        }

        return getBase64Decoder(urlFriendly).decode(base64);
    }

    private static Encoder getBase64Encoder(final boolean urlFriendly) {

        return urlFriendly ? Base64.getUrlEncoder() : Base64.getEncoder();
    }

    private static Decoder getBase64Decoder(final boolean urlFriendly) {

        return urlFriendly ? Base64.getUrlDecoder() : Base64.getDecoder();
    }
}
