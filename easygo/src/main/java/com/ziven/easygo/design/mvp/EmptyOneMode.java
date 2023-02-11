package com.ziven.easygo.design.mvp;

import android.content.Context;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;

import java.util.Map;

/**
 * @author Ziven
 */
@Keep
public class EmptyOneMode extends OneModel {

    @Override
    protected void obtainOneData(@Nullable Context c, @Nullable Map<Object, Object> params) {
        obtainedOneData(OneData.of());
    }
}
