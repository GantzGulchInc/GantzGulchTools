package com.gantzgulch.tools.crypto.pkcs10;

import java.security.KeyPair;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;

import com.gantzgulch.tools.common.lang.GGArgs;
import com.gantzgulch.tools.crypto.BouncyCastleState;

public class PKCS10Service {

    static {
        BouncyCastleState.init();
    }

    public PKCS10Service() {
    }

    public PKCS10CertificationRequest buildCertificateSigningRequest(//
            final KeyPair keyPair, //
            final X500Name subject, 
            final String algorithm) throws OperatorCreationException {

        GGArgs.notNull(keyPair, "keyPair");
        GGArgs.notNull(subject, "subject");
        GGArgs.notNull(algorithm, "algorithm");

        final PKCS10CertificationRequestBuilder builder = new JcaPKCS10CertificationRequestBuilder(subject, keyPair.getPublic());

        final JcaContentSignerBuilder csBuilder = new JcaContentSignerBuilder(algorithm);

        final ContentSigner signer = csBuilder.build(keyPair.getPrivate());

        final PKCS10CertificationRequest csr = builder.build(signer);

        return csr;
    }


}
