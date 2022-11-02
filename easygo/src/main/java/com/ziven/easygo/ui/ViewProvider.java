package com.ziven.easygo.ui;

import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;

import com.ziven.easygo.EasyGos;
import com.ziven.easygo.util.MapDataProvider;
import com.ziven.easygo.util.Nulls;
import com.ziven.easygo.util.ViewHelper;

/**
 * @author Ziven
 */
public final class ViewProvider<T extends View> {

    private final MapDataProvider<Integer, ViewHelper<T>> provider;

    public static <T extends View> ViewProvider<T> newInstance() {
        return new ViewProvider<>();
    }

    private ViewProvider() {
        provider = EasyGos.newMapDataProvider();
    }

    @NonNull
    public ViewHelper<T> getViewHelper(@IdRes int id) {
        ViewHelper<T> helper = provider.get(id);
        return helper != null ? helper : EasyGos.newViewHelper();
    }

    public ViewProvider<T> putView(@IdRes int id, T view) {
        provider.put(id, EasyGos.newViewHelper(view));
        return this;
    }

    public T getView(@IdRes int id) {
        return getViewHelper(id).getView();
    }

    public Nulls<T> getViewNulls(@IdRes int id) {
        return getViewHelper(id).getViewNulls();
    }
}
