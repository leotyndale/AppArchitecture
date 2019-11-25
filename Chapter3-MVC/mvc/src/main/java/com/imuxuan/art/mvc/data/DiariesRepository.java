package com.imuxuan.art.mvc.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.imuxuan.art.mvc.data.local.DiariesLocalDataSource;
import com.imuxuan.art.mvc.model.Diary;
import com.imuxuan.art.mvc.source.DataCallback;
import com.imuxuan.art.mvc.source.DataSource;
import com.imuxuan.art.mvc.util.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class DiariesRepository implements DataSource<Diary> { // 数据仓库

    private static volatile DiariesRepository mInstance; // 数据仓库实例
    private final DataSource<Diary> mLocalDataSource; // 本地数据源

    private Map<String, Diary> mMemoryCache; // 内存缓存

    private DiariesRepository() {
        mMemoryCache = new LinkedHashMap<>();
        mLocalDataSource = DiariesLocalDataSource.get(); // 获取本地数据源单例
    }

    public static DiariesRepository getInstance() { // 获取数据仓库单例
        if (mInstance == null) {
            synchronized (DiariesRepository.class) {
                if (mInstance == null) {
                    mInstance = new DiariesRepository();
                }
            }
        }
        return mInstance;
    }

    @Override
    public void getAll(@NonNull final DataCallback<List<Diary>> callback) { // 获取所有日记数据
        if (!CollectionUtils.isEmpty(mMemoryCache)) {
            callback.onSuccess(new ArrayList<>(mMemoryCache.values())); // 内存数据获取成功
            return;
        }

        mLocalDataSource.getAll(new DataCallback<List<Diary>>() { // 本地数据获取成功
            @Override
            public void onSuccess(List<Diary> diaries) {
                updateMemoryCache(diaries); // 更新内存缓存数据
                callback.onSuccess(new ArrayList<>(mMemoryCache.values())); // 回调通知
            }

            @Override
            public void onError() {
                callback.onError(); // 数据获取失败
            }
        });
    }

    @Override
    public void get(@NonNull final String id, @NonNull final DataCallback<Diary> callback) { // 获得某个日记数据
        Diary cachedDiary = getDiaryByIdFromMemory(id); // 从内存缓存获取数据

        if (cachedDiary != null) {
            callback.onSuccess(cachedDiary); // 内存缓存获取成功
            return;
        }

        mLocalDataSource.get(id, new DataCallback<Diary>() {  // 本地数据获取成功
            @Override
            public void onSuccess(Diary diary) {
                mMemoryCache.put(diary.getId(), diary); // 更新内存缓存数据
                callback.onSuccess(diary); // 回调通知
            }

            @Override
            public void onError() {
                callback.onError(); // 数据获取失败
            }
        });
    }

    @Override
    public void update(@NonNull Diary diary) { // 更新日记数据
        mLocalDataSource.update(diary); // 更新本地数据
        mMemoryCache.put(diary.getId(), diary); // 更新内存缓存数据
    }

    @Override
    public void clear() { // 清空日记数据
        mLocalDataSource.clear(); // 清空本地数据
        mMemoryCache.clear(); // 清空内存缓存数据
    }

    @Override
    public void delete(@NonNull String id) { // 删除日记数据
        mLocalDataSource.delete(id); // 删除本地数据
        mMemoryCache.remove(id); // 删除内存缓存数据
    }

    private void updateMemoryCache(List<Diary> diaryList) { // 更新内存缓存
        mMemoryCache.clear(); // 清空内存缓存
        for (Diary diary : diaryList) {
            mMemoryCache.put(diary.getId(), diary); // 将数据添加到内存缓存
        }
    }

    @Nullable
    private Diary getDiaryByIdFromMemory(@NonNull String id) { // 获得某个日记数据
        if (CollectionUtils.isEmpty(mMemoryCache)) {
            return null;
        } else {
            return mMemoryCache.get(id); // 从内存缓存获得数据
        }
    }

}
