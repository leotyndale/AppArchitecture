package com.imuxuan.art.data.di;

import com.imuxuan.art.data.local.DiariesLocalDataSource;
import com.imuxuan.art.model.Diary;
import com.imuxuan.art.source.DataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Yunpeng Li on 2018/12/31.
 */
@Module
public class DiariesRepositoryModule {

    @Singleton // 单例注解
    @Provides // 标注提供依赖实例的方法
    DataSource<Diary> provideDiariesLocalDataSource() {
        return new DiariesLocalDataSource();
    }
}
