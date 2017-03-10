package com.yunikov.vertx.domain.commons;

public interface MediaPrinter {

    Media print(final Media media);

    default String printString(final Media media) {
        return print(media).toString();
    }
}
