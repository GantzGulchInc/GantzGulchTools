package com.gantzgulch.tools.common.lang;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public final class GGStream {

    private GGStream() {
        throw new UnsupportedOperationException();
    }

    public static <T> Stream<T> of(final Collection<T> collection) {
        return collection != null ? collection.stream() : Stream.of();
    }
    
    public static IntStream of(final int[] array) {
        return array != null ? Arrays.stream(array) : IntStream.of();
    }
    
    public static LongStream of(final long[] array) {
        return array != null ? Arrays.stream(array) : LongStream.of();
    }
    
    public static DoubleStream of(final double[] array) {
        return array != null ? Arrays.stream(array) : DoubleStream.of();
    }
    
    public static <T> Stream<T> of(final T[] array) {
     
        if( array == null ) {
            return Stream.of();
        }
        
        return Arrays.stream(array);
    }
    
    public static <T> void forWhile(final Collection<T> collection, final Function<T,Boolean> op) {
        
        for(final T value : collection) {
            if( op.apply(value) ) {
                break;
            }
        }
        
    }
}
