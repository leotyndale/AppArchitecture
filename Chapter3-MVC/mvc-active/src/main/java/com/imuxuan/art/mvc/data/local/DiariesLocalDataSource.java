package com.imuxuan.art.mvc.data.local;

import android.support.annotation.NonNull;

import com.google.gson.reflect.TypeToken;
import com.imuxuan.art.mvc.data.mock.MockDiaries;
import com.imuxuan.art.mvc.model.Diary;
import com.imuxuan.art.mvc.source.DataCallback;
import com.imuxuan.art.mvc.source.DataSource;
import com.imuxuan.art.mvc.util.CollectionUtils;
import com.imuxuan.art.mvc.util.GsonUtils;
import com.imuxuan.art.mvc.util.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class DiariesLocalDataSource implements DataSource<Diary> {

    private static final String DIARY_DATA = "diary_data";
    private static final String ALL_DIARY = "all_diary";

    private static SharedPreferencesUtils mSpUtils;
    private static Map<String, Diary> LOCAL_DATA = new LinkedHashMap<>();
    private static volatile DiariesLocalDataSource mInstance;

    private DiariesLocalDataSource() {
        mSpUtils = SharedPreferencesUtils.getInstance(DIARY_DATA);
        String diaryStr = mSpUtils.getString(ALL_DIARY);
        LinkedHashMap<String, Diary> diariesObj = json2Obj(diaryStr);
        if (!CollectionUtils.isEmpty(diariesObj)) {
            LOCAL_DATA = diariesObj;
        } else {
            LOCAL_DATA = MockDiaries.mock();
        }
    }

    public static DiariesLocalDataSource get() {
        if (mInstance == null) {
            synchronized (DiariesLocalDataSource.class) {
                if (mInstance == null) {
                    mInstance = new DiariesLocalDataSource();
                }
            }
        }
        return mInstance;
    }

    @Override
    public void getAll(@NonNull final DataCallback<List<Diary>> callback) {
        if (LOCAL_DATA.isEmpty()) {
            callback.onError();
        } else {
            callback.onSuccess(new ArrayList<>(LOCAL_DATA.values()));
        }
    }

    @Override
    public void get(@NonNull final String id, @NonNull final DataCallback<Diary> callback) {
        Diary diary = LOCAL_DATA.get(id);
        if (diary != null) {
            callback.onSuccess(diary);
        } else {
            callback.onError();
        }
    }

    @Override
    public void update(@NonNull final Diary diary) {
        LOCAL_DATA.put(diary.getId(), diary);
        mSpUtils.put(ALL_DIARY, GsonUtils.toJson(LOCAL_DATA));
    }

    @Override
    public void clear() {
        LOCAL_DATA.clear();
        mSpUtils.remove(ALL_DIARY);
    }

    @Override
    public void delete(@NonNull final String id) {
        LOCAL_DATA.remove(id);
        mSpUtils.put(ALL_DIARY, GsonUtils.toJson(LOCAL_DATA));
    }

    private LinkedHashMap<String, Diary> json2Obj(String diaryStr) {
        return GsonUtils.fromJson(diaryStr, new TypeToken<LinkedHashMap<String, Diary>>() {
        }.getType());
    }
}
