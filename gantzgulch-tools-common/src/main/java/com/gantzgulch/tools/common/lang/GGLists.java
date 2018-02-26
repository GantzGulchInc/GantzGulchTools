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

        if (list == null) {
            return null;
        }

        if (list.size() == 0) {
            return null;
        }

        return list.get(0);
    }

    public static <T> T last(final List<T> list) {

        if (list == null) {
            return null;
        }

        if (list.size() == 0) {
            return null;
        }

        return list.get(list.size() - 1);
    }

    public static <T> boolean contains(List<T> list, Predicate<? super T> filter) {

        return list != null ? list.stream().anyMatch(filter) : false;
    }

    public static <T> T removeFirst(List<T> list, Predicate<? super T> filter) {

        if (list != null && filter != null) {

            final ListIterator<T> i = list.listIterator();

            while (i.hasNext()) {

                final T item = i.next();

                if (filter.test(item)) {

                    i.remove();

                    return item;
                }
            }
        }

        return null;
    }

    public static <T> List<T> removeAll(List<T> list, Predicate<? super T> filter) {

        final List<T> results = new ArrayList<>();

        if (list != null && filter != null) {

            final ListIterator<T> i = list.listIterator();

            while (i.hasNext()) {

                final T item = i.next();

                if (filter.test(item)) {

                    i.remove();

                    results.add(item);
                }
            }
        }

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

    public static boolean isNotEmpty(final List<?> list) {
        
        return list != null && list.size() > 0 ;
    }
}
