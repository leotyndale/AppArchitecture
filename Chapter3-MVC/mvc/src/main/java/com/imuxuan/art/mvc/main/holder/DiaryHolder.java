package com.imuxuan.art.mvc.main.holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.imuxuan.art.mvc.R;
import com.imuxuan.art.mvc.model.Diary;

/**
 * Created by Yunpeng Li on 2018/12/10.
 */
public class DiaryHolder extends RecyclerViewHolder<Diary> { // 日记Holder

    private View.OnLongClickListener mOnLongClickListener; // 长按监听事件

    public DiaryHolder(ViewGroup parent) {
        super(parent, R.layout.list_diary_item); // 传入日记布局XML
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.mOnLongClickListener = onLongClickListener; // 设置长按监听
    }

    @Override
    public void onBindView(Diary diary) { // 绑定日记数据
        super.onBindView(diary);
        TextView title = itemView.findViewById(R.id.title); // 加载标题控件
        title.setText(diary.getTitle()); // 设置标题控件文字为日记标题

        TextView desc = itemView.findViewById(R.id.desc); // 加载详情控件
        desc.setText(diary.getDescription()); // 设置详情控件文字为日记标题

        itemView.setOnLongClickListener(new View.OnLongClickListener() { // 设置长按监听
            @Override
            public boolean onLongClick(View v) {
                // 回调监听事件
                return mOnLongClickListener != null && mOnLongClickListener.onLongClick(v);
            }
        });
    }

}
