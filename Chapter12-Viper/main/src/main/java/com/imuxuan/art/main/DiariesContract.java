package com.imuxuan.art.main;

import android.support.annotation.NonNull;

import com.imuxuan.art.base.BaseInteractor;
import com.imuxuan.art.base.BasePresenter;
import com.imuxuan.art.base.BaseRouter;
import com.imuxuan.art.base.BaseView;
import com.imuxuan.art.main.list.DiariesAdapter;
import com.imuxuan.art.main.usecase.GetAllDiariesUseCase;
import com.imuxuan.art.model.Diary;


public interface DiariesContract {

    interface View extends BaseView<Presenter> { // 日记列表视图

//        void gotoWriteDiary(); // 跳转添加日记

//        void gotoUpdateDiary(String diaryId); // 跳转更新日记

        void showSuccess(); // 弹出成功提示信息

        void showError(); // 弹出失败提示信息

        boolean isActive();  // 判断Fragment是否已经添加到了Activity中

        void setListAdapter(DiariesAdapter mListAdapter); // 设置适配器
    }

    interface Presenter extends BasePresenter { // 日记列表主持人

        void loadDiaries(); // 加载日记数据

        void addDiary(); // 跳转添加日记

        void updateDiary(@NonNull Diary diary); // 跳转更新日记

        void onResult(int requestCode, int resultCode); // 返回界面获取结果信息

    }

    interface Interactor extends BaseInteractor {

        GetAllDiariesUseCase getAll();

    }

    interface Router extends BaseRouter {

        void gotoWriteDiary();

        void gotoUpdateDiary(String diaryId);
    }
}
