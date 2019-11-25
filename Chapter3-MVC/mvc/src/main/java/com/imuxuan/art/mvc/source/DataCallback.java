package com.imuxuan.art.mvc.source;

/**
 * Created by Yunpeng Li on 2018/12/8.
 */
public interface DataCallback<T> { // 数据操作回调

    void onSuccess(T data); // 通知成功

    void onError(); // 通知失败

}
