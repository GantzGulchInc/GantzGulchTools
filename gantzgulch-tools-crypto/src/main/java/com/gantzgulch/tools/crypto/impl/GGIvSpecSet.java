package com.gantzgulch.tools.crypto.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gantzgulch.tools.common.lang.GGArgs;
import com.gantzgulch.tools.common.lang.GGArrays;
import com.gantzgulch.tools.common.lang.GGLists;
import com.gantzgulch.tools.crypto.exception.CryptoException;

public class GGIvSpecSet extends AbstractGGIvSpec {

    private final Set<Integer> ivSizesInBits = new HashSet<>();

    public GGIvSpecSet(final int ... ivSizeInBits) {
        
        super(GGArrays.min(ivSizeInBits), GGArrays.max(ivSizeInBits));
        
        GGArgs.notNull(ivSizeInBits, "ivSizeInBits");
        GGArgs.isGreaterThan(ivSizeInBits.length, 0, "ivSizeInBits.length");
        
        for(int bits : ivSizeInBits){
            this.ivSizesInBits.add( bits );
        }
    }


    @Override
    public void verify(final byte[] iv) {

        if( iv == null ){
            throw new CryptoException( String.format("Expected an IV with size on of %s, but received null.", createIvSizeString()));
        }
        
        final int ivLength = iv.length * 8;
        
        if( ! ivSizesInBits.contains(ivLength) ) {
            throw new CryptoException( String.format("Expected an IV with size one of %s, but received iv with size %d.", createIvSizeString(), ivLength));
        }
    }

    private String createIvSizeString() {
        
        final List<Integer> sizes = new ArrayList<>(ivSizesInBits);
        
        Collections.sort(sizes);
        
        return GGLists.join(sizes, ",");
        
    }
}
