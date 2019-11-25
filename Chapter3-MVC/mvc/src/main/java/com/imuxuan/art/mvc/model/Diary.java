package com.imuxuan.art.mvc.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.UUID;

public class Diary { // 日记Model

    private String id; // 日记唯一标识
    private String title; // 日记标题
    private String description; // 日记内容

    public Diary(@Nullable String title, @Nullable String description) {
        this(title, description, UUID.randomUUID().toString()); // 通过UUID生成唯一标识
    }

    public Diary(@Nullable String title, @Nullable String description,
                 @NonNull String id) { // 构造方法
        this.id = id;
        this.title = title;
        this.description = description;
    }

    /**
     * Getter
     */
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Setter
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
