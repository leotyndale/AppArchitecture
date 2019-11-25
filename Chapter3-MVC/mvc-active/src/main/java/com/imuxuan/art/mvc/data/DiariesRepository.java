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


public class DiariesRepository implements DataSource<Diary> {

    private static volatile DiariesRepository mInstance;
    private final DataSource<Diary> mLocalDataSource;

    private Map<String, Diary> mMemoryCache;

    private DiariesRepository() {
        mLocalDataSource = DiariesLocalDataSource.get();
    }

    public static DiariesRepository getInstance() {
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
    public void getAll(@NonNull final DataCallback<List<Diary>> callback) {
        if (mMemoryCache != null) {
            callback.onSuccess(new ArrayList<>(mMemoryCache.values()));
            return;
        }

        mLocalDataSource.getAll(new DataCallback<List<Diary>>() {
            @Override
            public void onSuccess(List<Diary> diaries) {
                updateMemoryCache(diaries);
                callback.onSuccess(new ArrayList<>(mMemoryCache.values()));
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }

    @Override
    public void update(@NonNull Diary diary) {
        mLocalDataSource.update(diary);
        getEnsureMemoryCache().put(diary.getId(), diary);
    }

    @Override
    public void get(@NonNull final String id, @NonNull final DataCallback<Diary> callback) {
        Diary cachedDiary = getDiaryById(id);

        if (cachedDiary != null) {
            callback.onSuccess(cachedDiary);
            return;
        }

        mLocalDataSource.get(id, new DataCallback<Diary>() {
            @Override
            public void onSuccess(Diary diary) {
                getEnsureMemoryCache().put(diary.getId(), diary);
                callback.onSuccess(diary);
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }

    @Override
    public void clear() {
        mLocalDataSource.clear();
        getEnsureMemoryCache().clear();
    }

    @Override
    public void delete(@NonNull String id) {
        mLocalDataSource.delete(id);
        mMemoryCache.remove(id);
    }

    private void updateMemoryCache(List<Diary> diaryList) {
        getEnsureMemoryCache().clear();
        for (Diary diary : diaryList) {
            mMemoryCache.put(diary.getId(), diary);
        }
    }

    @Nullable
    private Diary getDiaryById(@NonNull String id) {
        if (CollectionUtils.isEmpty(mMemoryCache)) {
            return null;
        } else {
            return mMemoryCache.get(id);
        }
    }

    private Map<String, Diary> getEnsureMemoryCache() {
        if (mMemoryCache == null) {
            mMemoryCache = new LinkedHashMap<>();
        }
        return mMemoryCache;
    }
}
