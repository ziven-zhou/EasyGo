package com.ziven.easygo.design.mvp;

import android.content.Context;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ziven.easygo.util.EasyUtils;

import java.util.Map;

/**
 * @author :zhiyuan.zhou
 * @date :2021/5/25
 */
@Keep
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

    protected Object getParam(@NonNull final Object key, @Nullable final Map<Object, Object> params, @NonNull Object def) {
        return params == null ? def : params.get(key);
    }

    protected <T> T getTargetParam(@NonNull final Object key, @Nullable final Map<Object, Object> params) {
        return EasyUtils.transition(getParam(key, params));
    }

    protected <T> T getTargetParam(@NonNull final Object key, @Nullable final Map<Object, Object> params, @NonNull Object def) {
        return EasyUtils.transition(getParam(key, params, def));
    }

    protected int getInt(@NonNull final Object key, @Nullable final Map<Object, Object> params) {
        return getInt(key, params, 0);
    }

    protected int getInt(@NonNull final Object key, @Nullable final Map<Object, Object> params, int def) {
        Object target = getParam(key, params);
        return target instanceof Integer ? EasyUtils.transition(target) : def;
    }

    protected boolean getBoolean(@NonNull final Object key, @Nullable final Map<Object, Object> params) {
        return getBoolean(key, params, false);
    }

    protected boolean getBoolean(@NonNull final Object key, @Nullable final Map<Object, Object> params, boolean def) {
        Object target = getParam(key, params);
        return target instanceof Boolean ? EasyUtils.transition(target) : def;
    }

    protected String getString(@NonNull final Object key, @Nullable final Map<Object, Object> params) {
        return getString(key, params, EasyUtils.EMPTY);
    }

    protected String getString(@NonNull final Object key, @Nullable final Map<Object, Object> params, String def) {
        Object target = getParam(key, params);
        return target instanceof String ? EasyUtils.transition(target) : def;
    }
}
