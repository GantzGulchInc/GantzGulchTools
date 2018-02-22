package com.gantzgulch.tools.crypto.pem;

import java.io.IOException;
import java.io.StringWriter;

import org.bouncycastle.openssl.jcajce.JcaPEMWriter;

import com.gantzgulch.tools.crypto.exception.CryptoException;

public final class PEMWriter {

    private PEMWriter() {
        throw new UnsupportedOperationException();
    }

    public static String write(final Object object) {

        try {
            final StringWriter stringWriter = new StringWriter();

            final JcaPEMWriter pemWriter = new JcaPEMWriter(stringWriter);

            pemWriter.writeObject(object);
            pemWriter.close();

            return stringWriter.toString();
            
        } catch (final IOException e) {
            
            throw new CryptoException(e);
        }
    }

}
