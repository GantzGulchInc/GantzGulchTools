package com.gantzgulch.tools.common.lang;

public final class GGComparables {

    private GGComparables() {
        throw new UnsupportedOperationException();
    }

    public static <T> int compare(final Comparable<T> c1, final T c2) {

        if (c1 == c2) {
            return 0;
        }

        if (c1 == null) {
            return -1;
        }

        if (c2 == null) {
            return 1;
        }

        return c1.compareTo(c2);

    }

}
