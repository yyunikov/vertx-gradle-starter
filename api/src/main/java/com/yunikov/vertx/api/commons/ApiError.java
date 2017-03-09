package com.yunikov.vertx.api.commons;

import com.yunikov.vertx.api.vertx.VerticleException;
import io.netty.handler.codec.http.HttpResponseStatus;

public class ApiError {

    private final VerticleException exception;

    public ApiError(final VerticleException exception) {
        this.exception = exception;
    }

    @SuppressWarnings("unused")
    public long getTimestamp() {
        return java.lang.System.nanoTime();
    }

    @SuppressWarnings("unused")
    public int getStatus() {
        return exception.getStatusCode();
    }

    @SuppressWarnings("unused")
    public String getError() {
        return HttpResponseStatus.valueOf(exception.getStatusCode()).reasonPhrase();
    }

    @SuppressWarnings("unused")
    public String getMessage() {
        return exception.getMessage();
    }
}
