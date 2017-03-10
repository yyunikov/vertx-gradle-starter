package com.yunikov.vertx.api.commons;

import com.yunikov.vertx.api.vertx.VerticleException;
import com.yunikov.vertx.domain.commons.Media;
import com.yunikov.vertx.domain.commons.MediaPrintable;
import io.netty.handler.codec.http.HttpResponseStatus;

public class ApiError implements MediaPrintable {

    private final VerticleException exception;

    public ApiError(final VerticleException exception) {
        this.exception = exception;
    }

    @Override
    public Media print(final Media media) {
        return media.with("timestamp", timeStamp())
                .with("status", status())
                .with("error", error())
                .with("message", message());
    }

    private long timeStamp() {
        return java.lang.System.nanoTime();
    }

    private int status() {
        return exception.getStatusCode();
    }

    private String error() {
        return HttpResponseStatus.valueOf(exception.getStatusCode()).reasonPhrase();
    }

    private String message() {
        return exception.getMessage();
    }
}
