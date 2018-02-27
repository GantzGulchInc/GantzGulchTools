package com.gantzgulch.tools.crypto.alg.mac;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.gantzgulch.tools.crypto.BouncyCastleState;
import com.gantzgulch.tools.crypto.GGHmac;
import com.gantzgulch.tools.crypto.exception.CryptoException;

public class GGHmacImpl implements GGHmac {

    public static final GGHmacImpl MD5 = new GGHmacImpl("HmacMD5");
    public static final GGHmacImpl SHA_1 = new GGHmacImpl("HmacSHA1");
    public static final GGHmacImpl SHA_224 = new GGHmacImpl("HmacSHA224");
    public static final GGHmacImpl SHA_256 = new GGHmacImpl("HmacSHA256");
    public static final GGHmacImpl SHA_384 = new GGHmacImpl("HmacSHA384");
    public static final GGHmacImpl SHA_512 = new GGHmacImpl("HmacSHA512");
    

    static {
        BouncyCastleState.init();
    }

    protected final String algorithm;

    public GGHmacImpl(final String algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public String getAlgorithm() {
        return algorithm;
    }
    
    @Override
    public byte[] sign(final byte[] secret, final byte[] input) {

        try {

            final Mac mac = createMac();

            mac.init(new SecretKeySpec(secret, algorithm));

            return mac.doFinal(input);

        } catch (final NoSuchAlgorithmException | InvalidKeyException e) {
            throw new CryptoException(e);
        }

    }

    private Mac createMac() throws NoSuchAlgorithmException {
        return Mac.getInstance(algorithm);
    }
}
