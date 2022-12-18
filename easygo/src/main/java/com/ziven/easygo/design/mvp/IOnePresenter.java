package com.ziven.easygo.design.mvp;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

/**
 * @author :zhiyuan.zhou
 * @date :2021/5/25
 */
@Keep
public interface IOnePresenter extends IPresenter {
    /**
     * Getting OneData
     * @param data OneData
     */
    void obtainedOneData(@NonNull AbstractOneData data);
}
