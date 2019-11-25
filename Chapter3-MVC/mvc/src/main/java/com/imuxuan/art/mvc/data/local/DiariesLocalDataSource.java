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


public class DiariesLocalDataSource implements DataSource<Diary> { // 日记本地数据源

    private static final String DIARY_DATA = "diary_data"; // 存储日记数据的SharedPreferences名称
    private static final String ALL_DIARY = "all_diary"; // 存储日记数据的SharedPreferences键名

    private static SharedPreferencesUtils mSpUtils; // SharedPreferences工具类
    private static Map<String, Diary> LOCAL_DATA = new LinkedHashMap<>(); // 本地数据内存缓存
    private static volatile DiariesLocalDataSource mInstance; // 本地数据源

    private DiariesLocalDataSource() {
        mSpUtils = SharedPreferencesUtils.getInstance(DIARY_DATA); // 获取SharedPreferences管理本地缓存
        String diaryStr = mSpUtils.get(ALL_DIARY); // 获取本地日记信息
        LinkedHashMap<String, Diary> diariesObj = json2Obj(diaryStr); // 解析本地日记信息
        if (!CollectionUtils.isEmpty(diariesObj)) { //判断集合是否不为空
            LOCAL_DATA = diariesObj; // 不为空则将解析后的值赋予成员变量
        } else {
            LOCAL_DATA = MockDiaries.mock(); // 为空则构造测试数据
        }
    }

    // 获取日记本地数据源类的单例
    public static DiariesLocalDataSource get() {
        if (mInstance == null) { // 线程安全的单例模式
            synchronized (DiariesLocalDataSource.class) {
                if (mInstance == null) {
                    mInstance = new DiariesLocalDataSource();
                }
            }
        }
        return mInstance;
    }

    @Override
    public void getAll(@NonNull final DataCallback<List<Diary>> callback) { // 获得所有日记数据
        if (LOCAL_DATA.isEmpty()) { // 内存缓存是否为空
            callback.onError(); // 通知查询错误
        } else {
            callback.onSuccess(new ArrayList<>(LOCAL_DATA.values())); // 通知查询成功
        }
    }

    @Override
    public void get(@NonNull final String id, @NonNull final DataCallback<Diary> callback) { // 获取某个日记数据
        Diary diary = LOCAL_DATA.get(id); // 从内存数据中查找日记信息
        if (diary != null) {
            callback.onSuccess(diary); // 通知查找成功
        } else {
            callback.onError(); // 通知查找失败
        }
    }

    @Override
    public void update(@NonNull final Diary diary) {  // 更新某个日记数据
        LOCAL_DATA.put(diary.getId(), diary); // 更新内存中的日记数据
        mSpUtils.put(ALL_DIARY, obj2Json()); // 更新本地日记数据
    }

    @Override
    public void clear() { // 清空日记数据
        LOCAL_DATA.clear(); // 清空内存日记数据
        mSpUtils.remove(ALL_DIARY); // 清空本地日记数据
    }

    @Override
    public void delete(@NonNull final String id) { // 删除某个日记数据
        LOCAL_DATA.remove(id); // 删除内存某个日记数据
        mSpUtils.put(ALL_DIARY, obj2Json()); // 删除本地某个日记数据
    }

    private String obj2Json() {
        return GsonUtils.toJson(LOCAL_DATA);
    }

    private LinkedHashMap<String, Diary> json2Obj(String diaryStr) { // 将日记json数据转换为日记对象
        return GsonUtils.fromJson(diaryStr, new TypeToken<LinkedHashMap<String, Diary>>() {
        }.getType());
    }
}
