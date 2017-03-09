package com.yunikov.vertx.domain.configs;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class GradleProperties {

    private final ConfigRetriever configRetriever;
    private boolean cached = false;

    public GradleProperties(final Vertx vertx) {
        final ConfigStoreOptions storeOptions = new ConfigStoreOptions()
                .setType("file")
                .setFormat("properties")
                .setConfig(new JsonObject().put("path", "gradle.properties"));
        final ConfigRetrieverOptions retrieverOptions = new ConfigRetrieverOptions()
                .addStore(storeOptions);
        configRetriever = ConfigRetriever.create(vertx, retrieverOptions);
    }

    public Future<String> version() {
        return config().map(json -> json.getString("version"));
    }

    private Future<JsonObject> config() {
        if (cached) {
            return Future.succeededFuture(configRetriever.getCachedConfig());
        }

        final Future<JsonObject> config = ConfigRetriever.getConfigAsFuture(configRetriever);
        cached = true;
        return config;
    }
}
