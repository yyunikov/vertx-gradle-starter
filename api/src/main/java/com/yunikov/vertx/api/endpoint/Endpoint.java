package com.yunikov.vertx.api.endpoint;

import com.yunikov.vertx.api.commons.JsonMedia;
import com.yunikov.vertx.api.vertx.VerticleException;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpHeaders;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import java.util.function.Function;

public abstract class Endpoint {

    private final Router router;

    public Endpoint(final Router router) {
        this.router = router;
    }

    protected <T> Handler<AsyncResult<T>> jsonResponseHandler(final RoutingContext routingContext,
                                                              final int statusCode,
                                                              final Function<T, String> viewCreationFunction) {
        return result -> {
            if (result.succeeded()) {
                routingContext.response()
                        .setStatusCode(statusCode)
                        .putHeader(HttpHeaders.CONTENT_TYPE, JsonMedia.CONTENT_TYPE_UTF_8)
                        .end(viewCreationFunction.apply(result.result()));
            } else {
                routingContext.fail(new VerticleException(result.cause()));
            }
        };
    }

    protected Router router() {
        return router;
    }
}
