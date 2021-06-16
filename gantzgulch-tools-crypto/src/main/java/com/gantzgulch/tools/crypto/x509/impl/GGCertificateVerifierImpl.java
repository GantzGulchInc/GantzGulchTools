package com.gantzgulch.tools.crypto.x509.impl;

import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.cert.CertPathBuilder;
import java.security.cert.CertStore;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.PKIXCertPathBuilderResult;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.security.auth.x500.X500Principal;

import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.tools.crypto.BouncyCastleState;
import com.gantzgulch.tools.crypto.x509.GGCertificateVerifier;
import com.gantzgulch.tools.crypto.x509.GGCertificateVerifyResult;

public class GGCertificateVerifierImpl implements GGCertificateVerifier {

    public static final GGLogger LOG = GGLogger.getLogger(GGCertificateVerifierImpl.class);

    static {
        BouncyCastleState.init();
    }

    public GGCertificateVerifierImpl() {

    }

    @Override
    public GGCertificateVerifyResult verify(final X509Certificate cert, final Set<X509Certificate> intermediateCerts, final Set<X509Certificate> caCerts) {

        try {

            if (isSelfSignedImpl(cert)) {
                
                LOG.debug("Certificate is self signed.");
                
                throw new GeneralSecurityException("Certificate is self signed.");
            }

            final TrustedCerts tc = new TrustedCerts(intermediateCerts, caCerts);

            final PKIXCertPathBuilderResult result = verifyCertificate(cert, tc);

            if( ! caCerts.contains( result.getTrustAnchor().getTrustedCert() ) ) {
                
                throw new GeneralSecurityException("Trusted anchor was not from caCerts.");
                
            }
            
            return new GGCertificateVerifyResult(result);

        } catch (final RuntimeException re) {
            return new GGCertificateVerifyResult(re);
        } catch (final GeneralSecurityException gse) {
            return new GGCertificateVerifyResult(gse);
        }

    }

    @Override
    public boolean isSelfSigned(final X509Certificate cert) {
        return isSelfSignedImpl(cert);
    }

    public static boolean isSelfSignedImpl(final X509Certificate cert) {

        try {
            
            LOG.trace("NotAfter: %s", cert.getNotAfter() );
            
            final X500Principal subject = cert.getSubjectX500Principal();
            final X500Principal issuer = cert.getIssuerX500Principal();

            if( ! Objects.equals(subject, issuer) ){
            
                LOG.trace("isSelfSignedImpl: mismatch: subject: %s, issue: %s", subject, issuer);
                
                return false;
            }
            
            LOG.trace("isSelfSignedImpl: match: subject: %s, issue: %s", subject, issuer);

            final PublicKey key = cert.getPublicKey();

            cert.verify(key, BouncyCastleState.BOUNCY_CASTLE_PROVIDER);

            return true;

        } catch (final RuntimeException re) {

            LOG.warn(re, "isSelfSigned: error.");
            
            return false;
            
        } catch (final GeneralSecurityException gse) {

            LOG.warn(gse, "isSelfSigned: error.");
            
            return false;
        }

    }

    private static PKIXCertPathBuilderResult verifyCertificate(final X509Certificate cert, final TrustedCerts trustedCerts) throws GeneralSecurityException {

        final X509CertSelector selector = new X509CertSelector();
        selector.setCertificate(cert);

        final Set<TrustAnchor> trustAnchors = trustedCerts.createTrustAnchors();

        final PKIXBuilderParameters pkixParams = new PKIXBuilderParameters(trustAnchors, selector);
        pkixParams.setRevocationEnabled(false);

        final CertStore intermediateCertStore = trustedCerts.createIntermediateCertStore();
        pkixParams.addCertStore(intermediateCertStore);

        final CertPathBuilder builder = CertPathBuilder.getInstance("PKIX", BouncyCastleState.BOUNCY_CASTLE_PROVIDER);

        final PKIXCertPathBuilderResult result = (PKIXCertPathBuilderResult) builder.build(pkixParams);

        return result;
    }

    private static class TrustedCerts {

        final Set<X509Certificate> rootCerts = new HashSet<>();
        final Set<X509Certificate> intermeditateCerts = new HashSet<>();

        public TrustedCerts(final Set<X509Certificate> intermediateCerts, final Set<X509Certificate> caCerts) {

            if (intermediateCerts != null) {
                
                for (final X509Certificate cert : intermediateCerts) {

                    if (isSelfSignedImpl(cert)) {
                        rootCerts.add(cert);
                    } else {
                        intermeditateCerts.add(cert);
                    }
                }
            }
            
            if( caCerts != null ){
                
                for(final X509Certificate cert : caCerts ){

                    if (isSelfSignedImpl(cert)) {
                        rootCerts.add(cert);
                    } else {
                        intermeditateCerts.add(cert);
                    }
                    
                }
            }

        }

        public Set<TrustAnchor> createTrustAnchors() {

            final Set<TrustAnchor> trustAnchors = new HashSet<>();

            for (final X509Certificate cert : rootCerts) {

                trustAnchors.add(new TrustAnchor(cert, null));
            }

            return trustAnchors;
        }

        public CertStore createIntermediateCertStore() throws GeneralSecurityException {

            final CollectionCertStoreParameters params = new CollectionCertStoreParameters(intermeditateCerts);

            final CertStore intermediateCertStore = CertStore.getInstance("Collection", params, BouncyCastleState.BOUNCY_CASTLE_PROVIDER);

            return intermediateCertStore;

        }
    }
}
