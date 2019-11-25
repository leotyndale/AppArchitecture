package com.imuxuan.art.main;

import android.app.Activity;
import android.content.Intent;

import com.imuxuan.art.edit.DiaryEditActivity;
import com.imuxuan.art.edit.DiaryEditFragment;

public class DiariesRouter implements DiariesContract.Router {

    private Activity mActivity;

    public DiariesRouter(Activity activity) {
        mActivity = activity;
    }

    @Override
    public void gotoWriteDiary() { // 跳转添加日记
        Intent intent = new Intent(mActivity, DiaryEditActivity.class); // 构造跳转页面的intent
        mActivity.startActivity(intent); // 根据intent跳转
    }

    @Override
    public void gotoUpdateDiary(String diaryId) { // 跳转更新日记
        Intent intent = new Intent(mActivity, DiaryEditActivity.class); // 构造跳转页面的intent
        intent.putExtra(DiaryEditFragment.DIARY_ID, diaryId); // 设置跳转携带信息
        mActivity.startActivity(intent); // 根据intent跳转
    }
}
