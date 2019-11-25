package com.imuxuan.art.base.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public final class GsonUtils {

    private static final Gson GSON = createGson();

    public static String toJson(final Object object) { // 将对象转换为JSON格式String
        return GSON.toJson(object);
    }

    public static <T> T fromJson(final String json, final Type type) { // 将JSON格式String转换为对象
        return GSON.fromJson(json, type);
    }

    private static Gson createGson() {  // 创建Gson实例
        final GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        return builder.create();
    }
}
