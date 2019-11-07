package com.gantzgulch.tools.crypto.openssl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.generators.OpenSSLPBEParametersGenerator;
import org.bouncycastle.crypto.io.CipherInputStream;
import org.bouncycastle.crypto.io.CipherOutputStream;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.BlockCipherPadding;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;

import com.gantzgulch.tools.common.lang.GGIO;
import com.gantzgulch.tools.common.logging.GGLogger;
import com.gantzgulch.tools.crypto.GGNonces;
import com.gantzgulch.tools.crypto.GGOpenSSL;
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

        final BufferedBlockCipher cipher = createCipher(true, password, salt);

        try (final CipherOutputStream cos = new CipherOutputStream(os, cipher)) {

            os.write(salted);

            os.write(salt);
            
            os.flush();

            GGIO.copy(is, cos);

            cos.flush();

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

        final BufferedBlockCipher cipher = createCipher(false, password, salt);

        try (final CipherInputStream cis = new CipherInputStream(is, cipher)) {

            GGIO.copy(cis, os);

        } catch (final IOException e) {

            LOG.warn(e, "Error decrypting stream: %s", e.getMessage());

            throw new CryptoException(e);

        }
    }

    private BufferedBlockCipher createCipher(final boolean forEncryption, final byte[] password, final byte[] salt) {

        BlockCipherPadding padding = new PKCS7Padding();
        BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESEngine()), padding);

        CipherParameters params = getCipherParameters(keyLengthBits, password, salt);
        cipher.reset();
        cipher.init(forEncryption, params);

        return cipher;

    }

    // public static void aes256cbcDecrypt(final byte[] password, final
    // InputStream is, final OutputStream os) {
    //
    // final int keyLenBits = 256;
    //
    // byte[] salted = new byte[8];
    // byte[] salt = new byte[8];
    //
    // // byte[] salt = Arrays.copyOfRange(src, 8, 16); // 0..7 is "SALTED__",
    // // 8..15 is the salt
    //
    // try {
    //
    // readFully(is, salted);
    //
    // readFully(is, salt);
    //
    // // Encryption algorithm. Note that the "strength" (bitsize) is
    // // controlled by the key object that is used.
    // // Note that PKCS5 padding and PKCS7 padding are identical.
    // BlockCipherPadding padding = new PKCS7Padding();
    // BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new
    // CBCBlockCipher(new AESEngine()), padding);
    //
    // CipherParameters params = getCipherParameters(keyLenBits, password,
    // salt);
    // cipher.reset();
    // cipher.init(false, params);
    //
    // final CipherInputStream cis = new CipherInputStream(is, cipher);
    //
    // GGIO.copy(cis, os);
    //
    // // int buflen = cipher.getOutputSize(src.length - 16);
    // // byte[] workingBuffer = new byte[buflen];
    // // int len = cipher.processBytes(src, 16, src.length - 16,
    // // workingBuffer, 0);
    // // len += cipher.doFinal(workingBuffer, len);
    // //
    // // // Note that getOutputSize returns a number which includes space
    // // for "padding" bytes to be stored in.
    // // // However we don't want these padding bytes; the "len" variable
    // // contains the length of the *real* data
    // // // (which is always less than the return value of getOutputSize.
    // // byte[] bytesDec = new byte[len];
    // // System.arraycopy(workingBuffer, 0, bytesDec, 0, len);
    // // return bytesDec;
    //
    // } catch (final IOException | RuntimeException e) {
    //
    // LOG.warn(e, "Unexpected error: %s", e.getMessage());
    //
    // throw new CryptoException(e);
    // }
    //
    // }

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
