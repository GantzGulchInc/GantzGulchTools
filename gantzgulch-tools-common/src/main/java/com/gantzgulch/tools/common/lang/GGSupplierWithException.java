package com.gantzgulch.tools.common.lang;

public interface GGSupplierWithException<T> {

    T get() throws Exception;

}
