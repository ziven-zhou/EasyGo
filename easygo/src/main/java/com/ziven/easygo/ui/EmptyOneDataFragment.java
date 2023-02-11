package com.ziven.easygo.ui;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import com.ziven.easygo.design.mvp.AbstractOneData;
import com.ziven.easygo.design.mvp.EmptyOneMode;
import com.ziven.easygo.design.mvp.OneModel;

/**
 * @author Ziven
 */
@Keep
public abstract class EmptyOneDataFragment extends AbstractOneDataFragment {

    @Override
    public void layoutOneData(@NonNull AbstractOneData data) {

    }

    @NonNull
    @Override
    protected Class<? extends OneModel> obtainOneModelClass() {
        return EmptyOneMode.class;
    }
}
