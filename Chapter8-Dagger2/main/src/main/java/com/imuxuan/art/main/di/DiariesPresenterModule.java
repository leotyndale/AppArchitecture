package com.imuxuan.art.main.di;

import com.imuxuan.art.main.DiariesContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Yunpeng Li on 2018/12/30.
 */
@Module
public class DiariesPresenterModule {

    private final DiariesContract.View mView;

    public DiariesPresenterModule(DiariesContract.View view) {
        mView = view;
    }

    @Provides
    DiariesContract.View provideDiariesContractView() {
        return mView;
    }
}
