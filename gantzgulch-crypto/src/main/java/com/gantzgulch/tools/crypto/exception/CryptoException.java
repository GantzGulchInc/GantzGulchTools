package com.gantzgulch.tools.crypto.exception;

public class CryptoException extends RuntimeException {

    private static final long serialVersionUID = -2075486822965347254L;

    public CryptoException() {
    }

    public CryptoException(final String message) {
        super(message);
    }

    public CryptoException(final Throwable cause) {
        super(cause);
    }

    public CryptoException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CryptoException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
