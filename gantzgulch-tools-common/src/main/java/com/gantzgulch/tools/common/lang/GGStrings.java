package com.gantzgulch.tools.common.lang;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * This class consists of {@code static} utility methods for operating on
 * Strings.
 * 
 * @author gantzm
 *
 */
public final class GGStrings {

    public static final int HEX_RADIX = 16;

    public static final char[] HEX = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static final String UTF8_CHARSET_NAME = "UTF-8";

    public static final Charset UTF8_CHARSET = Charset.forName(UTF8_CHARSET_NAME);

    private GGStrings() {
        throw new UnsupportedOperationException();
    }

    /**
     * Repeat a String {@code repeat} times to form a new String.
     * 
     * @param string
     *            the String to repeat.
     * @param repeat
     *            the number times to repeat the string.
     * 
     * @return the repeated String.
     */
    public static String repeat(final String string, final int repeat) {

        if (string == null) {
            return null;
        }

        if (repeat < 1) {
            return "";
        }

        if (repeat == 1) {
            return string;
        }

        final StringBuilder sb = new StringBuilder(string.length() * repeat);

        for (int index = 0; index < repeat; index++) {
            sb.append(string);
        }

        return sb.toString();
    }

    /**
     * Left pad a String with spaces to length {@code width}.
     * 
     * @param string
     *            the String to pad.
     * @param width
     *            the total width to pad to.
     * 
     * @return the padded String.
     */
    public static String leftPad(final String string, final int width) {

        if (string == null) {
            return null;
        }

        int toAdd = width - string.length();

        if (toAdd < 1) {
            return string;
        }

        return repeat(" ", toAdd) + string;
    }

    /**
     * Right pad a String with spaces to length {@code width}.
     * 
     * @param string
     *            the String to pad.
     * @param width
     *            the total width to pad to.
     * 
     * @return the padded String.
     */
    public static String rightPad(final String string, final int width) {

        if (string == null) {
            return null;
        }

        int toAdd = width - string.length();

        if (toAdd < 1) {
            return string;
        }

        return string + repeat(" ", toAdd);
    }

    /**
     * Center a String with spaces to {@code width}.
     * 
     * @param string
     *            the String to center.
     * @param width
     *            the total width to center to.
     * 
     * @return the centered String.
     */
    public static String center(final String string, final int width) {

        if (string == null) {
            return null;
        }

        if (width < 1 || width < string.length()) {
            return string;
        }

        final int strLen = string.length();
        final int pads = width - strLen;

        if (pads <= 0) {
            return string;
        }

        return rightPad(leftPad(string, strLen + pads / 2), width);

    }

    public static String fromBytes(final byte[] bytes) {

        return bytes != null ? new String(bytes, UTF8_CHARSET) : null;

    }

    public static byte[] toBytes(final String string) {

        return string != null ? string.getBytes(UTF8_CHARSET) : null;

    }

    public static boolean isNotBlank(final String s) {

        if (s == null) {
            return false;
        }

        return s.chars().anyMatch(i -> {
            return !Character.isWhitespace(i);
        });
    }

    public static boolean isBlank(final String s) {

        if (s == null) {
            return true;
        }

        return s.chars().allMatch(i -> {
            return Character.isWhitespace(i);
        });

    }

    public static List<String> splitAndClean(final String string, final char separator) {

        final List<String> results = new ArrayList<>();

        if (string != null) {

            for (final String s : string.split(Pattern.quote("" + separator))) {

                if (isNotBlank(s)) {
                    results.add(s.trim());
                }
            }
        }

        return results;
    }

}
