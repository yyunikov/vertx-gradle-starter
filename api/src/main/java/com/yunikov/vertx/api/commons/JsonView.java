package com.yunikov.vertx.api.commons;

import com.yunikov.vertx.domain.commons.View;
import io.vertx.core.json.Json;

public class JsonView implements View<String> {

    public static final String CONTENT_TYPE_UTF_8 = "application/json; charset=utf-8";

    private final Object object;

    public JsonView(final Object object) {
        this.object = object;
    }

    @Override
    public String print() {
        return Json.encode(object);
    }
}
