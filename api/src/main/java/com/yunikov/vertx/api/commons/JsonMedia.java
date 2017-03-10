package com.yunikov.vertx.api.commons;

import com.yunikov.vertx.domain.commons.Media;
import com.yunikov.vertx.domain.commons.MediaPrintable;
import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class JsonMedia implements Media {

    public static final String CONTENT_TYPE_UTF_8 = "application/json; charset=utf-8";

    private final Map<String, Object> jsonMap;

    public JsonMedia() {
        this(new HashMap<>());
    }

    private JsonMedia(final Map<String, Object> jsonMap) {
        this.jsonMap = jsonMap;
    }

    @Override
    public Media with(final String key, final Object value) {
        if (value instanceof MediaPrintable) {
            final JsonMedia media = new JsonMedia();
            ((MediaPrintable) value).print(media);
            jsonMap.put(key, new JsonObject(media.jsonMap));
        } else {
            jsonMap.put(key, value);
        }

        return this;
    }

    @Override
    public String toString() {
        return json().encode();
    }

    private JsonObject json() {
        return new JsonObject(jsonMap);
    }
}
