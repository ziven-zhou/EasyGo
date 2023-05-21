package com.example.myapplication;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ziven.easygo.design.mvvm.ViewData;
import com.ziven.easygo.design.mvvm.ViewModel;
import com.ziven.easygo.util.LogHelper;

import java.util.Map;

/**
 * @author Ziven
 */
public class TestViewModel extends ViewModel {

    @Override
    protected void obtainViewData(@Nullable Context c,
                                  @Nullable Map<Object, Object> params,
                                  @NonNull ViewData viewData) {

        String sb = getString(PARAM_S_1, params) +
                "-" +
                getString(PARAM_S_2, params) +
                "-" +
                getString(PARAM_S_3, params) +
                "-" +
                getString(PARAM_S_4, params) +
                "-" +
                getString(PARAM_S_5, params) +
                "-" +
                getInt(PARAM_I_1, params) +
                "-" +
                getInt(PARAM_I_2, params) +
                "-" +
                getInt(PARAM_I_3, params) +
                "-" +
                getInt(PARAM_I_4, params) +
                "-" +
                getInt(PARAM_I_5, params) +
                "-" +
                getBoolean(PARAM_B_1, params) +
                "-" +
                getBoolean(PARAM_B_2, params) +
                "-" +
                getBoolean(PARAM_B_3, params) +
                "-" +
                getBoolean(PARAM_B_4, params) +
                "-" +
                getBoolean(PARAM_B_5, params) +
                "-" +
                Thread.currentThread();

        LogHelper.log("TestViewModel:" + sb);

        obtainedViewData(viewData
                .putViewData(R.id.button, "按钮")
                .putViewData(R.id.button2, "按钮2"));
    }
}
