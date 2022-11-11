package com.ziven.easygo.design.mvp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ziven.easygo.util.Condition;
import com.ziven.easygo.util.ThreadUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :zhiyuan.zhou
 * @date :2021/5/25
 */
public class OnePresenter extends AbstractPresenter<IOneView, OneModel> implements IOnePresenter {

    private Map<Object, Object> mParams;
    private boolean mClearAfterObtain = true;
    private boolean mRunOnWorkThread = false;

    public OnePresenter() {

    }

    public OnePresenter(@Nullable IOneView view) {
        super(view);
    }

    public OnePresenter(@Nullable OneModel model) {
        super(model);
    }

    public OnePresenter(@Nullable IOneView view, @Nullable OneModel model) {
        super(view, model);
    }

    /**
     * Getting OneData
     *
     * @param data OneData
     */
    @Override
    public void obtainedOneData(@NonNull final AbstractOneData data) {
        ThreadUtils.runOnMainThread(() -> doNotNullView(() -> getView().layoutOneData(data)));
    }

    public void obtainOneData() {
        obtainOneData(null, mParams);
    }

    public void obtainOneData(@Nullable Context c) {
        obtainOneData(c, mParams);
    }

    public void obtainOneData(@Nullable Map<Object, Object> params) {
        obtainOneData(null, params);
    }

    public void obtainOneData(@Nullable final Context c, @Nullable Map<Object, Object> params) {
        final Map<Object, Object> copy = new HashMap<>(params == null ? new HashMap<>(0) : params);
        Condition
                .of(mClearAfterObtain)
                .doTrue(this::clearParam);
        if(mRunOnWorkThread) {
            ThreadUtils.runOnWorkerThread(() -> doNotNullModel(() -> getModel().obtainOneData(c, copy)));
        } else {
            ThreadUtils.runOnMainThread(() -> doNotNullModel(() -> getModel().obtainOneData(c, copy)));
        }
    }

    public OnePresenter runOnWorkThread() {
        mRunOnWorkThread = true;
        return this;
    }

    public OnePresenter runOnMainThread() {
        mRunOnWorkThread = false;
        return this;
    }

    public OnePresenter clearAfterObtain(boolean clear) {
        mClearAfterObtain = clear;
        return this;
    }

    public OnePresenter clearParam() {
        Condition.of(mParams).doTrue(() -> mParams.clear());
        return this;
    }

    public OnePresenter setParam(@Nullable Object param) {
        return setParam(param, param);
    }

    public OnePresenter setParam(@Nullable Object key, @Nullable Object param) {
        Condition.of(mParams)
                .doFalse(() -> mParams = new HashMap<>(8))
                .toTrue()
                .and(key)
                .and(param)
                .doTrue(() -> mParams.put(key, param));
        return this;
    }

    @Override
    protected IPresenter getPresenterInstance() {
        return this;
    }
}
