package com.ziven.easygo.design.funnel;

import androidx.annotation.NonNull;

/**
 * @author Ziven
 * @date 2021/5/29
 */
public interface IFilter<T extends AbstractFilterGrain> {

    /**
     * Filter Grain
     * @param grain Grain
     */
    void filter(@NonNull T grain);
}
