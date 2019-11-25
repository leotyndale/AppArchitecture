package com.imuxuan.art.util;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by Yunpeng Li on 2019/1/25.
 */
public class ThreadUtils { // 线程操作工具类

    public static void runOnUI(Runnable runnable) { // 在主线程中执行Runnable
        if (runnable == null) { // runnable无效，返回
            return;
        }
        if (Looper.myLooper() != Looper.getMainLooper()) { // 判断是否是主线程
            new Handler(Looper.getMainLooper()).post(runnable);
        } else {
            runnable.run();
        }
    }
}