package com.ziven.easygo.design.funnel;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ziven.easygo.util.Carry;
import com.ziven.easygo.util.EasyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ziven
 * @date 2021/5/29
 */
@Keep
public final class EasyFilter {

    private final List<IFilter<? extends AbstractFilterGrain>> mFilters;

    public static EasyFilter of() {
        return new EasyFilter();
    }

    private EasyFilter() {
        mFilters = new ArrayList<>(8);
    }

    public EasyFilter add(@Nullable IFilter<? extends AbstractFilterGrain> filter) {
        synchronized (mFilters) {
            if(filter != null) {
                mFilters.add(filter);
            }
        }
        return this;
    }

    public EasyFilter addAll(@Nullable IFilter<? extends AbstractFilterGrain>... filters) {
        synchronized (mFilters) {
            EasyUtils.forEach(filters, (Carry<IFilter<? extends AbstractFilterGrain>>) mFilters::add);
        }
        return this;
    }

    public EasyFilter remove(@Nullable IFilter<? extends AbstractFilterGrain> filter) {
        synchronized (mFilters) {
            if(filter != null) {
                mFilters.remove(filter);
            }
        }
        return this;
    }

    public EasyFilter filter(@NonNull AbstractFilterGrain grain) {
        final List<IFilter<? extends AbstractFilterGrain>> filters;
        synchronized (mFilters) {
            filters = new ArrayList<>(mFilters);
        }
        for(IFilter<? extends AbstractFilterGrain> filter : filters) {
            if(grain.isEnd()) {
                return this;
            }
            filter.filter(EasyUtils.transition(grain));
        }
        return this;
    }

    public EasyFilter clear() {
        synchronized (mFilters) {
            mFilters.clear();
        }
        return this;
    }

    @NonNull
    public FilterHelper filterHelper() {
        return FilterHelper.get();
    }
}
