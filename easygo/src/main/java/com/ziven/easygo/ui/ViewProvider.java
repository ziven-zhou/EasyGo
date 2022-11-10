package com.ziven.easygo.ui;

import android.util.SparseArray;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;

import com.ziven.easygo.EasyGos;
import com.ziven.easygo.util.Carry;
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
        return getProvider().get(id);
    }

    public ViewProvider<T> putView(@IdRes int id, T view) {
        getProvider().put(id, EasyGos.newViewHelper(view));
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

    public ViewProvider<T> remove(@IdRes int id) {
        provider.remove(id);
        return this;
    }

    public ViewProvider<T> clear() {
        provider.clear();
        return this;
    }

    public SparseArray<ViewHelper<T>> getProvider() {
        return provider;
    }

    public ViewProvider<T> forEach(@NonNull Carry<ViewHelper<T>> carry) {
        int size = provider.size();
        ViewHelper<T> helper;
        for(int i=0; i<size; i++) {
            helper = provider.valueAt(i);
            if(helper != null) {
                carry.carry(helper);
            }
        }
        return this;
    }

    public ViewProvider<T> forEachView(@NonNull Carry<T> carry) {
        return forEach(helper -> {
            T view = helper.getView();
            if(view != null) {
                carry.carry(view);
            }
        });
    }
}
