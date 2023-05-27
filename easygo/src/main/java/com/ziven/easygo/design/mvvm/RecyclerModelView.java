package com.ziven.easygo.design.mvvm;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ziven.easygo.design.mvp.AbstractOneData;
import com.ziven.easygo.ui.AbstractEasyAdapter;
import com.ziven.easygo.util.DataProvider;
import com.ziven.easygo.util.EasyUtils;
import com.ziven.easygo.util.ViewHelper;

import java.util.List;

/**
 * @author Ziven
 */
public class RecyclerModelView implements IModelView<RecyclerView> {

    public static final int CLEAR_CODE = 100;

    @Override
    public void layoutViewData(@NonNull ViewHelper<RecyclerView> helper,
                               @NonNull AbstractOneData data) {
        int code = data.getOneCode();
        Object d = data.getOneData();
        List<?> list = null;
        if(d instanceof List<?>) {
            list = (List<?>) d;
        }
        if(list == null) {
            return;
        }

        RecyclerView rv = helper.getView();
        if(rv == null) {
            return;
        }

        RecyclerView.Adapter<?> adapter = rv.getAdapter();
        AbstractEasyAdapter<?, ?> easyAdapter = null;
        if(adapter instanceof AbstractEasyAdapter<?, ?>) {
            easyAdapter = (AbstractEasyAdapter<?, ?>) adapter;
        }
        if(easyAdapter == null) {
            return;
        }

        DataProvider<?> provider = easyAdapter.getDataProvider();
        if(code == CLEAR_CODE) {
            provider.clear();
        }
        EasyUtils.forEach(list, value -> provider.add(EasyUtils.transitionSafety(value)));
        helper.notifyDataSetChanged();
    }
}
