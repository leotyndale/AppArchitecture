/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.imuxuan.art.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.imuxuan.art.model.Diary;

import java.util.List;

@Dao
public interface DiaryDao { // 数据库操作类

    // 获得所有数据
    @Query("SELECT * FROM Diary")
    List<Diary> getAll();

    // 获取某个数据
    @Query("SELECT * FROM Diary WHERE diaryId = :id")
    Diary get(String id);

    // 更新某个数据
    @Update
    int update(Diary diary);

    // 清空所有数据
    @Query("DELETE FROM Diary")
    void deleteAll();

    // 删除某个数据
    @Query("DELETE FROM Diary WHERE diaryId = :id")
    int delete(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(Diary diary);
}
