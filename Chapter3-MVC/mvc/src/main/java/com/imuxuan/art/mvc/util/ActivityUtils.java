package com.imuxuan.art.mvc.util;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


public class ActivityUtils {

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction(); // Fragment事务开始
        transaction.add(frameId, fragment); // 添加Fragment，frameId为fragment的id
        transaction.commit(); // 提交事务
    }

}
