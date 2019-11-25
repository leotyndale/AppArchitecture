package com.imuxuan.art.main;

import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.imuxuan.art.R;
import com.imuxuan.art.databinding.FragmentDiariesBinding;
import com.imuxuan.art.main.list.DiariesAdapter;

public class DiariesFragment extends Fragment { // 日记展示页面

    private DiariesViewModel mViewModel;
//    private DiariesContract.Presenter mPresenter; // 日记页面的主持人
    private FragmentDiariesBinding mDiariesBinding;

    // Fragment的生命周期onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 加载日记页面布局
//        View root = inflater.inflate(R.layout.fragment_diaries, container, false);
        mDiariesBinding = FragmentDiariesBinding.inflate(inflater, container, false);
        mDiariesBinding.setViewModel(mViewModel);
        mDiariesBinding.setLayoutManager(new LinearLayoutManager(getContext()));
//        this.mRecyclerView = root.findViewById(R.id.diaries_list);
        // 将日记列表控件传入控制器
        initDiariesList();

        setHasOptionsMenu(true); // 设置界面有菜单功能
        return mDiariesBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initToast();
        initRecyclerView();
    }

    private void initToast() {
        mViewModel.toastInfo.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                showMessage(mViewModel.toastInfo.get());
            }
        });
    }

    private void initRecyclerView() {
        mViewModel.listAdapter.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                setListAdapter(mViewModel.listAdapter.get());
            }
        });
    }

    @Override
    public void onResume() { // Fragment的生命周期onResume
        super.onResume(); // 调用父类的onResume方法
//        mPresenter.start();
        mViewModel.start();
    }

    public void initDiariesList() { // 配置日记列表
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext())); // 设置日记列表为线性布局显示
        mDiariesBinding.diariesList.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL) // 为列表条目添加分割线
        );
        mDiariesBinding.diariesList.setItemAnimator(new DefaultItemAnimator()); // 设置列表默认动画
    }

    public void setViewModel(DiariesViewModel viewModel) {
        mViewModel = viewModel;
    }

//    @Override
//    public void setPresenter(@NonNull DiariesViewModel presenter) {
//        mPresenter = presenter; // 设置主持人
//    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) { // 创建菜单，重写父类中的方法
        inflater.inflate(R.menu.menu_write, menu); // 加载菜单的布局文件
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { // 菜单的选择监听，重写父类中的方法
        switch (item.getItemId()) { // 判断点击事件
            case R.id.menu_add: // 点击添加按钮
//                mPresenter.addDiary(); // 通知控制器添加新的日记
                mViewModel.addDiary();
                return true; // 返回true代表菜单点选择事件已经被处理
        }
        return false; // 返回false代表菜单点选择事件没有被处理
    }

//    public void gotoWriteDiary() { // 跳转添加日记
//        Intent intent = new Intent(getContext(), DiaryEditActivity.class); // 构造跳转页面的intent
//        startActivity(intent); // 根据intent跳转
//    }
//
//    public void gotoUpdateDiary(String diaryId) { // 跳转更新日记
//        Intent intent = new Intent(getContext(), DiaryEditActivity.class); // 构造跳转页面的intent
//        intent.putExtra(DiaryEditFragment.DIARY_ID, diaryId); // 设置跳转携带信息
//        getContext().startActivity(intent); // 根据intent跳转
//    }

//    public void showSuccess() {
//        showMessage(getString(R.string.success)); // 弹出成功提示信息
//    }

//    public void showError() {
//        showMessage(getString(R.string.error)); // 弹出失败提示信息
//    }

    private void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show(); // 弹出文字提示信息
    }

//    public boolean isActive() {
//        return isAdded(); // 判断Fragment是否已经添加到了Activity中
//    }

    public void setListAdapter(DiariesAdapter diariesAdapter) {
        mDiariesBinding.diariesList.setAdapter(diariesAdapter);
    }

}
