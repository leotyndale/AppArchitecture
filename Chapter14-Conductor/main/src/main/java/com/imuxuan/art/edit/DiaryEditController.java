package com.imuxuan.art.edit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.imuxuan.art.R;
import com.imuxuan.art.base.BaseController;

public class DiaryEditController extends BaseController implements DiaryEditContract.View { // 日记修改界面

    public static final String DIARY_ID = "diary_id"; // 日记ID
    private DiaryEditContract.Presenter mPresenter; // 日记修改Presenter
    private TextView mTitle; // 日记标题
    private TextView mDescription; // 日记详情

    private String mDiaryId;

    public DiaryEditController(String diaryId) {
        Bundle bundle = new Bundle();
        bundle.putString(DIARY_ID, diaryId);
        mDiaryId = diaryId;
    }

    public DiaryEditController(Bundle args) {
        mDiaryId = args.getString(DIARY_ID);
    }

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) { // Fragment生命周期onCreateView
        View root = inflater.inflate(R.layout.fragment_diary_edit, container, false); // 加载布局文件
        mTitle = root.findViewById(R.id.edit_title); // 加载标题控件
        mDescription = root.findViewById(R.id.edit_description); // 加载详情控件
        initToolbar(mDiaryId);

        setPresenter(new DiaryEditPresenter(
                mDiaryId, // 日记Id
                this // 修改日记的Fragment
        )); // 初始化Presenter);

        setHasOptionsMenu(true); // 设置有菜单
        setActive(true);
        return root;
    }

    private void initToolbar(String diaryId) {
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar); // 从布局文件中加载顶部导航Toolbar
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar); // 自定义顶部导航Toolbar为ActionBar
        setToolbarTitle(TextUtils.isEmpty(diaryId)); // 设置导航栏标题
    }

    private void setToolbarTitle(boolean isAdd) {
        if (isAdd) { // 是否是写日记操作
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.add); // 设置标题为写日记
        } else {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.edit); // 设置标题为修改日记
        }
    }

//    @Override
//    public void onResume() { // Fragment生命周期onResume
//        super.onResume();
//        mPresenter.start();  // Presenter生命周期开始
//    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
        mPresenter.start();  // Presenter生命周期开始
    }

    @Override
    public void onDestroy() { // Fragment生命周期onDestroy
        mPresenter.destroy(); // Presenter生命周期结束
        super.onDestroy();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_done, menu); // 加载菜单布局
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { // 菜单点击监听
        switch (item.getItemId()) { // 获取按钮id
            case R.id.menu_done: // 点击完成按钮
                mPresenter.saveDiary(mTitle.getText().toString(), mDescription.getText().toString());  // 保存日记信息
                return true;
        }
        return false;
    }

    @Override
    public void setPresenter(@NonNull DiaryEditContract.Presenter presenter) {
        mPresenter = presenter; // 设置Presenter
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(), getResources().getString(R.string.error), Toast.LENGTH_SHORT).show(); // 显示错误提示
    }

    @Override
    public void showDiariesList() { // 显示日记列表
//        getActivity().setResult(Activity.RESULT_OK); // 标记处理成功
//        getActivity().finish(); // 销毁当前界面

        getRouter().popController(this);
    }

    @Override
    public void setTitle(String title) {
        mTitle.setText(title); // 设置标题
    }

    @Override
    public void setDescription(String description) {
        mDescription.setText(description); // 设置详情
    }

//    @Override
//    public boolean isActive() {
//        return isAdded();  // 判断Fragment是否已经添加到了Activity中
//    }
}
