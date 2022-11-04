package com.ziven.easygo.overall;

import android.content.Context;

import androidx.annotation.Nullable;

import com.ziven.easygo.design.mvp.OneModel;
import com.ziven.easygo.design.mvp.OnePresenter;
import com.ziven.easygo.util.LogHelper;

import java.util.Map;

/**
 * @author zhiyuan.zhou
 */
public abstract class OverallModel extends OneModel {

    public static final OverallModel EMPTY = new OverallModel() {
        @Override
        protected void obtainOneData(@Nullable Context c, @Nullable Map<Object, Object> params) {
            LogHelper.of("EMPTY : Application implements IOverall.");
        }
    };

    protected OverallModel() {
        new OnePresenter(this);
    }
}
