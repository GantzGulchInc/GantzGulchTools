package com.gantzgulch.tools.common.lang;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public final class GGBiFunction {

    public static <K, V, R> Function<Map.Entry<K, V>, R> mapFunction(final BiFunction<K, V, R> function) {
        return entry -> function.apply(entry.getKey(), entry.getValue());
    }

}
