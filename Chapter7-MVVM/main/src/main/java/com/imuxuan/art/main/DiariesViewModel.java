package com.imuxuan.art.main;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.view.View;

import com.imuxuan.art.EnApplication;
import com.imuxuan.art.R;
import com.imuxuan.art.data.DiariesRepository;
import com.imuxuan.art.main.list.DiariesAdapter;
import com.imuxuan.art.model.Diary;
import com.imuxuan.art.source.DataCallback;

import java.util.List;

public class DiariesViewModel extends BaseObservable {

    public final ObservableList<Diary> data = new ObservableArrayList<>();
    public final ObservableField<String> toastInfo = new ObservableField<>();
    public final ObservableField<DiariesAdapter> listAdapter = new ObservableField<>();

    private final DiariesRepository mDiariesRepository; // 数据仓库
    //    private final DiariesFragment mView; // 日记列表视图
    private Context mContext;

    public DiariesViewModel(/*@NonNull DiariesFragment diariesFragment*/Context context) { // 控制日记显示的Controller
        mContext = context;
        mDiariesRepository = DiariesRepository.getInstance(); // 获取数据仓库的实例
//        mView = diariesFragment; // 将传入的界面复制给日记的成员变量保存
    }

    public void start() {
        initAdapter(); // 初始化适配器
        loadDiaries(); // 加载日记数据
    }

//    @Override
//    public void destroy() {
//
//    }

    public void loadDiaries() { // 加载日记数据
        mDiariesRepository.getAll(new DataCallback<List<Diary>>() { // 通过数据仓库获取数据
            @Override
            public void onSuccess(List<Diary> diaryList) {
//                if (!mView.isActive()) { // 视图未被添加则返回
//                    return;
//                }
                updateDiaries(diaryList); // 数据获取成功，处理数据
            }

            @Override
            public void onError() {
//                if (!mView.isActive()) { // 视图未被添加则返回
//                    return;
//                }
//                mView.showError();  // 数据获取失败，弹出错误提示
                toastInfo.set(EnApplication.get().getString(R.string.error));
            }
        });
    }

    public void addDiary() {
        toastInfo.set(EnApplication.get().getString(R.string.developing));
    }

    public void updateDiary(@NonNull Diary diary) {
        toastInfo.set(EnApplication.get().getString(R.string.developing));
    }

    private void initAdapter() { // 初始化适配器
        DiariesAdapter diariesAdapter = new DiariesAdapter();
        // 设置列表的条目长按事件
        diariesAdapter.setOnLongClickListener(new DiariesAdapter.OnLongClickListener<Diary>() {
            @Override
            public boolean onLongClick(View v, Diary data) {
                updateDiary(data); // 更新日记
                return false;
            }
        });
//        mView.setListAdapter(diariesAdapter);
        listAdapter.set(diariesAdapter);
    }

    private void updateDiaries(List<Diary> diaries) {
//        mListAdapter.update(diaries); // 更新列表中的日记数据
        data.clear();
        data.addAll(diaries);
    }
}
