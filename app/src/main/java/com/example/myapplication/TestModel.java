package com.example.myapplication;

import android.content.Context;

import androidx.annotation.Nullable;

import com.ziven.easygo.design.mvp.OneData;
import com.ziven.easygo.design.mvp.OneModel;
import com.ziven.easygo.util.EasyUtils;

import java.util.List;
import java.util.Map;

public class TestModel extends OneModel {

    private final List<String> list = EasyUtils.newList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");

    @Override
    protected void obtainOneData(@Nullable Context c, @Nullable Map<Object, Object> params) {
        obtainedOneData(OneData.of(list));
    }
}
