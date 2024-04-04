package com.gantzgulch.tools.common.lang;

import java.util.*;

public class GGMappedCounts<K> {

    private final Map<K,Long> counts = new HashMap<>();

    public GGMappedCounts() {

    }

    public Collection<K> getKeys(){
        return Collections.unmodifiableSet(counts.keySet());
    }

    public Optional<Long> getValue(final K key) {
        return Optional.ofNullable(counts.get(key));
    }

    public long increment(final K key) {

        final long value = counts.computeIfAbsent( key, this::create );

        final long newValue = value + 1;

        counts.put(key, newValue);

        return newValue;

    }

    public long decrement(final K key) {

        final long value = counts.computeIfAbsent(key, this::create);

        final long newValue = value - 1;

        counts.put(key, newValue);

        return newValue;
    }

    private long create(final K key) {
        return 0;
    }
}
