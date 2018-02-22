package com.gantzgulch.tools.json;

public class GGJsonException extends RuntimeException {

    private static final long serialVersionUID = 9143595832443695086L;

    public GGJsonException() {
    }

    public GGJsonException(final String message) {
        super(message);
    }

    public GGJsonException(final Throwable cause) {
        super(cause);
    }

    public GGJsonException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public GGJsonException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
