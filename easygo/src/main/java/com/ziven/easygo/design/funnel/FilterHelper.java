package com.ziven.easygo.design.funnel;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ziven
 * @date 2021/5/29
 */
public final class FilterHelper {

    private final Map<Object, EasyFilter> mEasyFilters;

    private FilterHelper() {
        mEasyFilters = new HashMap<>(8);
    }

    private static class Instance {
        public static final FilterHelper INSTANCE = new FilterHelper();
    }

    public static FilterHelper get() {
        return Instance.INSTANCE;
    }

    public EasyFilter withEmpty(@NonNull Object key) {
        return withFilter(key, EasyFilter.of());
    }

    public EasyFilter withFilter(@NonNull Object key, @NonNull EasyFilter easyfilter) {
        mEasyFilters.put(key, easyfilter);
        return easyfilter;
    }

    public EasyFilter get(@NonNull Object key) {
        return mEasyFilters.get(key);
    }

    public EasyFilter withFilter() {
        EasyFilter filter = EasyFilter.of();
        return withFilter(filter, filter);
    }

    public EasyFilter withThis() {
        return withFilter(this, EasyFilter.of());
    }

    public void filterThis() {
        filterNull(this);
    }

    public void filterThis(@NonNull Object value) {
        filterValue(this, value);
    }

    public void filterThis(@NonNull AbstractFilterGrain grain) {
        filterGrain(this, grain);
    }

    public void clearThis() {
        clearFilter(this);
    }

    public void filterNull(@NonNull Object key) {
        filterGrain(key, OneFilterGrain.of());
    }

    public void filterValue(@NonNull Object key, @NonNull Object value) {
        filterGrain(key, OneFilterGrain.of(value));
    }

    public void filterGrain(@NonNull Object key, @NonNull AbstractFilterGrain grain) {
        EasyFilter filter = get(key);
        if(filter != null) {
            filter.filter(grain);
        }
    }

    public void clearFilter(@NonNull Object key) {
        EasyFilter filter = mEasyFilters.remove(key);
        if(filter != null) {
            filter.clear();
        }
    }

    public void clearAll() {
        for (EasyFilter easyFilter : mEasyFilters.values()) {
            easyFilter.clear();
        }
        mEasyFilters.clear();
    }
}
