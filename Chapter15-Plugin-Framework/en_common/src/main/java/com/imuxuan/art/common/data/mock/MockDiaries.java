package com.imuxuan.art.common.data.mock;

import android.support.annotation.NonNull;

import com.imuxuan.art.common.model.Diary;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Yunpeng Li on 2018/12/8.
 */
public class MockDiaries {

    private static final String DESCRIPTION = "今天，天气晴朗，在第九巷大道上，我遇到一群年轻人，" +
            "优雅地演奏着手风琴，围观人群大都是一群少男少女，他们目不转睛";

    public static Map<String, Diary> mock() {
        return mock(new LinkedHashMap<String, Diary>()); // 构造测试日记数据，存入空的有序哈希集合
    }

    public static Map<String, Diary> mock(Map<String, Diary> data) { // 构造测试日记数据
        Diary test1 = getDiary("2018-11-02  艺术节");
        data.put(test1.getId(), test1);

        Diary test2 = getDiary("2018-11-04  参加会展");
        data.put(test2.getId(), test2);

        Diary test3 = getDiary("2018-11-05  今天的心情很糟糕");
        data.put(test3.getId(), test3);

        Diary test4 = getDiary("2018-11-07  学习了新的架构");
        data.put(test4.getId(), test4);

        Diary test5 = getDiary("2018-11-09  持续进步");
        data.put(test5.getId(), test5);

        Diary test6 = getDiary("2018-11-10  我还在成长");
        data.put(test6.getId(), test6);

        Diary test7 = getDiary("2018-11-11  该怎么样合作");
        data.put(test7.getId(), test7);

        Diary test8 = getDiary("2018-11-12  进步");
        data.put(test8.getId(), test8);
        return data;
    }

    @NonNull
    private static Diary getDiary(String title) { // 创建一个日记对象
        return new Diary(title, DESCRIPTION);
    }

}
