package com.gantzgulch.tools.common.lang;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;

public final class GGIO {

    private static final int MEGABYTE = 1024 * 1024;
    
    private GGIO() {
        throw new UnsupportedOperationException();
    }

    public static byte[] read(final InputStream is) throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream(MEGABYTE);

        final byte[] buffer = new byte[1024 * 1024];

        int len = 0;

        while ((len = is.read(buffer)) >= 0) {
            baos.write(buffer, 0, len);
        }

        return baos.toByteArray();
    }

    public static String readToString(final InputStream is) throws IOException {


        return GGStrings.fromBytes( read(is) );
    }

    public static String readResourceToString(final Class<?> c, final String resourceName) throws IOException {

        try (final InputStream is = c.getResourceAsStream(resourceName)) {
            return readToString(is);
        }

    }

    public static BufferedReader newBufferedReader(final InputStream inputStream) {

        final InputStreamReader isReader = new InputStreamReader(inputStream, GGUtf8.CHARSET);

        return new BufferedReader(isReader);

    }

    public static BufferedReader newBufferedReader(final InputStream inputStream, final Charset charset) {

        final InputStreamReader isReader = new InputStreamReader(inputStream, charset);

        return new BufferedReader(isReader);

    }

    public static Writer newWriter(final OutputStream outputStream) {
        return new OutputStreamWriter(outputStream, GGUtf8.CHARSET);
    }

    public static Reader newResourceReader(final Class<?> resourceClass, final String resourceName, final Charset encoding) {

        final InputStream inputStream = resourceClass.getResourceAsStream(resourceName);

        try {

            return newBufferedReader(inputStream, encoding);

        } catch (RuntimeException re) {
            GGCloseables.closeQuietly(inputStream);
            throw re;
        }

    }

    public static void write(final String value, final File file) throws IOException {

        try (final OutputStream os = new FileOutputStream(file)) {

            os.write( GGStrings.toBytes(value) );

            os.flush();
        }

    }
    
    public static long copy(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        
        GGArgs.notNull(inputStream, "inputStream");
        GGArgs.notNull(outputStream, "outputStream");
        
        final byte[] buffer = new byte[MEGABYTE];
        
        long total = 0;
        int len = 0;
        
        while( (len = inputStream.read(buffer) ) >= 0 ){
            
            outputStream.write(buffer, 0, len);
            
            total += len;
        }
        
        return total;
        
    }

}
