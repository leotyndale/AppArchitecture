package com.imuxuan.art.main;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

/**
 * Created by Yunpeng Li on 2019/1/13.
 */
public class ToastInfo extends MutableLiveData<String> {

    public void observe(LifecycleOwner owner, final ToastObserver observer) {
        super.observe(owner, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String t) {
                if (t == null) {
                    return;
                }
                observer.onNewMessage(t); // 收到变化，通知回调
            }
        });
    }

    public interface ToastObserver {

        void onNewMessage(String toastInfo); // 监听变化的回调
    }
}
