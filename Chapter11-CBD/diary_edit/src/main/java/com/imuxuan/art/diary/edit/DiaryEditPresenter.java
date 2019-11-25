package com.imuxuan.art.diary.edit;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.imuxuan.art.common.data.DiariesRepository;
import com.imuxuan.art.common.model.Diary;
import com.imuxuan.art.common.source.DataCallback;
import com.imuxuan.art.common.source.DataSource;


public class DiaryEditPresenter implements DiaryEditContract.Presenter { // 日记修改Presenter

    private final DataSource<Diary> mDiariesRepository; // 数据源
    private final DiaryEditContract.View mView; // 视图
    private String mDiaryId; // 日记id

    public DiaryEditPresenter(@Nullable String diaryId, @NonNull DiaryEditContract.View addDiaryView) {
        mDiaryId = diaryId; // 传入日记id
        mDiariesRepository = DiariesRepository.getInstance(); // 获取数据仓库的实例
        mView = addDiaryView; // 传入视图
    }

    @Override
    public void start() {
        requestDiary(); // 获取日记信息
    }

    @Override
    public void destroy() {

    }

    @Override
    public void saveDiary(String title, String description) {
        if (isAddDiary()) { // 是否是添加日记
            createDiary(title, description); // 创建日记
        } else {
            updateDiary(title, description); // 更新日记
        }
    }

    private void createDiary(String title, String description) { // 创建日记
        Diary newDiary = new Diary(title, description); // 创建日记对象
        mDiariesRepository.update(newDiary); // 通过数据仓库更新数据
        mView.showDiariesList(); // 显示日记列表
    }

    private void updateDiary(String title, String description) { // 更改日记
        Diary diary = new Diary(title, description, mDiaryId); // 创建指定id的日记对象
        mDiariesRepository.update(diary); // 通过数据仓库更新数据
        mView.showDiariesList(); // 显示日记列表
    }

    @Override
    public void requestDiary() {
        if (isAddDiary()) { // 日记id为空则返回，添加日记，不作查询处理
            return;
        }
        mDiariesRepository.get(mDiaryId, new DataCallback<Diary>() { // 获取日记信息
            @Override
            public void onSuccess(Diary diary) { // 获取成功
                if (!mView.isActive()) { // 视图未被添加则返回
                    return;
                }
                mView.setTitle(diary.getTitle()); // 设置视图标题
                mView.setDescription(diary.getDescription()); // 设置视图详情
            }

            @Override
            public void onError() { // 获取失败
                if (!mView.isActive()) { // 视图未被添加则返回
                    return;
                }
                mView.showError(); // 弹出错误提示
            }
        });
    }

    private boolean isAddDiary() { // 是否是添加日记
        return TextUtils.isEmpty(mDiaryId); // id为空则为添加日记
    }

}
