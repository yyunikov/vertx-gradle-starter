package com.yunikov.vertx.api.endpoint;

import com.yunikov.vertx.domain.configs.GradleProperties;
import com.yunikov.vertx.domain.system.System;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

/**
 * Initializes all the endpoints with {@link Router} and {@link Vertx} objects.
 * Can be replaced by any DI framework.
 */
public class Endpoints {

    private final Vertx vertx;
    private final Router router;

    public Endpoints(final Vertx vertx, final Router router) {
        this.vertx = vertx;
        this.router = router;
    }

    public void initialize() {
        initSystemEndpoint(vertx, router);
    }

    private void initSystemEndpoint(final Vertx vertx, final Router router) {
        new SystemEndpoint(router,
                new System(
                        new GradleProperties(vertx)
                )
        );
    }
}
