package com.imuxuan.art.base;

import android.view.View;

import com.bluelinelabs.conductor.Controller;

public abstract class BaseController extends Controller {

    private boolean isActive = false;


    @Override
    protected void onDestroyView(View view) {
        super.onDestroyView(view);
        setActive(false);
    }

    protected void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }

}
