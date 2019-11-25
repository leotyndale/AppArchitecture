package com.imuxuan.art.mvc.main.holder;

import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by Yunpeng Li on 2018/12/10.
 */
public class RecyclerViewHolder<T> extends RecyclerView.ViewHolder { // ViewHolder基类

    private T mData; // ViewHolder需要的数据

    public RecyclerViewHolder(ViewGroup parent, @LayoutRes int resource) {
        // 构造方法，加载界面布局
        super(LayoutInflater.from(parent.getContext()).inflate(resource, parent, false));
    }

    @CallSuper // 提示调用父类方法
    public void onBindView(T data) {
        mData = data; // 绑定数据
    }

    public T getData() {
        return mData; // 获得数据
    }
}
