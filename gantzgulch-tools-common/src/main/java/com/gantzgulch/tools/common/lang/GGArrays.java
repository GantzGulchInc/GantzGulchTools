package com.gantzgulch.tools.common.lang;

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

        int min = 0;

        if (array != null && array.length > 0) {

            min = array[0];

            for (int i : array) {

                if (i < min) {
                    min = i;
                }
            }

        }

        return min;
    }

    public static int max(final int[] array) {

        int max = 0;

        if (array != null && array.length > 0) {

            max = array[0];

            for (int i : array) {

                if (i > max) {
                    max = i;
                }
            }

        }

        return max;
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
}
