package com.gantzgulch.tools.common.lang;

import com.gantzgulch.tools.common.GGSupplier;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.function.Supplier;

public class GGEnumerationIterable<T> implements Iterable<T> {

    private final Supplier<Enumeration<T>> enumerationSupplier;

    public GGEnumerationIterable(final Supplier<Enumeration<T>> enumerationSupplier) {
        this.enumerationSupplier = enumerationSupplier;
    }

    @Override
    public Iterator<T> iterator() {
        return new GGEnumerationIterator<>(enumerationSupplier.get());
    }

    public static <T> Iterable<T> of(final GGSupplierWithException<Enumeration<T>> enumerationSupplier) {
        return new GGEnumerationIterable<>(new GGSupplier<>(enumerationSupplier));
    }
}
