package com.yunikov.vertx.api.commons;

import com.yunikov.vertx.domain.commons.Media;
import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class JsonMedia implements Media {

    public static final String CONTENT_TYPE_UTF_8 = "application/json; charset=utf-8";

    private final Map<String, Object> jsonMap;

    public JsonMedia() {
        this(new HashMap<>());
    }

    public JsonMedia(final Map<String, Object> jsonMap) {
        this.jsonMap = jsonMap;
    }

    @Override
    public Media with(final String key, final Object value) {
        jsonMap.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        return new JsonObject(jsonMap).encode();
    }
}
