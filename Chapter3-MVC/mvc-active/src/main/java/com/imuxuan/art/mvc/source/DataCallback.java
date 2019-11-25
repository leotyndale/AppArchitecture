package com.imuxuan.art.mvc.source;

/**
 * Created by Yunpeng Li on 2018/12/8.
 */
public interface DataCallback<T> {

    void onSuccess(T data);

    void onError();

}
