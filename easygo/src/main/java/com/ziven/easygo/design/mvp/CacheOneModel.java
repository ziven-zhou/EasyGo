package com.ziven.easygo.design.mvp;

import android.content.Context;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;

import java.util.Map;

/**
 * @author Ziven
 * @date 2022/1/9
 */
@Keep
public abstract class CacheOneModel extends OneModel {

    @Override
    protected void obtainOneData(@Nullable Context c, @Nullable Map<Object, Object> params) {
        OneData cache = obtainOneDataCache();
        if(isObtainedOneDataCache(cache)) {
            obtainedOneData(cache);
        }
        if(isObtainOneDataNext(cache)) {
            obtainOneData2(c, params, cache);
        }
    }

    protected boolean isObtainedOneDataCache(OneData cache) {
        return cache != null;
    }


    protected boolean isObtainOneDataNext(OneData cache) {
        return true;
    }

    /**
     * Obtain OneData
     * @param c Context
     * @param params Params
     * @param cache Cache
     */
    protected abstract void obtainOneData2(@Nullable Context c, @Nullable Map<Object, Object> params, OneData cache);


    /**
     * Obtain cache OneData
     * @return OneData
     */
    protected abstract OneData obtainOneDataCache();
}
