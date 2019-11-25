package com.imuxuan.art.common;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class EnApplication extends Application{

    private static EnApplication INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        ARouter.init(this);
    }

    public static EnApplication get() {
        return INSTANCE;
    }
}
