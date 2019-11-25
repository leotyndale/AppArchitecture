package com.imuxuan.art.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.util.SimpleArrayMap;

import com.imuxuan.art.EnApplication;

public final class SharedPreferencesUtils {

    // SharedPreferences工具类内存缓存，以SharedPreferences的name为键
    private static final SimpleArrayMap<String, SharedPreferencesUtils> mCaches = new SimpleArrayMap<>();
    private SharedPreferences mSharedPreferences;

    private SharedPreferencesUtils(final String spName, final int mode) {
        mSharedPreferences = EnApplication.get().getSharedPreferences(spName, mode); // 获得SharedPreferences对象
    }

    public static SharedPreferencesUtils getInstance(String spName) {
        SharedPreferencesUtils spUtils = mCaches.get(spName); // 从内存缓存中获得SharedPreferences工具类
        if (spUtils == null) {
            spUtils = new SharedPreferencesUtils(spName, Context.MODE_PRIVATE); // 创建SharedPreferences工具类
            mCaches.put(spName, spUtils); // 将SharedPreferences工具类存入内存缓存
        }
        return spUtils;
    }

    public void put(@NonNull final String key, final String value) {
        mSharedPreferences.edit().putString(key, value).apply(); // 将数据存入SharedPreferences
    }

    public String get(@NonNull final String key) {
        return mSharedPreferences.getString(key, ""); // 从SharedPreferences中获取数据
    }

    public void remove(@NonNull final String key) {
        mSharedPreferences.edit().remove(key).apply(); // 从SharedPreferences中删除数据
    }

}
