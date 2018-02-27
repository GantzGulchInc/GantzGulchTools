package com.gantzgulch.tools.common.compression;

import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public final class GGZlib {

    private GGZlib() {
        throw new UnsupportedOperationException();
    }
    
    public static byte[] deflate(final byte[] input) {

        if (input == null) {
            return null;
        }

        final byte[] output = new byte[input.length * 2 + 30];

        final Deflater deflater = new Deflater();

        deflater.setInput(input);
        deflater.finish();

        final int compressedLength = deflater.deflate(output);

        deflater.end();

        final byte[] exactOutput = new byte[compressedLength];
        
        System.arraycopy(output, 0, exactOutput, 0, compressedLength);

        return exactOutput;
    }

    public static byte[] inflate(final byte[] input, final int originalLength) {

        final Inflater inflater = new Inflater();

        final byte[] output = new byte[originalLength];

        inflater.setInput(input);

        int resultLength = 0;

        try {
            resultLength = inflater.inflate(output);
        } catch (DataFormatException e) {
            throw new RuntimeException(e);
        }

        inflater.end();

        if (resultLength == originalLength) {
            return output;
        }

        throw new IllegalStateException("Inflated length : " + resultLength + " does not equal original : " + originalLength);
    }

}
