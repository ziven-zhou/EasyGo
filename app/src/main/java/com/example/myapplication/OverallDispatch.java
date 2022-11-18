package com.example.myapplication;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.activity.MethodTestActivity;
import com.example.myapplication.activity.ServerTestActivity;
import com.example.myapplication.activity.ReceiverTestActivity;
import com.ziven.easygo.design.mvp.OneData;
import com.ziven.easygo.overall.IDispatch;
import com.ziven.easygo.overall.OverallModel;

import java.util.Map;

/**
 * @author Ziven
 */
public class OverallDispatch implements IDispatch {

    public static final String ACTIVITY_PATH_TAG = "ACTIVITY_PATH";
    private static final String[] ACTIVITY_PATH = {
            ReceiverTestActivity.class.getSimpleName(),
            MethodTestActivity.class.getSimpleName(),
            ServerTestActivity.class.getSimpleName()
    };

    private static int checkIndex(int index) {
        return index >= 0 && index < ACTIVITY_PATH.length ? index : 0;
    }

    @NonNull
    @Override
    public OverallModel newModel() {
        return new OverallModel() {
            @Override
            protected void obtainOneData(@Nullable Context c, @Nullable Map<Object, Object> params) {
                int index = checkIndex(getInt(ACTIVITY_PATH_TAG, params, 0));
                obtainedOneData(OneData.of(ACTIVITY_PATH[index]));
            }
        };
    }
}
