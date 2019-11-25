package com.imuxuan.art.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.imuxuan.art.R;
import com.imuxuan.art.util.ActivityUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Activity的onCreate生命周期
        super.onCreate(savedInstanceState); // 调用超类方法
        setContentView(R.layout.activity_diaries); // 设置布局文件
        initToolbar(); // 初始化顶栏
        initFragment(); // 初始化Fragment
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar); // 从布局文件中加载顶部导航Toolbar
        setSupportActionBar(toolbar); // 自定义顶部导航Toolbar为ActionBar
    }

    private void initFragment() {
        DiariesFragment diariesFragment = getDiariesFragment(); // 初始化Fragment
        if (diariesFragment == null) { // 查找是否已经创建过日记Fragment
            diariesFragment = new DiariesFragment(); // 创建日记Fragment
            // 将日记Fragment添加到Activity显示
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), diariesFragment, R.id.content);
        }

        diariesFragment.setPresenter(new DiariesPresenter(diariesFragment)); // 设置主持人
    }

    private DiariesFragment getDiariesFragment() {
        // 通过FragmentManager查找日记展示Fragment
        return (DiariesFragment) getSupportFragmentManager().findFragmentById(R.id.content);
    }

}
