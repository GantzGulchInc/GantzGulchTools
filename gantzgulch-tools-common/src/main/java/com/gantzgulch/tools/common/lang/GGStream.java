package com.gantzgulch.tools.common.lang;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public final class GGStream {

    private GGStream() {
        throw new UnsupportedOperationException();
    }

    public static IntStream stream(final int[] array) {
        return array != null ? Arrays.stream(array) : IntStream.of();
    }
    
    public static LongStream stream(final long[] array) {
        return array != null ? Arrays.stream(array) : LongStream.of();
    }
    
    public static DoubleStream stream(final double[] array) {
        return array != null ? Arrays.stream(array) : DoubleStream.of();
    }
    
    public static <T> Stream<T> stream(final T[] array) {
     
        if( array == null ) {
            return Stream.of();
        }
        
        return Arrays.stream(array);
    }
}
