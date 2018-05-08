package com.gantzgulch.tools.crypto.x509;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.gantzgulch.tools.common.logging.GGLogger;
import com.gantzgulch.tools.crypto.pkcs10.PKCS10Reader;
import com.gantzgulch.tools.crypto.x509.impl.GGCertificateVerifierImpl;

public class GGCertificateVerifierTest {

    // Google wildcard certificate
    public static final String CERTIFICATE1 = "" + //
            "-----BEGIN CERTIFICATE-----\n" + //
            "MIIDxzCCAq+gAwIBAgIIBQoKGOts8hswDQYJKoZIhvcNAQELBQAwVDELMAkGA1UE\n" + //
            "BhMCVVMxHjAcBgNVBAoTFUdvb2dsZSBUcnVzdCBTZXJ2aWNlczElMCMGA1UEAxMc\n" + //
            "R29vZ2xlIEludGVybmV0IEF1dGhvcml0eSBHMzAeFw0xODA0MTcxMzI3MzZaFw0x\n" + //
            "ODA3MTAxMjM5MDBaMGgxCzAJBgNVBAYTAlVTMRMwEQYDVQQIDApDYWxpZm9ybmlh\n" + //
            "MRYwFAYDVQQHDA1Nb3VudGFpbiBWaWV3MRMwEQYDVQQKDApHb29nbGUgSW5jMRcw\n" + //
            "FQYDVQQDDA53d3cuZ29vZ2xlLmNvbTBZMBMGByqGSM49AgEGCCqGSM49AwEHA0IA\n" + //
            "BDe3g/rFaC7eGVPGidStIyuVs55ryUeB8LN7G/fxW/aPCycuM/FH+RHR9bE5YgLJ\n" + //
            "0qEEELQoRmGJQK+b7up6S52jggFSMIIBTjATBgNVHSUEDDAKBggrBgEFBQcDATAO\n" + //
            "BgNVHQ8BAf8EBAMCB4AwGQYDVR0RBBIwEIIOd3d3Lmdvb2dsZS5jb20waAYIKwYB\n" + //
            "BQUHAQEEXDBaMC0GCCsGAQUFBzAChiFodHRwOi8vcGtpLmdvb2cvZ3NyMi9HVFNH\n" + //
            "SUFHMy5jcnQwKQYIKwYBBQUHMAGGHWh0dHA6Ly9vY3NwLnBraS5nb29nL0dUU0dJ\n" + //
            "QUczMB0GA1UdDgQWBBQU05cN9ehLernVmgLZkCNM8xVhLzAMBgNVHRMBAf8EAjAA\n" + //
            "MB8GA1UdIwQYMBaAFHfCuFCaZ3Z2sS3ChtCDoH6mfrpLMCEGA1UdIAQaMBgwDAYK\n" + //
            "KwYBBAHWeQIFAzAIBgZngQwBAgIwMQYDVR0fBCowKDAmoCSgIoYgaHR0cDovL2Ny\n" + //
            "bC5wa2kuZ29vZy9HVFNHSUFHMy5jcmwwDQYJKoZIhvcNAQELBQADggEBAHc8Uokb\n" + //
            "KRxwjUmS/zbmxCqdYhPq8NhxgHooTBUOwpqQmtrYVejnoScNGMSHhlc6eDbg3Cmo\n" + //
            "BLCqW8By4IgMW/5iunMTzKeLn8QXKY+3CnjBQ+O6M8NBI/wDVvPE6mBjkSo5u/kR\n" + //
            "svashIWE/EhU67AeW8n+XB03uJsLy2Jh2x5c60kXSFshSm3X0u+2jj2dyvbGfP/a\n" + //
            "1CjCeQs0f+RJOKvC53jVoutQT5TqsnHnx5IVvwiwKM2IkGXngPw3dxO7XbDguToi\n" + //
            "MOtdgxEZ8VtiD7ivXWpS1YZnquoNhM4lyD8GYyolD4j+IXC8Yl+xk/9WFBnfoUqI\n" + //
            "RtvR3Ujwfb+64L4=\n" + //
            "-----END CERTIFICATE-----\n";

    // Google intermediate certificate
    public static final String CERTIFICATE2 = "" + //
            "-----BEGIN CERTIFICATE-----\n" + //
            "MIIEXDCCA0SgAwIBAgINAeOpMBz8cgY4P5pTHTANBgkqhkiG9w0BAQsFADBMMSAw\n" + //
            "HgYDVQQLExdHbG9iYWxTaWduIFJvb3QgQ0EgLSBSMjETMBEGA1UEChMKR2xvYmFs\n" + //
            "U2lnbjETMBEGA1UEAxMKR2xvYmFsU2lnbjAeFw0xNzA2MTUwMDAwNDJaFw0yMTEy\n" + //
            "MTUwMDAwNDJaMFQxCzAJBgNVBAYTAlVTMR4wHAYDVQQKExVHb29nbGUgVHJ1c3Qg\n" + //
            "U2VydmljZXMxJTAjBgNVBAMTHEdvb2dsZSBJbnRlcm5ldCBBdXRob3JpdHkgRzMw\n" + //
            "ggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDKUkvqHv/OJGuo2nIYaNVW\n" + //
            "XQ5IWi01CXZaz6TIHLGp/lOJ+600/4hbn7vn6AAB3DVzdQOts7G5pH0rJnnOFUAK\n" + //
            "71G4nzKMfHCGUksW/mona+Y2emJQ2N+aicwJKetPKRSIgAuPOB6Aahh8Hb2XO3h9\n" + //
            "RUk2T0HNouB2VzxoMXlkyW7XUR5mw6JkLHnA52XDVoRTWkNty5oCINLvGmnRsJ1z\n" + //
            "ouAqYGVQMc/7sy+/EYhALrVJEA8KbtyX+r8snwU5C1hUrwaW6MWOARa8qBpNQcWT\n" + //
            "kaIeoYvy/sGIJEmjR0vFEwHdp1cSaWIr6/4g72n7OqXwfinu7ZYW97EfoOSQJeAz\n" + //
            "AgMBAAGjggEzMIIBLzAOBgNVHQ8BAf8EBAMCAYYwHQYDVR0lBBYwFAYIKwYBBQUH\n" + //
            "AwEGCCsGAQUFBwMCMBIGA1UdEwEB/wQIMAYBAf8CAQAwHQYDVR0OBBYEFHfCuFCa\n" + //
            "Z3Z2sS3ChtCDoH6mfrpLMB8GA1UdIwQYMBaAFJviB1dnHB7AagbeWbSaLd/cGYYu\n" + //
            "MDUGCCsGAQUFBwEBBCkwJzAlBggrBgEFBQcwAYYZaHR0cDovL29jc3AucGtpLmdv\n" + //
            "b2cvZ3NyMjAyBgNVHR8EKzApMCegJaAjhiFodHRwOi8vY3JsLnBraS5nb29nL2dz\n" + //
            "cjIvZ3NyMi5jcmwwPwYDVR0gBDgwNjA0BgZngQwBAgIwKjAoBggrBgEFBQcCARYc\n" + //
            "aHR0cHM6Ly9wa2kuZ29vZy9yZXBvc2l0b3J5LzANBgkqhkiG9w0BAQsFAAOCAQEA\n" + //
            "HLeJluRT7bvs26gyAZ8so81trUISd7O45skDUmAge1cnxhG1P2cNmSxbWsoiCt2e\n" + //
            "ux9LSD+PAj2LIYRFHW31/6xoic1k4tbWXkDCjir37xTTNqRAMPUyFRWSdvt+nlPq\n" + //
            "wnb8Oa2I/maSJukcxDjNSfpDh/Bd1lZNgdd/8cLdsE3+wypufJ9uXO1iQpnh9zbu\n" + //
            "FIwsIONGl1p3A8CgxkqI/UAih3JaGOqcpcdaCIzkBaR9uYQ1X4k2Vg5APRLouzVy\n" + //
            "7a8IVk6wuy6pm+T7HT4LY8ibS5FEZlfAFLSW8NwsVz9SBK2Vqn1N0PIMn5xA6NZV\n" + //
            "c7o835DLAFshEWfC7TIe3g==\n" + //
            "-----END CERTIFICATE-----\n";
    
    // Google ROOT certificate
    public static final String ROOT_CERTIFICATE = "" + //
            "-----BEGIN CERTIFICATE-----\n" + //
            "MIIDujCCAqKgAwIBAgILBAAAAAABD4Ym5g0wDQYJKoZIhvcNAQEFBQAwTDEgMB4G\n" + //
            "A1UECxMXR2xvYmFsU2lnbiBSb290IENBIC0gUjIxEzARBgNVBAoTCkdsb2JhbFNp\n" + //
            "Z24xEzARBgNVBAMTCkdsb2JhbFNpZ24wHhcNMDYxMjE1MDgwMDAwWhcNMjExMjE1\n" + //
            "MDgwMDAwWjBMMSAwHgYDVQQLExdHbG9iYWxTaWduIFJvb3QgQ0EgLSBSMjETMBEG\n" + //
            "A1UEChMKR2xvYmFsU2lnbjETMBEGA1UEAxMKR2xvYmFsU2lnbjCCASIwDQYJKoZI\n" + //
            "hvcNAQEBBQADggEPADCCAQoCggEBAKbPJA6+Lm8omUVCxKs+IVSbC9N/hHD6ErPL\n" + //
            "v4dfxn+G07IwXNb9rfF73OX4YJYJkhD10FPe+3t+c4isUoh7SqbKSaZeqKeMWhG8\n" + //
            "eoLrvozps6yWJQeXSpkqBy+0Hne/ig+1AnwblrjFuTosvNYSuetZfeLQBoZfXklq\n" + //
            "tTleiDTsvHgMCJiEbKjNS7SgfQx5TfC4LcshytVsW33hoCmEofnTlEnLJGKRILzd\n" + //
            "C9XZzPnqJworc5HGnRusyMvo4KD0L5CLTfuwNhv2GXqF4G3yYROIXJ/gkwpRl4pa\n" + //
            "zq+r1feqCapgvdzZX99yqWATXgAByUr6P6TqBwMhAo6CygPCm48CAwEAAaOBnDCB\n" + //
            "mTAOBgNVHQ8BAf8EBAMCAQYwDwYDVR0TAQH/BAUwAwEB/zAdBgNVHQ4EFgQUm+IH\n" + //
            "V2ccHsBqBt5ZtJot39wZhi4wNgYDVR0fBC8wLTAroCmgJ4YlaHR0cDovL2NybC5n\n" + //
            "bG9iYWxzaWduLm5ldC9yb290LXIyLmNybDAfBgNVHSMEGDAWgBSb4gdXZxwewGoG\n" + //
            "3lm0mi3f3BmGLjANBgkqhkiG9w0BAQUFAAOCAQEAmYFThxxol4aR7OBKuEQLq4Gs\n" + //
            "J0/WwbgcQ3izDJr86iw8bmEbTUsp9Z8FHSbBuOmDAGJFtqkIk7mpM0sYmsL4h4hO\n" + //
            "291xNBrBVNpGP+DTKqttVCL1OmLNIG+6KYnX3ZHu01yiPqFbQfXf5WRDLenVOavS\n" + //
            "ot+3i9DAgBkcRcAtjOj4LaR0VknFBbVPFd5uRHg5h6h+u/N5GJG79G+dwfCMNYxd\n" + //
            "AfvDbbnvRG15RjF+Cv6pgsH/76tuIMRQyV+dTZsXjAzlAcmgQWpzU/qlULRuJQ/7\n" + //
            "TBj0/VLZjmmx6BEP3ojY+x1J96relc8geMJgEtslQIxq/H5COEBkEveegeGTLg==\n" + //
            "-----END CERTIFICATE-----\n";

    // CA Certificate
    public static final String CA_CERTIFICATE = "" + //
            "-----BEGIN CERTIFICATE-----\n" + //
            "MIIDujCCAqKgAwIBAgILBAAAAAABD4Ym5g0wDQYJKoZIhvcNAQEFBQAwTDEgMB4G\n" + //
            "A1UECxMXR2xvYmFsU2lnbiBSb290IENBIC0gUjIxEzARBgNVBAoTCkdsb2JhbFNp\n" + //
            "Z24xEzARBgNVBAMTCkdsb2JhbFNpZ24wHhcNMDYxMjE1MDgwMDAwWhcNMjExMjE1\n" + //
            "MDgwMDAwWjBMMSAwHgYDVQQLExdHbG9iYWxTaWduIFJvb3QgQ0EgLSBSMjETMBEG\n" + //
            "A1UEChMKR2xvYmFsU2lnbjETMBEGA1UEAxMKR2xvYmFsU2lnbjCCASIwDQYJKoZI\n" + //
            "hvcNAQEBBQADggEPADCCAQoCggEBAKbPJA6+Lm8omUVCxKs+IVSbC9N/hHD6ErPL\n" + //
            "v4dfxn+G07IwXNb9rfF73OX4YJYJkhD10FPe+3t+c4isUoh7SqbKSaZeqKeMWhG8\n" + //
            "eoLrvozps6yWJQeXSpkqBy+0Hne/ig+1AnwblrjFuTosvNYSuetZfeLQBoZfXklq\n" + //
            "tTleiDTsvHgMCJiEbKjNS7SgfQx5TfC4LcshytVsW33hoCmEofnTlEnLJGKRILzd\n" + //
            "C9XZzPnqJworc5HGnRusyMvo4KD0L5CLTfuwNhv2GXqF4G3yYROIXJ/gkwpRl4pa\n" + //
            "zq+r1feqCapgvdzZX99yqWATXgAByUr6P6TqBwMhAo6CygPCm48CAwEAAaOBnDCB\n" + //
            "mTAOBgNVHQ8BAf8EBAMCAQYwDwYDVR0TAQH/BAUwAwEB/zAdBgNVHQ4EFgQUm+IH\n" + //
            "V2ccHsBqBt5ZtJot39wZhi4wNgYDVR0fBC8wLTAroCmgJ4YlaHR0cDovL2NybC5n\n" + //
            "bG9iYWxzaWduLm5ldC9yb290LXIyLmNybDAfBgNVHSMEGDAWgBSb4gdXZxwewGoG\n" + //
            "3lm0mi3f3BmGLjANBgkqhkiG9w0BAQUFAAOCAQEAmYFThxxol4aR7OBKuEQLq4Gs\n" + //
            "J0/WwbgcQ3izDJr86iw8bmEbTUsp9Z8FHSbBuOmDAGJFtqkIk7mpM0sYmsL4h4hO\n" + //
            "291xNBrBVNpGP+DTKqttVCL1OmLNIG+6KYnX3ZHu01yiPqFbQfXf5WRDLenVOavS\n" + //
            "ot+3i9DAgBkcRcAtjOj4LaR0VknFBbVPFd5uRHg5h6h+u/N5GJG79G+dwfCMNYxd\n" + //
            "AfvDbbnvRG15RjF+Cv6pgsH/76tuIMRQyV+dTZsXjAzlAcmgQWpzU/qlULRuJQ/7\n" + //
            "TBj0/VLZjmmx6BEP3ojY+x1J96relc8geMJgEtslQIxq/H5COEBkEveegeGTLg==\n" + //
            "-----END CERTIFICATE-----\n";

    
    private static final GGLogger LOG = GGLogger.getLogger(GGCertificateVerifierTest.class);
    
    @Test
    public void testCertificate() {
        
        GGCertificateVerifier verifier = new GGCertificateVerifierImpl();
        
        final Set<X509Certificate> intermediates = createSet(CERTIFICATE1, CERTIFICATE2, ROOT_CERTIFICATE);
        
        final Set<X509Certificate> roots = createSet(CA_CERTIFICATE);
        
        final GGCertificateVerifyResult result = verifier.verify(PKCS10Reader.readCertificate(CERTIFICATE1), intermediates, roots);
        
        LOG.info("result: %s", result);
        
        assertThat(result.isValid(), is(true));
    
    }
    
    
    
    private Set<X509Certificate> createSet(final String ... pems){
        
        final Set<X509Certificate> set = new HashSet<>();
        
        
        for(final String pem : pems){
            
            set.add( PKCS10Reader.readCertificate(pem));
            
        }
        
        
        return set;
    }
}
