package com.example.myapplication;

import android.content.Context;

import androidx.annotation.Nullable;

import com.ziven.easygo.design.mvp.OneData;
import com.ziven.easygo.design.mvp.OneModel;
import com.ziven.easygo.util.EasyUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Ziven
 */
public class TestModel extends OneModel {

    private final List<String> list = EasyUtils.newList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");

    @Override
    protected void obtainOneData(@Nullable Context c, @Nullable Map<Object, Object> params) {
        List<String> l = new ArrayList<>(Arrays.asList(OverallDispatch.ACTIVITY_PATH));
        l.addAll(list);
        obtainedOneData(OneData.of(l));
    }
}
