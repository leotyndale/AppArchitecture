package com.imuxuan.art.data.di;

import com.imuxuan.art.data.DiariesRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Yunpeng Li on 2018/12/30.
 */
@Singleton
@Component(modules = DiariesRepositoryModule.class)
public interface DiariesRepositoryComponent {

    DiariesRepository getDiariesRepository();
}
