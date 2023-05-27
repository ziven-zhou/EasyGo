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
public class TextModelView implements IModelView<TextView> {

    @Override
    public void layoutViewData(@NonNull ViewHelper<TextView> helper,
                               @NonNull AbstractOneData data) {
        Object d = data.getOneData();
        if(d instanceof CharSequence) {
            helper.setText((CharSequence) d);
        } else if(d instanceof Integer) {
            helper.setText((Integer) d);
        } else if(d != null) {
            helper.setText(d.toString());
        }
    }
}
