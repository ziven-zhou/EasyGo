package com.ziven.easygo.design.mvvm;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ziven.easygo.util.LogHelper;

import java.util.Map;

/**
 * @author Ziven
 */
public class EmptyViewModel extends DataViewModel {

    private static final String TAG = "DataViewModel";

    @Override
    protected void obtainViewData(@Nullable Context c,
                                  @Nullable Map<Object, Object> params,
                                  @NonNull ViewData viewData) {
        int id = getInt(ID, params);
        LogHelper.log(TAG, "id=" + id);
        obtainedViewData(viewData.putViewData(id, TAG));
    }
}
