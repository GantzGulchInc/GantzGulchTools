package com.gantzgulch.tools.httpclient.exception;

public class GGHttpClientException extends RuntimeException {

    private static final long serialVersionUID = 1028271991011126549L;

    public GGHttpClientException() {
    }

    public GGHttpClientException(final String message) {
        super(message);
    }

    public GGHttpClientException(final Throwable cause) {
        super(cause);
    }

    public GGHttpClientException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public GGHttpClientException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
