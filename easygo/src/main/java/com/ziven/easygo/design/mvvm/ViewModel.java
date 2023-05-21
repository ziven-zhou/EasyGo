package com.ziven.easygo.design.mvvm;

import android.content.Context;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ziven.easygo.design.mvp.OneModel;

import java.util.Map;

/**
 * @author Ziven
 */
@Keep
public abstract class ViewModel extends OneModel {

    public static final String PARAM_S_1 = "param_s_1";
    public static final String PARAM_S_2 = "param_s_2";
    public static final String PARAM_S_3 = "param_s_3";
    public static final String PARAM_S_4 = "param_s_4";
    public static final String PARAM_S_5 = "param_s_5";

    public static final String PARAM_I_1 = "param_i_1";
    public static final String PARAM_I_2 = "param_i_2";
    public static final String PARAM_I_3 = "param_i_3";
    public static final String PARAM_I_4 = "param_i_4";
    public static final String PARAM_I_5 = "param_i_5";

    public static final String PARAM_B_1 = "param_b_1";
    public static final String PARAM_B_2 = "param_b_2";
    public static final String PARAM_B_3 = "param_b_3";
    public static final String PARAM_B_4 = "param_b_4";
    public static final String PARAM_B_5 = "param_b_5";

    protected void obtainedViewData(@NonNull ViewData viewData) {
        obtainedOneData(viewData);
    }

    @Override
    protected void obtainOneData(@Nullable Context c,
                                 @Nullable Map<Object, Object> params) {
        obtainViewData(c, params, ViewData.newViewData());
    }

    /**
     * Obtain ViewData
     * @param c Context
     * @param params params
     * @param viewData ViewData
     */
    protected abstract void obtainViewData(@Nullable Context c,
                                           @Nullable Map<Object, Object> params,
                                           @NonNull ViewData viewData);
}
