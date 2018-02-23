package com.gantzgulch.tools.common.lang;

import java.util.List;

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

}
