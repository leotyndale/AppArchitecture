package com.imuxuan.art.main;

import android.arch.lifecycle.ViewModel;
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

//public class DiariesViewModel extends BaseObservable {
public class DiariesViewModel extends ViewModel {

    public final ObservableList<Diary> data = new ObservableArrayList<>();
    public final ObservableField<DiariesAdapter> listAdapter = new ObservableField<>();

//    public final ObservableField<String> toastInfo = new ObservableField<>();
    private final ToastInfo mToastInfo = new ToastInfo();
    private final DiariesRepository mDiariesRepository; // 数据仓库

    public DiariesViewModel() { // 控制日记显示的Controller
        mDiariesRepository = DiariesRepository.getInstance(); // 获取数据仓库的实例
    }

    public void start() {
        initAdapter(); // 初始化适配器
        loadDiaries(); // 加载日记数据
    }

    public ToastInfo getToastInfo() {
        return mToastInfo;
    }

    public void loadDiaries() { // 加载日记数据
        mDiariesRepository.getAll(new DataCallback<List<Diary>>() { // 通过数据仓库获取数据
            @Override
            public void onSuccess(List<Diary> diaryList) {
                updateDiaries(diaryList); // 数据获取成功，处理数据
            }

            @Override
            public void onError() {
//                toastInfo.set(EnApplication.get().getString(R.string.error));
                mToastInfo.setValue(EnApplication.get().getString(R.string.error));
            }
        });
    }

    public void addDiary() {
//        toastInfo.set(EnApplication.get().getString(R.string.developing));
        mToastInfo.setValue(EnApplication.get().getString(R.string.developing));
    }

    public void updateDiary(@NonNull Diary diary) {
//        toastInfo.set(EnApplication.get().getString(R.string.developing));
        mToastInfo.setValue(EnApplication.get().getString(R.string.developing));
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
        listAdapter.set(diariesAdapter);
    }

    private void updateDiaries(List<Diary> diaries) {
        data.clear();
        data.addAll(diaries);
    }
}
