package com.imuxuan.art.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.imuxuan.art.R;

public class MainActivity extends AppCompatActivity {

    private Router mRouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Activity的onCreate生命周期
        super.onCreate(savedInstanceState); // 调用超类方法
        setContentView(R.layout.activity_diaries); // 设置布局文件
        initToolbar(); // 初始化顶栏
//        initFragment(); // 初始化Fragment

        mRouter = Conductor.attachRouter(this, (ViewGroup) findViewById(R.id.content), savedInstanceState);
        if (!mRouter.hasRootController()) {
            DiariesController controller = new DiariesController();
            controller.setPresenter(new DiariesPresenter(controller));
            mRouter.setRoot(RouterTransaction.with(controller));
        }
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar); // 从布局文件中加载顶部导航Toolbar
        setSupportActionBar(toolbar); // 自定义顶部导航Toolbar为ActionBar
    }

    @Override
    public void onBackPressed() {
        if (!mRouter.handleBack()) {
            super.onBackPressed();
        }
    }

//    private void initFragment() {
//        DiariesController diariesFragment = getDiariesFragment(); // 初始化Fragment
//        if (diariesFragment == null) { // 查找是否已经创建过日记Fragment
//            diariesFragment = new DiariesController(); // 创建日记Fragment
//            // 将日记Fragment添加到Activity显示
//            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), diariesFragment, R.id.content);
//        }
//
//        diariesFragment.setPresenter(new DiariesPresenter(diariesFragment)); // 设置主持人
//    }
//
//    private DiariesController getDiariesFragment() {
//        // 通过FragmentManager查找日记展示Fragment
//        return (DiariesController) getSupportFragmentManager().findFragmentById(R.id.content);
//    }

}
