package com.yunikov.vertx.api;

import com.yunikov.vertx.api.commons.ApiError;
import com.yunikov.vertx.api.commons.JsonMedia;
import com.yunikov.vertx.api.endpoint.Endpoints;
import com.yunikov.vertx.api.vertx.VerticleException;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpHeaders;
import io.vertx.ext.web.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiVerticle extends AbstractVerticle {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractVerticle.class);

    @Override
    public void init(final Vertx vertx, final Context context) {
        super.init(vertx, context);
        java.lang.System.setProperty("vertx.logger-delegate-factory-class-name", "io.vertx.core.logging.SLF4JLogDelegateFactory");
    }

    @Override
    public void start(final Future<Void> startFuture) throws Exception {
        final Router router = router();

        new Endpoints(vertx, router).initialize();

        final int port = config().getInteger("http.port", 8080);
        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(port,
                        result -> {
                            if (result.succeeded()) {
                                LOG.info("Started API on port " + port);
                                startFuture.complete();
                            } else {
                                startFuture.fail(result.cause());
                            }
                        });
    }

    private Router router() {
        final Router router = Router.router(vertx);
        router.route().failureHandler(routingContext -> {
            final VerticleException exception = new VerticleException(routingContext.failure());
            routingContext.response().putHeader(HttpHeaders.CONTENT_TYPE, JsonMedia.CONTENT_TYPE_UTF_8);
            routingContext.response().setStatusCode(exception.getStatusCode());
            routingContext.response().end(new ApiError(exception).printString(new JsonMedia()));
        });

        return router;
    }
}
