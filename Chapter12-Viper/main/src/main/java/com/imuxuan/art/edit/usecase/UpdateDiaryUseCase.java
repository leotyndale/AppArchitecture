package com.imuxuan.art.edit.usecase;

import android.text.TextUtils;

import com.imuxuan.art.base.UseCase;
import com.imuxuan.art.data.DiariesRepository;
import com.imuxuan.art.model.Diary;

/**
 * Created by Yunpeng Li on 2019/1/25.
 */
public class UpdateDiaryUseCase extends UseCase<UpdateDiaryUseCase.RequestValues, Void> {

    @Override
    protected void executeUseCase(UpdateDiaryUseCase.RequestValues requestValues) {
        if (isAddDiary()) { // 是否是添加日记
            createDiary(getRequestValues().title, getRequestValues().description); // 创建日记
        } else {
            updateDiary(getRequestValues().title, getRequestValues().description); // 更新日记
        }
    }

    private boolean isAddDiary() { // 是否是添加日记
        return TextUtils.isEmpty(getRequestValues().diaryId); // id为空则为添加日记
    }

    private void createDiary(String title, String description) { // 创建日记
        Diary newDiary = new Diary(title, description); // 创建日记对象
        getRequestValues().diariesRepository.update(newDiary); // 通过数据仓库更新数据
        getUseCaseCallback().onSuccess(null);
    }

    private void updateDiary(String title, String description) { // 更改日记
        Diary diary = new Diary(title, description, getRequestValues().diaryId); // 创建指定id的日记对象
        getRequestValues().diariesRepository.update(diary); // 通过数据仓库更新数据
        getUseCaseCallback().onSuccess(null);
    }

    public static class RequestValues implements UseCase.RequestValues {

        private DiariesRepository diariesRepository;
        private String diaryId;
        private String title;
        private String description;

        public RequestValues(DiariesRepository diariesRepository, String diaryId, String title, String description) {
            this.diariesRepository = diariesRepository;
            this.diaryId = diaryId;
            this.title = title;
            this.description = description;
        }
    }
}
