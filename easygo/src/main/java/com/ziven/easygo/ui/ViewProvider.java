package com.ziven.easygo.ui;

import android.util.SparseArray;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;

import com.ziven.easygo.EasyGos;
import com.ziven.easygo.util.Nulls;
import com.ziven.easygo.util.ViewHelper;

/**
 * @author Ziven
 */
public final class ViewProvider<T extends View> {

    private final SparseArray<ViewHelper<T>> provider;

    public static <T extends View> ViewProvider<T> newInstance() {
        return new ViewProvider<>();
    }

    private ViewProvider() {
        provider = new SparseArray<>();
    }

    @NonNull
    public ViewHelper<T> getViewHelper(@IdRes int id) {
        ViewHelper<T> helper = getViewHelperNullable(id);
        return helper != null ? helper : EasyGos.newViewHelper();
    }

    public ViewHelper<T> getViewHelperNullable(@IdRes int id) {
        return provider.get(id);
    }

    public ViewProvider<T> putView(@IdRes int id, T view) {
        provider.put(id, EasyGos.newViewHelper(view));
        return this;
    }

    public ViewProvider<T> putViewNonNull(@IdRes int id, T view) {
        return view != null ? putView(id, view) : this;
    }

    public T getView(@IdRes int id) {
        return getViewHelper(id).getView();
    }

    public Nulls<T> getViewNulls(@IdRes int id) {
        return getViewHelper(id).getViewNulls();
    }
}
