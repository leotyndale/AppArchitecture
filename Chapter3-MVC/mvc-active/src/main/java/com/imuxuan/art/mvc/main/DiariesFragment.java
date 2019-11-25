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

public class DiariesFragment extends Fragment {

    private DiariesController mController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mController = new DiariesController(this);
        
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_diaries, container, false);
        mController.setDiariesList((RecyclerView) root.findViewById(R.id.diaries_list));
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mController.loadDiaries();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_write, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                mController.gotoWriteDiary();
                return true;
        }
        return false;
    }

}
