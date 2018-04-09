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

public class GGIvNonceSpecSet extends AbstractGGIvNonceSpec {

    private final Set<Integer> ivNonceSizesInBits = new HashSet<>();

    public GGIvNonceSpecSet(final int ... ivNonceSizeInBits) {
        
        super(GGArrays.min(ivNonceSizeInBits), GGArrays.max(ivNonceSizeInBits));
        
        GGArgs.notNull(ivNonceSizeInBits, "ivNonceSizeInBits");
        GGArgs.isGreaterThan(ivNonceSizeInBits.length, 0, "ivNonceSizeInBits.length");
        
        for(int bits : ivNonceSizeInBits){
            this.ivNonceSizesInBits.add( bits );
        }
    }


    @Override
    public void verify(final byte[] ivNonce) {

        if( ivNonce == null ){
            throw new CryptoException( String.format("Expected an IV/Nonce with size on of %s, but received null.", createIvNonceSizeString()));
        }
        
        final int ivNonceLength = ivNonce.length * 8;
        
        if( ! ivNonceSizesInBits.contains(ivNonceLength) ) {
            throw new CryptoException( String.format("Expected an IV/Nonce with size one of %s, but received iv/nonce with size %d.", createIvNonceSizeString(), ivNonceLength));
        }
    }

    private String createIvNonceSizeString() {
        
        final List<Integer> sizes = new ArrayList<>(ivNonceSizesInBits);
        
        Collections.sort(sizes);
        
        return GGLists.join(sizes, ",");
        
    }
}
