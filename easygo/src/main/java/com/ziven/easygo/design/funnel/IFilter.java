package com.ziven.easygo.design.funnel;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

/**
 * @author Ziven
 * @date 2021/5/29
 */
@Keep
public interface IFilter<T extends AbstractFilterGrain> {

    /**
     * Filter Grain
     * @param grain Grain
     */
    void filter(@NonNull T grain);
}
