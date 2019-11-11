package com.gantzgulch.tools.crypto.openssl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.security.Key;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.generators.OpenSSLPBEParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

import com.gantzgulch.tools.common.logging.GGLogger;
import com.gantzgulch.tools.crypto.GGCiphers;
import com.gantzgulch.tools.crypto.GGNonces;
import com.gantzgulch.tools.crypto.GGOpenSSL;
import com.gantzgulch.tools.crypto.alg.aes.AESKeyGenerator;
import com.gantzgulch.tools.crypto.exception.CryptoException;

public class AESOpenSSL implements GGOpenSSL {

    public static final AESOpenSSL AES_128_CBC = new AESOpenSSL(128);

    public static final AESOpenSSL AES_256_CBC = new AESOpenSSL(256);

    private static final GGLogger LOG = GGLogger.getLogger(AESOpenSSL.class);

    private static final int AES_NIVBITS = 128;

    private int keyLengthBits;

    public AESOpenSSL(final int keyLengthBits) {
        this.keyLengthBits = keyLengthBits;
    }

    @Override
    public void encrypt(final byte[] password, final InputStream is, final OutputStream os) {

        byte[] salted = "Salted__".getBytes(StandardCharsets.US_ASCII);

        byte[] salt = GGNonces.SECURE_RANDOM.nonce(8);

        final ParametersWithIV piv = (ParametersWithIV) getCipherParameters(keyLengthBits, password, salt);

        final byte[] iv = piv.getIV();

        final KeyParameter kp = (KeyParameter) piv.getParameters();

        final Key key = AESKeyGenerator.create(kp.getKey());

        try {

            os.write(salted);

            os.write(salt);

            os.flush();

            GGCiphers.AES_CBC_PKCS7_PADDING.encrypt(key, is, os, iv);

        } catch (final IOException e) {

            LOG.warn(e, "Error encrypting stream: %s", e.getMessage());

            throw new CryptoException(e);
        }

    }

    @Override
    public void decrypt(final byte[] password, final InputStream is, final OutputStream os) {

        byte[] salted = new byte[8];
        byte[] salt = new byte[8];

        readFully(is, salted);

        readFully(is, salt);

        final ParametersWithIV piv = (ParametersWithIV) getCipherParameters(keyLengthBits, password, salt);

        final byte[] iv = piv.getIV();

        final KeyParameter kp = (KeyParameter) piv.getParameters();

        final Key key = AESKeyGenerator.create(kp.getKey());

        GGCiphers.AES_CBC_PKCS7_PADDING.decrypt(key, is, os, iv);

    }

    private void readFully(final InputStream is, final byte[] buffer) {

        try {
            int offset = 0;
            int lengthToRead = buffer.length;

            while (lengthToRead > 0) {

                int lenRead = is.read(buffer, offset, lengthToRead);

                offset += lenRead;

                lengthToRead -= lenRead;

            }

        } catch (final IOException e) {
            LOG.warn(e, "Error reading stream: %s", e.getMessage());
            throw new CryptoException(e);
        }

    }

    private CipherParameters getCipherParameters(int keyLenBits, byte[] pwd, byte[] salt) {

        // Use bouncycastle implementation of openssl non-standard
        // (pwd,salt)->(key,iv) algorithm.
        // Note that if a "CBC" cipher is selected, then an IV is required as
        // well as a key. When using a password, Openssl
        // *derives* the IV from the (pwd,salt) pair at the same time as it
        // derives the key.
        //
        // * PBE = Password Based Encryption
        // * CBC = Cipher Block Chaining (ie IV is needed)
        //
        // Note also that when the IV is derived from (pwd, salt) the salt
        // **must** be different for each message; this is
        // the default for openssl - just make sure to NOT explicitly provide a
        // salt, or encryption security is badly affected.

        OpenSSLPBEParametersGenerator gen = new OpenSSLPBEParametersGenerator();

        gen.init(pwd, salt);

        CipherParameters cp = gen.generateDerivedParameters(keyLenBits, AES_NIVBITS);

        return cp;
    }
}
