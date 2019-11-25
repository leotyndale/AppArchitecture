package com.imuxuan.art.main.usecase;

import com.imuxuan.art.base.UseCase;
import com.imuxuan.art.data.DiariesRepository;
import com.imuxuan.art.model.Diary;
import com.imuxuan.art.source.DataCallback;

import java.util.List;

/**
 * Created by Yunpeng Li on 2019/1/25.
 */
public class GetAllDiariesUseCase extends UseCase<DiariesRepository, List<Diary>> {

    @Override
    protected void executeUseCase(DiariesRepository requestValues) {
        requestValues.getAll(new DataCallback<List<Diary>>() { // 通过数据仓库获取数据
            @Override
            public void onSuccess(List<Diary> diaryList) {
                getUseCaseCallback().onSuccess(diaryList);
            }

            @Override
            public void onError() {
                getUseCaseCallback().onError();
            }
        });
    }
}
