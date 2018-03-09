package com.gantzgulch.tools.crypto.alg.rsa;

import java.util.ArrayList;
import java.util.List;

import com.gantzgulch.tools.crypto.GGIvSpec;
import com.gantzgulch.tools.crypto.GGKeySpec;
import com.gantzgulch.tools.crypto.GGNonceSpec;
import com.gantzgulch.tools.crypto.alg.impl.AbstractGGCipher;

public class RSACipher extends AbstractGGCipher {

    public static final List<RSACipher> CIPHERS = new ArrayList<>();
    
    public static final RSACipher RSA_NONE_OAEPWithMD5AndMGF1Padding = new RSACipher("RSA/NONE/OAEPWithMD5AndMGF1Padding", KEY_RSA, IV_NONE, NONCE_NONE);
    public static final RSACipher RSA_NONE_OAEPWithSHA_1AndMGF1Padding = new RSACipher("RSA/NONE/OAEPWithSHA1AndMGF1Padding", KEY_RSA, IV_NONE, NONCE_NONE);
    public static final RSACipher RSA_NONE_OAEPWithSHA_224AndMGF1Padding = new RSACipher("RSA/NONE/OAEPWithSHA224AndMGF1Padding", KEY_RSA, IV_NONE, NONCE_NONE);
    public static final RSACipher RSA_NONE_OAEPWithSHA_256AndMGF1Padding = new RSACipher("RSA/NONE/OAEPWithSHA256AndMGF1Padding", KEY_RSA, IV_NONE, NONCE_NONE);
    public static final RSACipher RSA_NONE_OAEPWithSHA_384AndMGF1Padding = new RSACipher("RSA/NONE/OAEPWithSHA384AndMGF1Padding", KEY_RSA, IV_NONE, NONCE_NONE);
    public static final RSACipher RSA_NONE_OAEPWithSHA_512AndMGF1Padding = new RSACipher("RSA/NONE/OAEPWithSHA512AndMGF1Padding", KEY_RSA, IV_NONE, NONCE_NONE);

    private RSACipher(final String algorithm, final GGKeySpec keySpec, final GGIvSpec ivSpec, final GGNonceSpec nonceSpec) {

        super(algorithm, keySpec, ivSpec, nonceSpec);

        CIPHERS.add(this);
    }

}
