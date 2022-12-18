package com.ziven.easygo.overall;

import androidx.annotation.Keep;

/**
 * Application implements IOverall
 * {@link IDispatch}
 * @author :zhiyuan.zhou
 * @date :2022/10/29
 */
@Keep
public interface IOverall<T extends IDispatch> {

    /**
     * Get Dispatch
     * @return Overall
     */
    T getDispatch();
}
