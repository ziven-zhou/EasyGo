package com.ziven.easygo.design.mvvm;

import android.view.View;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import com.ziven.easygo.design.mvp.AbstractOneData;
import com.ziven.easygo.design.mvp.IView;
import com.ziven.easygo.util.ViewHelper;

/**
 * @author Ziven
 */
@Keep
public interface IModelView<V extends View> extends IView {
    /**
     * Layout ViewData
     * @param helper ViewHelper
     * @param data OneData
     */
    void layoutViewData(@NonNull ViewHelper<V> helper,
                        @NonNull AbstractOneData data);
}
