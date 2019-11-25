package com.imuxuan.art.mvc.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.imuxuan.art.mvc.main.holder.DiaryHolder;
import com.imuxuan.art.mvc.model.Diary;

import java.util.List;

/**
 * Created by Yunpeng Li on 2018/12/3.
 */
public class DiariesAdapter extends RecyclerView.Adapter<DiaryHolder> { // 日记列表适配器

    private List<Diary> mDiaries; // 日记数据
    private OnLongClickListener<Diary> mOnLongClickListener; // 长按监听

    public DiariesAdapter(List<Diary> diaries) {
        mDiaries = diaries; // 传入日记数据
    }

    public void update(List<Diary> diaries) { // 更新日记数据
        mDiaries = diaries;
        notifyDataSetChanged(); // 通知Adapter更新
    }

    public void setOnLongClickListener(OnLongClickListener<Diary> onLongClickListener) {
        this.mOnLongClickListener = onLongClickListener; // 设置长按监听
    }

    @Override
    public DiaryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DiaryHolder(parent); // 创建日记Holder
    }

    @Override
    public void onBindViewHolder(DiaryHolder holder, int position) {
        final Diary diary = mDiaries.get(position); // 根据位置获取日记数据
        holder.onBindView(diary); // holder绑定日记数据
        holder.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // 长按监听回调
                return mOnLongClickListener != null && mOnLongClickListener.onLongClick(v, diary);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDiaries.size(); // 获得列表条目总数
    }

    public interface OnLongClickListener<T> { // 长按监听事件

        boolean onLongClick(View v, T data);

    }

}
