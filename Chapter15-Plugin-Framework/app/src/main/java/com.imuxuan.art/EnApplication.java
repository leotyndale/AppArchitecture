package com.imuxuan.art;

import android.app.Application;

import net.wequick.small.Small;

public class EnApplication extends Application {

    private static EnApplication INSTANCE;

    public EnApplication() {
        Small.preSetUp(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

    public static EnApplication get() {
        return INSTANCE;
    }
}
