package com.yunikov.vertx.api.vertx;

public class VerticleException extends RuntimeException {

    private static final int DEFAULT_STATUS_CODE = 500;

    private int statusCode;

    public VerticleException() {
        super();
        statusCode = DEFAULT_STATUS_CODE;
    }

    public VerticleException(final int code) {
        super();
        statusCode = code;
    }

    public VerticleException(final String message) {
        super(message);
        statusCode = DEFAULT_STATUS_CODE;
    }

    public VerticleException(final Throwable cause) {
        super(cause);
        statusCode = DEFAULT_STATUS_CODE;
    }

    public VerticleException(final String message, final Throwable cause) {
        super(message, cause);
        statusCode = DEFAULT_STATUS_CODE;
    }

    public VerticleException(final String message, final int code) {
        super(message);
        statusCode = code;
    }

    public VerticleException(final Throwable cause, final int code) {
        super(cause);
        statusCode = code;
    }

    public VerticleException(final String message, final Throwable cause, final int code) {
        super(message, cause);
        statusCode = code;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
