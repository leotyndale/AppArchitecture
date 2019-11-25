package com.imuxuan.art.mvc.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public final class GsonUtils {

    private static final Gson GSON = createGson();

    public static String toJson(final Object object) {
        return GSON.toJson(object);
    }

    public static <T> T fromJson(final String json, final Type type) {
        return GSON.fromJson(json, type);
    }

    private static Gson createGson() {
        final GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        return builder.create();
    }
}
