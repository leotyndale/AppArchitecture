package com.imuxuan.art.diary.edit;

import com.imuxuan.art.base.base.BasePresenter;
import com.imuxuan.art.base.base.BaseView;

public interface DiaryEditContract { // 修改日记协议层

    interface View extends BaseView<Presenter> { // 修改日记的视图

        void showError(); // 弹出错误提示信息

        void showDiariesList(); // 显示日记列表

        void setTitle(String title); // 设置标题

        void setDescription(String description); // 设置详情

        boolean isActive(); // 判断Fragment是否已经添加到了Activity中

    }

    interface Presenter extends BasePresenter { // 修改日记的主持人

        void saveDiary(String title, String description); // 保存日记信息

        void requestDiary(); // 获取日记信息

    }
}
