package com.gantzgulch.tools.common.lang;

import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public final class GGBiPredicate {

    public static <K, V> Predicate<Map.Entry<K, V>> mapPredicate(final BiPredicate<K, V> predicate) {
        return entry -> predicate.test(entry.getKey(), entry.getValue());
    }

}
