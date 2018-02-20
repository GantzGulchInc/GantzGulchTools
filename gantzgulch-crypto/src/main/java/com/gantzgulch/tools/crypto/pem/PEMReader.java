package com.gantzgulch.tools.crypto.pem;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;

import org.bouncycastle.openssl.PEMParser;

import com.gantzgulch.tools.common.lang.Arguments;
import com.gantzgulch.tools.common.lang.Cast;
import com.gantzgulch.tools.crypto.exception.CryptoException;

public final class PEMReader {

    private PEMReader() {
        throw new UnsupportedOperationException();
    }

    public static <T> T read(final String pem, final Class<T> objectClass) throws IOException {

        Arguments.isNotNull(pem, "pem is required to be non null.");

        final StringReader stringReader = new StringReader(pem);

        return read(stringReader, objectClass);
    }

    public static <T> T read(final InputStream is, final Class<T> objectClass) throws IOException {

        Arguments.isNotNull(is, "is is required to be non null.");

        final Reader reader = new InputStreamReader(is, Charset.forName("UTF-8"));

        return read(reader, objectClass);

    }

    public static <T> T read(final Reader reader, final Class<T> objectClass) throws IOException {

        Arguments.isNotNull(reader, "reader is required to be non null.");

        try (final PEMParser pemParser = new PEMParser(reader)) {

            final Object pemObject = pemParser.readObject();

            if (pemObject == null) {
                return null;
            }

            try {
                return Cast.cast(pemObject);

            } catch (final ClassCastException e) {
                throw new CryptoException("PEM did not contain correct object type.", e);
            }
        }

    }
}
