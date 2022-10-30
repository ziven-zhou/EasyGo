package com.example.myapplication;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ziven.easygo.design.mvp.OneData;
import com.ziven.easygo.overall.IDispatch;
import com.ziven.easygo.overall.OverallModel;
import com.ziven.easygo.util.LogHelper;

import java.util.Map;

/**
 * @author Ziven
 */
public class OverallDispatch implements IDispatch {


    @NonNull
    @Override
    public OverallModel newModel() {
        return new OverallModel() {
            @Override
            protected void obtainOneData(@Nullable Context c, @Nullable Map<Object, Object> params) {
                LogHelper.of("OverallDispatch").always().join("obtainOneData").print();
                obtainedOneData(OneData.of("I am OverallDispatch"));
            }
        };
    }
}
