package com.gantzgulch.tools.crypto.impl;

import com.gantzgulch.tools.common.lang.GGArgs;
import com.gantzgulch.tools.crypto.GGNonceSpec;

public abstract class AbstractGGNonceSpec implements GGNonceSpec {

    private final int minSize;
    private final int maxSize;

    public AbstractGGNonceSpec(final int minSize, final int maxSize) {
        
        GGArgs.isGreaterThanOrEqual(minSize, 0, "minSize");
        GGArgs.isGreaterThanOrEqual(maxSize, minSize, "maxSize");
        
        this.minSize = minSize;
        this.maxSize = maxSize;
    }

    @Override
    public int getMinSize() {
        return minSize;
    }
    
    @Override
    public int getMaxSize() {
        return maxSize;
    }
    
}
