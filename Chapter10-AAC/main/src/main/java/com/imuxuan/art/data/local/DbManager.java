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

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.NonNull;

import com.imuxuan.art.EnApplication;
import com.imuxuan.art.model.Diary;

@Database(entities = {Diary.class}, version = 1, exportSchema = false)
abstract class DbManager extends RoomDatabase {

    private static volatile DbManager mInstance;

    abstract DiaryDao diaryDao();

    static DbManager getInstance() {
        if (mInstance == null) {
            synchronized (DbManager.class) {
                if (mInstance == null) {
                    mInstance = getDatabase();
                }
            }
        }
        return mInstance;
    }

    @NonNull
    private static DbManager getDatabase() {
        return Room.databaseBuilder(
                EnApplication.get(),
                DbManager.class,
                "enDiary.db"
        ).build();
    }
}
