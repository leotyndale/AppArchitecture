package com.imuxuan.art;

import android.app.Application;

public class EnApplication extends Application{

    private static EnApplication INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

    public static EnApplication get() {
        return INSTANCE;
    }
}
