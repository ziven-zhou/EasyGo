package com.ziven.easygo.design.mvvm;

import android.view.View;

import androidx.annotation.NonNull;

import com.ziven.easygo.design.mvp.AbstractOneData;
import com.ziven.easygo.util.LogHelper;
import com.ziven.easygo.util.ViewHelper;

/**
 * @author Ziven
 */
public class EmptyModelView implements IModelView<View> {

    private static final String TAG = "EmptyModelView";

    @Override
    public void layoutViewData(@NonNull ViewHelper<View> helper,
                               @NonNull AbstractOneData data) {
        LogHelper.log(TAG, "data=" + data);
    }
}
