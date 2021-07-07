package com.ziven.easygo.design.mvp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ziven.easygo.util.EasyUtils;

import java.util.Map;

/**
 * @author :zhiyuan.zhou
 * @date :2021/5/25
 */
public abstract class OneModel extends AbstractModel<IOnePresenter> {

    /**
     * Obtain OneData
     * @param c Context
     * @param params Map<Object, Object>
     */
    protected abstract void obtainOneData(@Nullable final Context c, @Nullable final Map<Object, Object> params);

    protected void obtainedOneData(@NonNull AbstractOneData data) {
        doNotNullPresenter(() -> getPresenter().obtainedOneData(data));
    }

    protected Object getParam(@NonNull final Object key, @Nullable final Map<Object, Object> params) {
        return params == null ? null : params.get(key);
    }

    protected <T> T getTargetParam(@NonNull final Object key, @Nullable final Map<Object, Object> params) {
        return EasyUtils.transition(getParam(key, params));
    }
}
