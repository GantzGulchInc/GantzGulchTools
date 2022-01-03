package com.gantzgulch.tools.common.lang;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public final class GGMaps {

    private GGMaps() {
        throw new UnsupportedOperationException();
    }

    public static <K, E> Map<K, E> shallowCopy(final Map<K, E> src) {

        return src != null ? new HashMap<>(src) : new HashMap<>();

    }

    public static <K extends Comparable<K>, V> Comparator<Map.Entry<K, V>> mapEntryKeyComparator() {
        return (e1, e2) -> {
            return GGComparables.compare(e1.getKey(), e2.getKey());
        };
    }
    
    public static <K, V extends Comparable<V>> Comparator<Map.Entry<K, V>> mapEntryValueComparator() {
        return (e1, e2) -> {
            return GGComparables.compare(e1.getValue(), e2.getValue());
        };
    }
}
