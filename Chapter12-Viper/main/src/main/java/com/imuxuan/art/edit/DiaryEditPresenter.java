package com.imuxuan.art.edit;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.imuxuan.art.base.UseCase;
import com.imuxuan.art.data.DiariesRepository;
import com.imuxuan.art.edit.usecase.GetDiaryUseCase;
import com.imuxuan.art.edit.usecase.UpdateDiaryUseCase;
import com.imuxuan.art.model.Diary;


public class DiaryEditPresenter implements DiaryEditContract.Presenter { // 日记修改Presenter

    private final DiaryEditContract.View mView; // 视图
    private String mDiaryId; // 日记id

    private GetDiaryUseCase mGetDiaryUseCase = new GetDiaryUseCase();
    private UpdateDiaryUseCase mUpdateDiaryUseCase = new UpdateDiaryUseCase();

    public DiaryEditPresenter(@Nullable String diaryId, @NonNull DiaryEditContract.View addDiaryView) {
        mDiaryId = diaryId; // 传入日记id
        mView = addDiaryView; // 传入视图
    }

    @Override
    public void start() {
        requestDiary(); // 获取日记信息
    }

    @Override
    public void destroy() { }

    @Override
    public void saveDiary(String title, String description) {
        mUpdateDiaryUseCase.setRequestValues(
                new UpdateDiaryUseCase.RequestValues(
                        DiariesRepository.getInstance(),
                        mDiaryId,
                        title,
                        description
                )
        ).setUseCaseCallback(new UseCase.UseCaseCallback<Void>() {
            @Override
            public void onSuccess(Void response) {
                mView.showDiariesList(); // 显示日记列表
            }

            @Override
            public void onError() {

            }
        }).run();
    }

    @Override
    public void requestDiary() {
        mGetDiaryUseCase.setRequestValues(new GetDiaryUseCase.RequestValues(mDiaryId, DiariesRepository.getInstance()))
                .setUseCaseCallback(new UseCase.UseCaseCallback<Diary>() {
                    @Override
                    public void onSuccess(Diary diary) {
                        if (!mView.isActive()) { // 视图未被添加则返回
                            return;
                        }
                        mView.setTitle(diary.getTitle()); // 设置视图标题
                        mView.setDescription(diary.getDescription()); // 设置视图详情
                    }

                    @Override
                    public void onError() {
                        if (!mView.isActive()) { // 视图未被添加则返回
                            return;
                        }
                        mView.showError(); // 弹出错误提示
                    }
                }).run();

    }

}
