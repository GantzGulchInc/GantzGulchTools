package com.gantzgulch.tools.common.lang;

import java.util.function.Predicate;

public final class GGPredicates {

    public static <T> Predicate<T> alwaysTrue() {
        return new Predicate<T>() {
            @Override
            public boolean test(T t) {
                return true;
            }
        };
    }

    public static <T> Predicate<T> alwaysFalse() {
        return new Predicate<T>() {
            @Override
            public boolean test(T t) {
                return false;
            }
        };
    }

    @SafeVarargs
    public static <T> Predicate<T> firstNonNull(final Predicate<T>... predicates) {

        for (final Predicate<T> p : predicates) {
            if (p != null) {
                return p;
            }
        }

        return null;
    }

}
