package com.imuxuan.art.mvc.main;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.imuxuan.art.mvc.main.holder.DiaryHolder;
import com.imuxuan.art.mvc.model.Diary;

import java.util.List;

/**
 * Created by Yunpeng Li on 2018/12/3.
 */
public class DiariesAdapter extends RecyclerView.Adapter<DiaryHolder> {

    private List<Diary> mDiaries;

    public DiariesAdapter(List<Diary> diaries) {
        mDiaries = diaries;
    }

    public void update(List<Diary> diaries) {
        mDiaries = diaries;
        notifyDataSetChanged();
    }

    @Override
    public DiaryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DiaryHolder(parent);
    }

    @Override
    public void onBindViewHolder(DiaryHolder holder, int position) {
        holder.onBindView(mDiaries.get(position));
    }

    @Override
    public int getItemCount() {
        return mDiaries.size();
    }

}
