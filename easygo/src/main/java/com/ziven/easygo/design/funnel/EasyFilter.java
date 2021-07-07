package com.ziven.easygo.design.funnel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ziven.easygo.util.EasyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ziven
 * @date 2021/5/29
 */
public final class EasyFilter {

    private final List<IFilter<? extends AbstractFilterGrain>> mFilters;

    public static EasyFilter of() {
        return new EasyFilter();
    }

    private EasyFilter() {
        mFilters = new ArrayList<>(8);
    }

    public EasyFilter add(@Nullable IFilter<? extends AbstractFilterGrain> filter) {
        mFilters.add(filter);
        return this;
    }

    public EasyFilter remove(@Nullable IFilter<? extends AbstractFilterGrain> filter) {
        mFilters.remove(filter);
        return this;
    }

    public EasyFilter filter(@NonNull AbstractFilterGrain grain) {
        for(IFilter<? extends AbstractFilterGrain> filter : mFilters) {
            if(grain.isEnd()) {
                return this;
            }
            filter.filter(EasyUtils.transition(grain));
        }
        return this;
    }

    public EasyFilter clear() {
        mFilters.clear();
        return this;
    }
}
