package com.gantzgulch.tools.common.guice;

import java.util.Set;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.google.inject.util.Types;

public final class GuiceUtil {

    private GuiceUtil() {
        throw new UnsupportedOperationException();
    }

    public static <T> Set<T> getSetOf(final Injector injector,  final Class<T> type) {

        final TypeLiteral<Set<T>> lit = setOf(type);
        final Key<Set<T>> key = Key.get(lit);
        final Set<T> bindings = injector.getInstance(key);

        return bindings;
    }

    @SuppressWarnings("unchecked")
    public static <T> TypeLiteral<Set<T>> setOf(Class<T> type) {
        return (TypeLiteral<Set<T>>) TypeLiteral.get(Types.setOf(type));
    }

}
