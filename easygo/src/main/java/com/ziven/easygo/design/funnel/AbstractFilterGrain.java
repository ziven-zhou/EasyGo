package com.ziven.easygo.design.funnel;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ziven.easygo.util.EasyUtils;

/**
 * @author Ziven
 * @date 2021/5/29
 */
@Keep
public abstract class AbstractFilterGrain {

    private Object mGrain;
    private boolean isEnd;

    public AbstractFilterGrain () {
    }

    public AbstractFilterGrain(@Nullable Object grain) {
        mGrain = grain;
    }

    public <G extends AbstractFilterGrain> G setGain(@NonNull Object grain) {
        mGrain = grain;
        return EasyUtils.transition(this);
    }

    @Nullable
    public <G> G getGain() {
        return EasyUtils.transition(mGrain);
    }

    public boolean hasGain() {
        return mGrain != null;
    }

    public <G extends AbstractFilterGrain> G setEnd() {
        isEnd = true;
        return EasyUtils.transition(this);
    }

    public <G extends AbstractFilterGrain> G setGoOn() {
        isEnd = false;
        return EasyUtils.transition(this);
    }

    public boolean isEnd() {
        return isEnd;
    }

    @NonNull
    @Override
    public String toString() {
        return "AbstractFilterGrain{" +
                "mGrain=" + mGrain +
                '}';
    }
}
