package com.imuxuan.art.mvc.source;

import android.support.annotation.NonNull;

import java.util.List;


public interface DataSource<T> {

    void getAll(@NonNull DataCallback<List<T>> callback);

    void get(@NonNull String id, @NonNull DataCallback<T> callback);

    void update(@NonNull T diary);

    void clear();

    void delete(@NonNull String id);
}
