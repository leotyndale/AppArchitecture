package com.imuxuan.art.mvc.main.holder;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.imuxuan.art.mvc.EnApplication;
import com.imuxuan.art.mvc.R;
import com.imuxuan.art.mvc.model.Diary;

/**
 * Created by Yunpeng Li on 2018/12/10.
 */
public class DiaryHolder extends RecyclerViewHolder<Diary> {

    public DiaryHolder(ViewGroup parent) {
        super(parent, R.layout.list_diary_item);
    }

    @Override
    public void onBindView(Diary diary) {
        super.onBindView(diary);
        TextView title = itemView.findViewById(R.id.title);
        title.setText(diary.getTitle());

        TextView desc = itemView.findViewById(R.id.desc);
        desc.setText(diary.getDescription());

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showInputDialog();
                return false;
            }
        });
    }

    private void showInputDialog() {
        final EditText editText = new EditText(itemView.getContext());
        editText.setText(getData().getDescription());

        new AlertDialog.Builder(itemView.getContext()).setTitle(getData().getTitle())
                .setView(editText)
                .setPositiveButton(EnApplication.get().getString(R.string.ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getData().setDescription(editText.getText().toString());
                            }
                        })
                .setNegativeButton(EnApplication.get().getString(R.string.cancel), null).show();
    }
}
