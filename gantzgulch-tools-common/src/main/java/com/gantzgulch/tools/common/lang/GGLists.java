package com.gantzgulch.tools.common.lang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;

public final class GGLists {

    private GGLists() {
        throw new UnsupportedOperationException();
    }

    public static <T> T first(final List<T> list) {
        return GGCollections.isNotEmpty(list) ? list.get(0) : null;
    }

    public static <T> T last(final List<T> list) {
        return GGCollections.isNotEmpty(list) ? list.get(list.size() - 1) : null;
    }

    public static <T> boolean contains(List<T> list, Predicate<? super T> filter) {

        return GGStream //
                .of(list) //
                .anyMatch(filter);
    }

    public static <T> T removeFirst(List<T> list, Predicate<? super T> filter) {

        if (GGCollections.isEmpty(list)) {
            return null;
        }

        final ListIterator<T> i = list.listIterator();

        while (i.hasNext()) {

            final T item = i.next();

            if (filter.test(item)) {

                i.remove();

                return item;
            }
        }

        return null;
    }

    public static <T> List<T> removeAll(final List<T> list, final Predicate<? super T> filter) {

        if (GGCollections.isEmpty(list)) {
            return null;
        }

        final List<T> results = new ArrayList<>();

        list.removeIf((i) -> {
            if (filter.test(i)) {
                results.add(i);
                return true;
            }
            return false;
        });

        return results;
    }

    public static <T extends Comparable<T>> List<T> sort(final List<T> list) {

        if (list != null) {
            Collections.sort(list);
        }

        return list;
    }

    public static <T> List<T> sort(final List<T> list, final Comparator<T> comparator) {

        if (list != null && comparator != null) {
            Collections.sort(list, comparator);
        }

        return list;
    }

    public static boolean isEmpty(final List<?> list) {

        return list == null || list.size() == 0;

    }

    public static boolean isNotEmpty(final List<?> list) {

        return !isEmpty(list);

    }

    public static <T> T find(final List<T> list, final Predicate<T> pred) {

        return GGStream //
                .of(list) //
                .filter(pred) //
                .findAny() //
                .orElse(null);
    }

    public static <T> String join(final List<T> list, final String separator) {

        final StringBuilder b = new StringBuilder();

        if (isNotEmpty(list)) {

            for (final T item : list) {

                if (b.length() > 0) {
                    b.append(separator);
                }

                b.append(item);
            }

        }

        return b.toString();

    }
}
