package com.gantzgulch.tools.aws.exception;

public class GGAwsException extends RuntimeException {

    private static final long serialVersionUID = -6653600928973737199L;

    public GGAwsException() {
    }

    public GGAwsException(final String message) {
        super(message);
    }

    public GGAwsException(final Throwable cause) {
        super(cause);
    }

    public GGAwsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public GGAwsException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
