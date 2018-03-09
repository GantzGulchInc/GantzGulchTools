package com.gantzgulch.tools.crypto.impl;

import com.gantzgulch.tools.common.lang.GGArgs;
import com.gantzgulch.tools.crypto.GGIvSpec;

public abstract class AbstractGGIvSpec implements GGIvSpec {

    private final int minSizeBits;
    private final int maxSizeBits;

    public AbstractGGIvSpec(final int minSizeBits, final int maxSizeBits) {
        
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
