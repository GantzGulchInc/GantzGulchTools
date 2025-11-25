package com.gantzgulch.tools.common;

import com.gantzgulch.tools.common.lang.GGSupplierWithException;

import java.util.function.Supplier;

public class GGSupplier<T> implements Supplier<T> {

    private final GGSupplierWithException<T> supplier;

    public GGSupplier(final GGSupplierWithException<T> supplier){
        this.supplier = supplier;
    }

    @Override
    public T get() {
        try {
            return this.supplier.get();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}
