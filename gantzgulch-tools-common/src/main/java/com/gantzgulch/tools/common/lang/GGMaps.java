package com.gantzgulch.tools.common.lang;

import java.util.HashMap;
import java.util.Map;

public final class GGMaps {

    private GGMaps() {
        throw new UnsupportedOperationException();
    }

    public static <K,E> Map<K, E> shallowCopy(final Map<K, E> src) {

        return new HashMap<>(src);
        
    }
}
