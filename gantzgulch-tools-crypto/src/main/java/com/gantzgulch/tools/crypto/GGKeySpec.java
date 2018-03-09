package com.gantzgulch.tools.crypto;

import com.gantzgulch.tools.common.lang.GGArrays;
import com.gantzgulch.tools.crypto.impl.GGKeySpecImpl;

public interface GGKeySpec {

    int[] getKeySizesInBits();

    public static GGKeySpec create(final int... keySizes) {
        return new GGKeySpecImpl(keySizes);
    }

    public static GGKeySpec createRanged(final int min, final int max) {
        return new GGKeySpecImpl(GGArrays.createRange(min, max));
    }
}
