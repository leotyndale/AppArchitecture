package com.imuxuan.art.mvc.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.imuxuan.art.mvc.R;

public class DiariesFragment extends Fragment { // 日记展示页面

    private DiariesController mController; // 日记页面的控制器

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) { // Fragment的生命周期onCreate
        super.onCreate(savedInstanceState); // 调用父类的onCreate方法
        mController = new DiariesController(this); // 创建日记控制器，具体细节在后面会详述
    }

    // Fragment的生命周期onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 加载日记页面布局
        View root = inflater.inflate(R.layout.fragment_diaries, container, false);
        // 将日记列表控件传入控制器
        mController.setDiariesList((RecyclerView) root.findViewById(R.id.diaries_list));
        return root;
    }

    @Override
    public void onResume() { // Fragment的生命周期onResume
        super.onResume(); // 调用父类的onResume方法
        mController.loadDiaries(); // 加载日志数据
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) { // 创建菜单，重写父类中的方法
        inflater.inflate(R.menu.menu_write, menu); // 加载菜单的布局文件
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { // 菜单的选择监听，重写父类中的方法
        switch (item.getItemId()) { // 判断点击事件
            case R.id.menu_add: // 点击添加按钮
                mController.gotoWriteDiary(); // 通知控制器添加新的日记
                return true; // 返回true代表菜单点选择事件已经被处理
        }
        return false; // 返回false代表菜单点选择事件没有被处理
    }

}
