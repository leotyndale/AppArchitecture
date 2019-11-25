package com.imuxuan.art.base.util;

import android.app.Application;

public class EnContext {

    public static final Application mInstance;

    static {
        Application context = null;
        try {
            context = (Application) Class.forName("android.app.AppGlobals")
                    .getMethod("getInitialApplication").invoke(null);
        } catch (final Exception e) {
            e.printStackTrace();
            try {
                context = (Application) Class.forName("android.app.ActivityThread")
                        .getMethod("currentApplication").invoke(null);
            } catch (final Exception ex) {
                e.printStackTrace();
            }
        } finally {
            mInstance = context;
        }
    }

    public static Application get() {
        return mInstance;
    }
}
