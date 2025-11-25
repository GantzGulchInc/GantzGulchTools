package com.gantzgulch.tools.common.lang;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.function.Supplier;

public class GGEnumerationIterator<T> implements Iterator<T> {

    private final Enumeration<T> enumeration;

    public GGEnumerationIterator(final Enumeration<T> enumeration) {
        this.enumeration = enumeration;
    }

    @Override
    public boolean hasNext() {
        return enumeration.hasMoreElements();
    }

    @Override
    public T next() {
        return enumeration.nextElement();
    }
}
