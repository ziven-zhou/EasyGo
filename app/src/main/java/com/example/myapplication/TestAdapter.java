package com.example.myapplication;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.ziven.easygo.ui.AbstractEasyAdapter;
import com.ziven.easygo.ui.AbstractEasyViewHolder;

public class TestAdapter extends AbstractEasyAdapter<String, TestAdapter.TestHolder> {

    @NonNull
    @Override
    public TestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TestHolder(parent, R.layout.item_test);
    }

    public static class TestHolder extends AbstractEasyViewHolder<String> {

        public TestHolder(@NonNull ViewGroup parent, int layoutId) {
            super(parent, layoutId);
        }

        @Override
        protected void bindLayout(String data, int position) {
            setText(R.id.item, data);
        }
    }
}
