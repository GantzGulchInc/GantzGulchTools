package com.gantzgulch.tools.common.lang;

import java.util.Collection;
import java.util.function.Predicate;

public final class GGCollections {

    private GGCollections() {
        throw new UnsupportedOperationException();
    }

    public static boolean isEmpty(final Collection<?> collection) {

        return collection == null || collection.size() == 0;

    }

    public static boolean isNotEmpty(final Collection<?> collection) {

        return collection != null && collection.size() > 0;

    }

    public static int size(final Collection<?> collection) {

        if (collection == null) {
            return 0;
        }

        return collection.size();
    }

    public static boolean hasSize(final Collection<?> collection, final int size) {

        return size(collection) == size;

    }

    public static <T> boolean contains(final Collection<T> collection, final Predicate<? super T> filter) {

        if (collection == null || filter == null) {
            return false;
        }

        return collection //
                .stream()//
                .anyMatch(filter);
    }

    public static <T> T find(final Collection<T> collection, final Predicate<T> pred) {

        if (collection == null || pred == null) {
            return null;
        }

        return collection. //
                stream(). //
                filter(pred). //
                findFirst(). //
                orElse(null);
    }

}
