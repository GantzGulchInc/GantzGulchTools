package com.gantzgulch.tools.common.lang;

import java.util.List;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    public static int size(final long[] array) {

        return array == null ? 0 : array.length;

    }

    public static int size(final double[] array) {

        return array == null ? 0 : array.length;

    }

    public static int size(final char[] array) {

        return array == null ? 0 : array.length;

    }

    public static int min(final int[] array) {

        return GGStream //
                .stream(array)//
                .min()//
                .orElse(0);

    }

    public static long min(final long[] array) {

        return GGStream //
                .stream(array)//
                .min()//
                .orElse(0);

    }

    public static double min(final double[] array) {

        return GGStream //
                .stream(array)//
                .min()//
                .orElse(0);

    }

    public static int max(final int[] array) {

        return GGStream//
                .stream(array)//
                .max()//
                .orElse(0);
    }

    public static long max(final long[] array) {

        return GGStream//
                .stream(array)//
                .max()//
                .orElse(0);
    }

    public static double max(final double[] array) {

        return GGStream//
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

        return GGStream //
                .stream(array)//
                .anyMatch(filter);
    }

    public static boolean contains(final int[] array, final IntPredicate filter) {

        return GGStream //
                .stream(array)//
                .anyMatch(filter);
    }

    public static boolean contains(final long[] array, final LongPredicate filter) {

        return GGStream //
                .stream(array)//
                .anyMatch(filter);
    }

    public static boolean contains(final double[] array, final DoublePredicate filter) {

        return GGStream //
                .stream(array)//
                .anyMatch(filter);
    }

    public static <T> T find(final T[] array, final Predicate<T> pred) {

        return GGStream //
                .stream(array) //
                .filter(pred) //
                .findAny() //
                .orElse(null);
    }

    public static <T> List<T> findAll(final T[] array, final Predicate<T> pred) {

        return GGStream //
                .stream(array) //
                .filter(pred) //
                .collect(Collectors.toList());
    }

    
}
