package com.imuxuan.art.edit.usecase;

import android.text.TextUtils;

import com.imuxuan.art.base.UseCase;
import com.imuxuan.art.data.DiariesRepository;
import com.imuxuan.art.model.Diary;
import com.imuxuan.art.source.DataCallback;

/**
 * Created by Yunpeng Li on 2019/1/25.
 */
public class GetDiaryUseCase extends UseCase<GetDiaryUseCase.RequestValues, Diary> {

    @Override
    protected void executeUseCase(GetDiaryUseCase.RequestValues requestValues) {
        if (isAddDiary()) { // 日记id为空则返回，添加日记，不作查询处理
            return;
        }
        getRequestValues().diariesRepository.get(getRequestValues().diaryId, new DataCallback<Diary>() { // 获取日记信息
            @Override
            public void onSuccess(Diary diary) { // 获取成功
                getUseCaseCallback().onSuccess(diary);
            }

            @Override
            public void onError() { // 获取失败
                getUseCaseCallback().onError();
            }
        });
    }

    private boolean isAddDiary() { // 是否是添加日记
        return TextUtils.isEmpty(getRequestValues().diaryId); // id为空则为添加日记
    }

    public static class RequestValues implements UseCase.RequestValues {

        private String diaryId;
        private DiariesRepository diariesRepository;

        public RequestValues(String diaryId, DiariesRepository diariesRepository) {
            this.diaryId = diaryId;
            this.diariesRepository = diariesRepository;
        }
    }
}
