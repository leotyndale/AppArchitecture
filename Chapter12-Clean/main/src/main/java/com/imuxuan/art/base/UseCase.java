package com.imuxuan.art.base;

import android.support.annotation.CallSuper;

/**
 * Created by Yunpeng Li on 2019/1/25.
 */
public abstract class UseCase<Q, P> {

    private Q mRequestValues;

    private UseCaseCallback<P> mUseCaseCallback;

    /**
     * 若请求数据包含多个，需要封装传入
     * {@link RequestValues}
     */
    @CallSuper
    public UseCase<Q, P> setRequestValues(Q requestValues) {
        mRequestValues = requestValues;
        return this;
    }

    public Q getRequestValues() {
        return mRequestValues;
    }

    protected UseCaseCallback<P> getUseCaseCallback() {
        return mUseCaseCallback;
    }

    /**
     * 数据处理结束后的回调
     */
    public UseCase<Q, P> setUseCaseCallback(UseCaseCallback<P> useCaseCallback) {
        mUseCaseCallback = new UICallBackWrapper<>(useCaseCallback);
        return this;
    }

    /**
     * 任意线程，callback返回主线程
     */
    public void run() {
        executeUseCase(mRequestValues);
    }

    /**
     * 处理数据
     */
    protected abstract void executeUseCase(Q requestValues);

    /**
     * 若请求数据包含多个，需要封装传入
     */
    public interface RequestValues {
    }

    /**
     * 若返回数据包含多个，需要封装传入
     */
    public interface ResponseValues {
    }

    /**
     * 更新UI
     */
    public interface UseCaseCallback<P> {

        /**
         * 若返回数据包含多个，需要封装传入
         * {@link ResponseValues}
         */
        void onSuccess(P response);

        void onError();
    }
}
