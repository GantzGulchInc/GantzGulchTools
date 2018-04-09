package com.gantzgulch.tools.crypto.impl;

import com.gantzgulch.tools.common.lang.GGArgs;
import com.gantzgulch.tools.crypto.GGIvNonceSpec;

public abstract class AbstractGGIvNonceSpec implements GGIvNonceSpec {

    private final int minSizeBits;
    private final int maxSizeBits;

    public AbstractGGIvNonceSpec(final int minSizeBits, final int maxSizeBits) {
        
        GGArgs.isGreaterThanOrEqual(minSizeBits, 0, "minSizeBits");
        GGArgs.isGreaterThanOrEqual(maxSizeBits, minSizeBits, "maxSizeBits");
        
        this.minSizeBits = minSizeBits;
        this.maxSizeBits = maxSizeBits;
    }

    @Override
    public int getMinSizeInBits() {
        return minSizeBits;
    }
    
    @Override
    public int getMaxSizeInBits() {
        return maxSizeBits;
    }
    
}
