package com.yunikov.vertx.api;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.ServerSocket;

@RunWith(VertxUnitRunner.class)
public class ApiVerticleTest {

    private Vertx vertx;
    private int port;

    @Before
    public void setUp(final TestContext context) throws IOException {
        vertx = Vertx.vertx();
        final ServerSocket socket = new ServerSocket(0);
        port = socket.getLocalPort();
        socket.close();
        final DeploymentOptions options = new DeploymentOptions()
                .setConfig(new JsonObject().put("http.port", port));
        vertx.deployVerticle(ApiVerticle.class.getName(), options, context.asyncAssertSuccess());
    }

    @After
    public void tearDown(final TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    public void serverIsStarting(final TestContext context) {
        final Async async = context.async();
        vertx.createHttpClient().getNow(port, "localhost", "/system/test", response -> {
            context.assertEquals(response.statusCode(), 200);
            response.bodyHandler(body -> {
                context.assertTrue(body.length() > 0);
                context.assertNotNull(body.toJsonObject().getString("version"));
                context.assertNotNull(body.toJsonObject().getString("hostname"));
                async.complete();
            });
        });
    }
}
