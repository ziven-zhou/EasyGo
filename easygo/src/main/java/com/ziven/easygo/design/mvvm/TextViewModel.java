package com.ziven.easygo.design.mvvm;

import android.widget.TextView;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import com.ziven.easygo.design.mvp.AbstractOneData;
import com.ziven.easygo.util.ViewHelper;

/**
 * @author Administrator
 */
@Keep
public class TextViewModel implements IModelView<TextView> {


    @Override
    public void layoutViewData(@NonNull ViewHelper<TextView> helper,
                               @NonNull AbstractOneData data) {
        helper.setText(data.getOneData());
    }
}
