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

public class GGNonceSpecSet extends AbstractGGNonceSpec {

    private final Set<Integer> nonceSizes = new HashSet<>();

    public GGNonceSpecSet(final int ... nonceSizes) {
        
        super(GGArrays.min(nonceSizes), GGArrays.max(nonceSizes));
        
        GGArgs.notNull(nonceSizes, "nonceSizes");
        GGArgs.isGreaterThan(nonceSizes.length, 0, "nonceSizes.length");
        
        for(int ivSize : nonceSizes){
            this.nonceSizes.add( ivSize );
        }
    }


    @Override
    public void verify(final byte[] nonce) {

        if( nonce == null ){
            throw new CryptoException( String.format("Expected a nonce with size on of %s, but received null.", createNonceSizeString()));
        }
        
        if( ! nonceSizes.contains(nonce.length) ) {
            throw new CryptoException( String.format("Expected a nonce with size one of %s, but received nonce with size %d.", createNonceSizeString(), nonce.length));
        }
    }

    private String createNonceSizeString() {
        
        final List<Integer> sizes = new ArrayList<>(nonceSizes);
        
        Collections.sort(sizes);
        
        return GGLists.join(sizes, ",");
        
    }
}
