package com.imuxuan.art;

import android.support.v7.app.AppCompatActivity;

import net.wequick.small.Small;

/**
 * Created by Yunpeng Li on 2019/2/12.
 */
public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        Small.setUp(LaunchActivity.this, new Small.OnCompleteListener() {
            @Override
            public void onComplete() {
                Small.openUri("diary_list", LaunchActivity.this);  // 跳转到日记列表页面
                finish(); // 销毁当前页面
            }
        });
    }
}
