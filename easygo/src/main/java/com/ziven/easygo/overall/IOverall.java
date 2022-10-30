package com.ziven.easygo.overall;

/**
 * Application implements IOverall
 * {@link IDispatch}
 * @author :zhiyuan.zhou
 * @date :2022/10/29
 */
public interface IOverall<T extends IDispatch> {

    /**
     * Get Dispatch
     * @return Overall
     */
    T getDispatch();
}
