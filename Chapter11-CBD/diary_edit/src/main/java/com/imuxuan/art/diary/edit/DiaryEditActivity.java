package com.imuxuan.art.diary.edit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.imuxuan.art.base.util.ActivityUtils;

@Route(path = "/diary/edit")
public class DiaryEditActivity extends AppCompatActivity { // 日记修改界面

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Activity的onCreate生命周期
        super.onCreate(savedInstanceState); // 调用超类方法
        setContentView(R.layout.activity_diary_edit); // 设置布局文件

        String diaryId = getIntent().getStringExtra(DiaryEditFragment.DIARY_ID); // 获得日记的id
        initToolbar(diaryId); // 初始化顶栏
        initFragment(diaryId); // 初始化Fragment
    }

    private void initToolbar(String diaryId) {
        Toolbar toolbar = findViewById(R.id.toolbar); // 从布局文件中加载顶部导航Toolbar
        setSupportActionBar(toolbar); // 自定义顶部导航Toolbar为ActionBar
        setToolbarTitle(TextUtils.isEmpty(diaryId)); // 设置导航栏标题
    }

    private void setToolbarTitle(boolean isAdd) {
        if (isAdd) { // 是否是写日记操作
            getSupportActionBar().setTitle(R.string.add); // 设置标题为写日记
        } else {
            getSupportActionBar().setTitle(R.string.edit); // 设置标题为修改日记
        }
    }

    private void initFragment(String diaryId) {
        DiaryEditFragment addEditDiaryFragment = getDiaryEditFragment(); // 初始化Fragment
        if (addEditDiaryFragment == null) { // 查找是否已经创建过日记Fragment
            addEditDiaryFragment = initEditDiaryFragment(diaryId);  // 创建日记Fragment
        }

        DiaryEditPresenter diaryEditPresenter = new DiaryEditPresenter(
                diaryId, // 日记Id
                addEditDiaryFragment // 修改日记的Fragment
        ); // 初始化Presenter
        addEditDiaryFragment.setPresenter(diaryEditPresenter); // 将创建的Presenter传入Fragment
    }

    @NonNull
    private DiaryEditFragment initEditDiaryFragment(String diaryId) {
        DiaryEditFragment addEditDiaryFragment = new DiaryEditFragment(); // 创建修改日记Fragment

        if (getIntent().hasExtra(DiaryEditFragment.DIARY_ID)) {
            Bundle bundle = new Bundle();
            bundle.putString(DiaryEditFragment.DIARY_ID, diaryId); // 将日记唯一标识保存到日记Fragment的Arguments
            addEditDiaryFragment.setArguments(bundle);
        }

        // 将日记Fragment添加到Activity显示
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), addEditDiaryFragment, R.id.content);
        return addEditDiaryFragment;
    }

    private DiaryEditFragment getDiaryEditFragment() { // 通过FragmentManager查找日记展示Fragment
        return (DiaryEditFragment) getSupportFragmentManager().findFragmentById(R.id.content);
    }
}
