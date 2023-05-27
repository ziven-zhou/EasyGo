package com.ziven.easygo.design.mvvm;

import android.content.ComponentName;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;

import com.ziven.easygo.autowired.EasyGo;
import com.ziven.easygo.design.mvp.AbstractOneData;
import com.ziven.easygo.util.EasyUtils;
import com.ziven.easygo.util.LogHelper;
import com.ziven.easygo.util.ViewHelper;

/**
 * @author Ziven
 */
public class ActionModelView implements IModelView<View> {

    private static final String TAG = "ActionViewModel";

    @Override
    public void layoutViewData(@NonNull ViewHelper<View> helper,
                               @NonNull AbstractOneData data) {
        Object d = data.getOneData();
        if(d instanceof Intent) {
            helper.setOnClickListener(
                    view -> EasyUtils.runSafety(
                            () -> EasyGo.easyGo(view.getContext(), (Intent) d), TAG));
        }else if(d instanceof ComponentName) {
            helper.setOnClickListener(view -> EasyUtils.runSafety(() -> {
                Intent intent = new Intent();
                intent.setComponent((ComponentName) d);
                EasyGo.easyGo(view.getContext(), intent);
            }, TAG));
        } else if(d instanceof String) {
            helper.setOnClickListener(view -> {
                try {
                    Intent intent = Intent.parseUri((String) d, 0);
                    EasyGo.easyGo(view.getContext(), intent);
                } catch (Throwable th) {
                    LogHelper.log(TAG, th);
                }
            });
        } else if (d instanceof Class<?>) {
            helper.setOnClickListener(
                    view -> EasyUtils.runSafety(
                            () -> EasyGo.easyGo(view.getContext(), (Class<?>) d), TAG));
        }
    }
}
