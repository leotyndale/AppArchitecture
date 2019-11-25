package com.imuxuan.art.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.imuxuan.art.R;

public class MainActivity extends AppCompatActivity {

    private DiariesPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Activity的onCreate生命周期
        super.onCreate(savedInstanceState); // 调用超类方法
        setContentView(R.layout.activity_diaries); // 设置布局文件
        initToolbar(); // 初始化顶栏
//        initFragment(); // 初始化Fragment
        initView();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar); // 从布局文件中加载顶部导航Toolbar
        setSupportActionBar(toolbar); // 自定义顶部导航Toolbar为ActionBar
    }

//    private void initFragment() {
//        DiariesFragment diariesFragment = getDiariesFragment(); // 初始化Fragment
//        if (diariesFragment == null) { // 查找是否已经创建过日记Fragment
//            diariesFragment = new DiariesFragment(); // 创建日记Fragment
//            // 将日记Fragment添加到Activity显示
//            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), diariesFragment, R.id.content);
//        }
//
//        diariesFragment.setPresenter(new DiariesPresenter(diariesFragment)); // 设置主持人
//    }

    private void initView() {
        DiariesContract.View mView = findViewById(R.id.content);
        mPresenter = new DiariesPresenter(mView);
        mView.setPresenter(mPresenter); // 设置主持人
    }

//    private DiariesFragment getDiariesFragment() {
//        // 通过FragmentManager查找日记展示Fragment
//        return (DiariesFragment) getSupportFragmentManager().findFragmentById(R.id.content);
//    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    protected void onDestroy() {
        mPresenter.destroy();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onResult(requestCode, resultCode);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // 创建菜单，重写父类中的方法
        getMenuInflater().inflate(R.menu.menu_write, menu); // 加载菜单的布局文件
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { // 菜单的选择监听，重写父类中的方法
        switch (item.getItemId()) { // 判断点击事件
            case R.id.menu_add: // 点击添加按钮
                mPresenter.addDiary(); // 通知控制器添加新的日记
                return true; // 返回true代表菜单点选择事件已经被处理
        }
        return false; // 返回false代表菜单点选择事件没有被处理
    }

}
