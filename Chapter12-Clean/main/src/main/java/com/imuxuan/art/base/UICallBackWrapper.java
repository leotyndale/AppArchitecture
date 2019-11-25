package com.imuxuan.art.base;

import com.imuxuan.art.util.ThreadUtils;

/**
 * Created by Yunpeng Li on 2019/1/25.
 */
public class UICallBackWrapper<P> implements UseCase.UseCaseCallback<P> {

    private final UseCase.UseCaseCallback<P> mCallback;

    UICallBackWrapper(UseCase.UseCaseCallback<P> callback) {
        mCallback = callback;
    }

    @Override
    public void onSuccess(final P response) {
        ThreadUtils.runOnUI(new Runnable() {
            @Override
            public void run() {
                mCallback.onSuccess(response);
            }
        });
    }

    @Override
    public void onError() {
        ThreadUtils.runOnUI(new Runnable() {
            @Override
            public void run() {
                mCallback.onError();
            }
        });
    }
}
