package com.gantzgulch.tools.common.lang;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public final class GGBiConsumer {

    public static <K, V> Consumer<Map.Entry<K, V>> mapConsumer(final BiConsumer<K, V> consumer) {
        return entry -> consumer.accept(entry.getKey(), entry.getValue());
    }

}
