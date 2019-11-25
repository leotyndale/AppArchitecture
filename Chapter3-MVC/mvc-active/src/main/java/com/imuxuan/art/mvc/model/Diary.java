package com.imuxuan.art.mvc.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.imuxuan.art.mvc.observer.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Diary {

    private String id;

    private String title;

    private String description;

    private List<Observer<Diary>> mObservers;

    public Diary(@Nullable String title, @Nullable String description) {
        this(title, description, UUID.randomUUID().toString());
    }

    public Diary(@Nullable String title, @Nullable String description,
                 @NonNull String id) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyObservers();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyObservers();
    }

    public void registerObserver(Observer<Diary> observer) {
        getObservers().add(observer);
    }

    private List<Observer<Diary>> getObservers() {
        if (mObservers == null) {
            mObservers = new ArrayList<>();
        }
        return mObservers;
    }

    public void notifyObservers() {
        for (Observer<Diary> observer : getObservers()) {
            observer.update(this);
        }
    }

}
