package com.gantzgulch.tools.crypto;

import java.security.Security;

import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

public final class BouncyCastleState {

    public static final String BOUNCY_CASTLE_PROVIDER = "BC";

    private static volatile boolean isInitialized = false;

    private BouncyCastleState() {
        throw new UnsupportedOperationException();
    }

    public synchronized static void init() {

        if (isInitialized) {
            return;
        }

        Security.addProvider(new BouncyCastleProvider());

        isInitialized = true;

        return;
    }

    public static JcaX509CertificateConverter createX509Converter() {

        init();

        return new JcaX509CertificateConverter().setProvider(BouncyCastleState.BOUNCY_CASTLE_PROVIDER);

    }

    public static JcaPEMKeyConverter createPemKeyConverter() {

        init();

        return new JcaPEMKeyConverter().setProvider(BouncyCastleState.BOUNCY_CASTLE_PROVIDER);
    }

}
