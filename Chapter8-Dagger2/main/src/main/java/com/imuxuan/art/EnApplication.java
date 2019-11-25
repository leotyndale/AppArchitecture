package com.imuxuan.art;

import android.app.Application;

import com.imuxuan.art.data.di.DaggerDiariesRepositoryComponent;
import com.imuxuan.art.data.di.DiariesRepositoryComponent;
import com.imuxuan.art.data.di.DiariesRepositoryModule;

public class EnApplication extends Application{

    private static EnApplication INSTANCE;
    private DiariesRepositoryComponent mDiariesRepositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;

        mDiariesRepositoryComponent = DaggerDiariesRepositoryComponent.builder()
                .diariesRepositoryModule(new DiariesRepositoryModule())
                .build();
    }

    public static EnApplication get() {
        return INSTANCE;
    }

    public DiariesRepositoryComponent getDiariesRepositoryComponent() {
        return mDiariesRepositoryComponent;
    }
}
