package com.gantzgulch.tools.common.lang;

import java.util.Arrays;
import java.util.function.Predicate;

public final class GGArrays {

    private GGArrays() {
        throw new UnsupportedOperationException();
    }

    public static <T> int size(final T[] array) {

        return array == null ? 0 : array.length;

    }

    public static int size(final byte[] array) {

        return array == null ? 0 : array.length;

    }

    public static int size(final int[] array) {

        return array == null ? 0 : array.length;

    }

    public static int size(final char[] array) {

        return array == null ? 0 : array.length;

    }

    public static int min(final int[] array) {

        if (array == null) {
            return 0;
        }

        return Arrays//
                .stream(array)//
                .min()//
                .orElse(0);

    }

    public static int max(final int[] array) {

        if (array == null) {
            return 0;
        }

        return Arrays//
                .stream(array)//
                .max()//
                .orElse(0);
    }

    public static int[] createRange(final int min, final int max) {

        final int len = max - min + 1;

        final int[] range = new int[len];

        int size = min;

        for (int i = 0; i < range.length; i++) {
            range[i] = size++;
        }

        return range;

    }

    public static <T> boolean contains(final T[] array, final Predicate<? super T> filter) {

        if (array == null || filter == null) {
            return false;
        }

        return Arrays //
                .stream(array)//
                .anyMatch(filter);
    }

    public static <T> T find(final T[] array, final Predicate<T> pred) {

        if (array == null || pred == null) {
            return null;
        }

        return Arrays//
                .stream(array) //
                .filter(pred) //
                .findAny() //
                .orElse(null);
    }
}
