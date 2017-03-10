package com.yunikov.vertx.api.endpoint;

import com.yunikov.vertx.api.commons.JsonMedia;
import com.yunikov.vertx.domain.system.System;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class SystemEndpoint extends Endpoint {

    private final System system;

    public SystemEndpoint(final Router router, final System system) {
        super(router);
        this.system = system;

        router().get("/system/test").handler(this::test);
    }

    private void test(final RoutingContext routingContext) {
        system.applicationTest()
                .setHandler(jsonResponseHandler(routingContext, 200,
                        applicationTest -> applicationTest.printString(new JsonMedia())));
    }
}
