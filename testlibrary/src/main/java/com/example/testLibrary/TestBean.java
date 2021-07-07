package com.example.testLibrary;

import androidx.annotation.NonNull;

/**
 * @author :zhiyuan.zhou
 * @date :2019/10/14
 */
public class TestBean {

    private final String title;

    public static TestBean of(@NonNull String title) {
        return new TestBean(title);
    }

    private TestBean(@NonNull String title) {
        this.title = title;
    }

    public @NonNull String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "title='" + title + '\'' +
                '}';
    }
}
