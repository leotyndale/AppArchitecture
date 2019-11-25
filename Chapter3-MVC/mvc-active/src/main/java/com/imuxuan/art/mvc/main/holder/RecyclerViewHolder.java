package com.imuxuan.art.mvc.main.holder;

import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by Yunpeng Li on 2018/12/10.
 */
public class RecyclerViewHolder<T> extends RecyclerView.ViewHolder {

    private T mData;

    public RecyclerViewHolder(ViewGroup parent, @LayoutRes int resource) {
        super(LayoutInflater.from(parent.getContext())
                .inflate(resource, parent, false));
    }

    @CallSuper
    public void onBindView(T data) {
        mData = data;
    }

    public T getData() {
        return mData;
    }
}
