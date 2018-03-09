package com.gantzgulch.tools.crypto.impl;

import com.gantzgulch.tools.crypto.GGKeySpec;

public class GGKeySpecImpl implements GGKeySpec {

    private final int[] keySizeBits;

    public GGKeySpecImpl(final int... keySizeBits) {

        this.keySizeBits = new int[keySizeBits.length];

        System.arraycopy(keySizeBits, 0, this.keySizeBits, 0, keySizeBits.length);

    }

    @Override
    public int[] getKeySizesInBits() {

        final int[] result = new int[this.keySizeBits.length];

        System.arraycopy(this.keySizeBits, 0, result, 0, this.keySizeBits.length);

        return result;
    }

    public static final GGKeySpec createRanged(final int min, final int max) {

        final int len = max - min + 1;

        final int[] keySizes = new int[len];

        int size = min;
        for (int i = 0; i < keySizes.length; i++) {

            keySizes[i] = size++;
        }

        return new GGKeySpecImpl(keySizes);

    }
}
