package com.imuxuan.art.mvc.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.imuxuan.art.mvc.R;
import com.imuxuan.art.mvc.data.DiariesRepository;
import com.imuxuan.art.mvc.model.Diary;
import com.imuxuan.art.mvc.observer.Observer;
import com.imuxuan.art.mvc.source.DataCallback;

import java.util.ArrayList;
import java.util.List;

public class DiariesController implements Observer<Diary> {

    private final DiariesRepository mDiariesRepository;
    private final DiariesFragment mView;
    private DiariesAdapter mListAdapter;

    public DiariesController(@NonNull DiariesFragment diariesFragment) {
        mDiariesRepository = DiariesRepository.getInstance();
        mView = diariesFragment;
        mView.setHasOptionsMenu(true);
        mListAdapter = new DiariesAdapter(new ArrayList<Diary>());
    }

    public void loadDiaries() {
        mDiariesRepository.getAll(new DataCallback<List<Diary>>() {
            @Override
            public void onSuccess(List<Diary> diaryList) {
                processDiaries(diaryList);
            }

            @Override
            public void onError() {
                showError();
            }
        });
    }

    private void processDiaries(List<Diary> diaries) {
        for (Diary diary : diaries) {
            diary.registerObserver(this);
        }
        mListAdapter.update(diaries);
    }

    public void gotoWriteDiary() {
        showMessage(mView.getString(R.string.developing));
    }

    public void showError() {
        showMessage(mView.getString(R.string.error));
    }

    private void showMessage(String message) {
        Toast.makeText(mView.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void setDiariesList(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(mView.getContext()));
        recyclerView.setAdapter(mListAdapter);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(mView.getContext(), DividerItemDecoration.VERTICAL)
        );
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void update(Diary diary) {
        mDiariesRepository.update(diary);
        loadDiaries();
    }
}
