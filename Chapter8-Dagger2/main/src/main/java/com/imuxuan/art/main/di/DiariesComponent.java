package com.imuxuan.art.main.di;

import com.imuxuan.art.data.di.DiariesRepositoryComponent;
import com.imuxuan.art.main.MainActivity;
import com.imuxuan.art.util.FragmentScoped;

import dagger.Component;

/**
 * Created by Yunpeng Li on 2018/12/30.
 */
@FragmentScoped
@Component(dependencies = DiariesRepositoryComponent.class, modules = DiariesPresenterModule.class)
public interface DiariesComponent {

    void inject(MainActivity activity);

}
