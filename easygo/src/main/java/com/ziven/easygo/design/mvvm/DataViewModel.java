package com.ziven.easygo.design.mvvm;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ziven.easygo.util.LogHelper;

import java.util.Map;

/**
 * @author Ziven
 */
public class DataViewModel extends ViewModel {

    private static final String TAG = "DataViewModel";
    public static final String DATA = "view_model_data";
    public static final String ID = "view_model_id";

    @Override
    protected void obtainViewData(@Nullable Context c,
                                  @Nullable Map<Object, Object> params,
                                  @NonNull ViewData viewData) {
        int id = getInt(ID, params);
        Object data = getParam(DATA, params);
        LogHelper.log(TAG, "id=" + id + " data=" + data);
        obtainedViewData(viewData.putViewData(id, data));
    }
}
