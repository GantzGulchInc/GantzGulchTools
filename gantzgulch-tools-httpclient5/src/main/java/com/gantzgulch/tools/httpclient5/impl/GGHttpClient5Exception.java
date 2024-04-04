package com.gantzgulch.tools.httpclient5.impl;

public class GGHttpClient5Exception extends RuntimeException {

    private static final long serialVersionUID = 1028271991011126549L;

    public GGHttpClient5Exception() {
    }

    public GGHttpClient5Exception(final String message) {
        super(message);
    }

    public GGHttpClient5Exception(final Throwable cause) {
        super(cause);
    }

    public GGHttpClient5Exception(final String message, final Throwable cause) {
        super(message, cause);
    }

    public GGHttpClient5Exception(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
