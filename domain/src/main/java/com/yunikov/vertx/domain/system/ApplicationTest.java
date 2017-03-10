package com.yunikov.vertx.domain.system;

import com.yunikov.vertx.domain.commons.Media;
import com.yunikov.vertx.domain.commons.MediaPrintable;

public class ApplicationTest implements MediaPrintable {
    private final String hostname;
    private final String version;

    public ApplicationTest(final String hostname, final String version) {
        this.hostname = hostname;
        this.version = version;
    }

    @Override
    public Media print(final Media media) {
        return media.with("hostname", hostname)
                .with("version", version);
    }
}
