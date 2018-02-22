package com.gantzgulch.tools.crypto;

import com.gantzgulch.tools.crypto.alg.aes.AESAEADCipher;
import com.gantzgulch.tools.crypto.alg.aes.AESCipher;
import com.gantzgulch.tools.crypto.alg.aes.AESGCMCipher;
import com.gantzgulch.tools.crypto.alg.blowfish.BlowfishCipher;
import com.gantzgulch.tools.crypto.alg.rsa.RSACipher;

public final class GGCiphers {

    public static final AESAEADCipher AES_CBC_NO_PADDING = AESAEADCipher.AES_CBC_NO_PADDING;
    public static final AESAEADCipher AES_CCM_NO_PADDING = AESAEADCipher.AES_CCM_NO_PADDING;
    public static final AESAEADCipher AES_EAX_NO_PADDING = AESAEADCipher.AES_EAX_NO_PADDING;
    public static final AESAEADCipher AES_OCB_NO_PADDING = AESAEADCipher.AES_OCB_NO_PADDING;
    public static final AESCipher AES_ECB_NO_PADDING = AESCipher.AES_ECB_NO_PADDING;
    public static final AESGCMCipher AES_GCM_NO_PADDING = AESGCMCipher.AES_GCM_NO_PADDING;
    
    public static final RSACipher RSA_NONE_OAEPWithMD5AndMGF1Padding = RSACipher.RSA_NONE_OAEPWithMD5AndMGF1Padding;
    public static final RSACipher RSA_NONE_OAEPWithSHA_1AndMGF1Padding = RSACipher.RSA_NONE_OAEPWithSHA_1AndMGF1Padding;
    public static final RSACipher RSA_NONE_OAEPWithSHA_224AndMGF1Padding = RSACipher.RSA_NONE_OAEPWithSHA_224AndMGF1Padding;
    public static final RSACipher RSA_NONE_OAEPWithSHA_256AndMGF1Padding = RSACipher.RSA_NONE_OAEPWithSHA_256AndMGF1Padding;
    public static final RSACipher RSA_NONE_OAEPWithSHA_384AndMGF1Padding = RSACipher.RSA_NONE_OAEPWithSHA_384AndMGF1Padding;
    public static final RSACipher RSA_NONE_OAEPWithSHA_512AndMGF1Padding = RSACipher.RSA_NONE_OAEPWithSHA_512AndMGF1Padding;

    public static final BlowfishCipher BLOWFISH_ECB_NO_PADDING = BlowfishCipher.BLOWFISH_ECB_NO_PADDING;
    public static final BlowfishCipher BLOWFISH_ECB_NO_PKCS5Padding = BlowfishCipher.BLOWFISH_ECB_NO_PKCS5Padding;

    private GGCiphers() {
        throw new UnsupportedOperationException();
    }

}
