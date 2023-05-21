package com.ziven.easygo.design.mvvm;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ziven.easygo.design.mvp.AbstractOneData;
import com.ziven.easygo.util.EasyUtils;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Ziven
 */
@Keep
public class ViewData extends AbstractOneData {

    private final ConcurrentHashMap<Integer, Object> dataMap;

    public static ViewData newViewData() {
        return new ViewData();
    }

    private ViewData() {
        dataMap = new ConcurrentHashMap<>();
    }

    public ViewData putViewData(int id, Object data) {
        dataMap.put(id, data);
        return this;
    }

    @Nullable
    public <T> T getViewData(int id) {
        return EasyUtils.transitionSafety(dataMap.get(id));
    }

    @NonNull
    @Override
    public String toString() {
        return "ViewData{" +
                "dataMap=" + dataMap +
                '}';
    }
}
