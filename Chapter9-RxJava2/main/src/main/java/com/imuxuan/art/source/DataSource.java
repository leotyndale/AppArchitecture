package com.imuxuan.art.source;

import android.support.annotation.NonNull;

import com.imuxuan.art.model.Diary;

import java.util.List;

import io.reactivex.Flowable;


public interface DataSource<T> {

    // 获得所有数据T
//    void getAll(@NonNull DataCallback<List<T>> callback);
    Flowable<List<Diary>> getAll();

    // 获取某个数据T
//    void get(@NonNull String id, @NonNull DataCallback<T> callback);
    Flowable<Diary> get(@NonNull String id/*, @NonNull DataCallback<T> callback*/);

    // 更新某个数据T
    void update(@NonNull T diary);

    // 清空所有数据T
    void clear();

    // 删除某个数据T
    void delete(@NonNull String id);
}
