package com.gantzgulch.tools.httpclient.exception;

public class GGHttpClientResponseException extends RuntimeException {

    private static final long serialVersionUID = 1028271991011126549L;
    
    private final int status;
    private final String body;

    public GGHttpClientResponseException(final String message, final int status, final String body) {
        super(message);
        this.status = status;
        this.body = body;
    }
    
    public int getStatus() {
        return status;
    }
    
    public String getBody() {
        return body;
    }
}
