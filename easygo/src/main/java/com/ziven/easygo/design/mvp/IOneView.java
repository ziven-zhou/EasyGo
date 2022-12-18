package com.ziven.easygo.design.mvp;

import androidx.annotation.Keep;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;

/**
 * @author :zhiyuan.zhou
 * @date :2021/5/25
 */
@Keep
public interface IOneView extends IView {
    /**
     * Layout after getting OneData
     * @param data OneData
     */
    @MainThread
    void layoutOneData(@NonNull AbstractOneData data);
}
