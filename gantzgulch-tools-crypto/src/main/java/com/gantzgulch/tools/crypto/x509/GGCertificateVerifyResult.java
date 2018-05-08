package com.gantzgulch.tools.crypto.x509;

import java.security.cert.PKIXCertPathBuilderResult;

public class GGCertificateVerifyResult {

    final PKIXCertPathBuilderResult result;
    
    final Exception exception;
    
    public GGCertificateVerifyResult(final PKIXCertPathBuilderResult result) {
        this.result = result;
        this.exception = null;
    }

    public GGCertificateVerifyResult(final Exception e) {
        this.result = null;
        this.exception = e;
    }
    
    public boolean isValid(){
        return result != null && exception == null;
    }
    
    public PKIXCertPathBuilderResult getResult() {
        return result;
    }
    
    public Exception getException() {
        return exception;
    }
    
    @Override
    public String toString() {
        
        final StringBuilder b = new StringBuilder();
        
        b.append("GGCertificateVerifyResult: Path: ");
        b.append(result);
        b.append(", Exception: ");
        b.append(exception);
        
        return b.toString();
    }

}
