package com.ziven.easygo.design.mvp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author Ziven
 * @date 2022/1/9
 */
public class OnePresenterHelper implements IOneView {

    private final OnePresenter onePresenter;
    private IOneView oneView;

    private static final int DEFAULT_CODE = -404;
    private int code = DEFAULT_CODE;

    private OnePresenterHelper(@NonNull Class<? extends OneModel> cls) {
        onePresenter = OnePresenter.of(OnePresenter.class, cls);
        onePresenter.setView(this);
    }

    public static OnePresenterHelper newInstance(@NonNull Class<? extends OneModel> cls) {
        return new OnePresenterHelper(cls);
    }

    public OnePresenterHelper setCode(int c) {
        code = c;
        return this;
    }

    public OnePresenterHelper setView(@NonNull IOneView view) {
        oneView = view;
        return this;
    }

    public OnePresenterHelper setParam(@Nullable Object param) {
        onePresenter.setParam(param);
        return this;
    }

    public OnePresenterHelper setParam(@Nullable Object key, @Nullable Object param) {
        onePresenter.setParam(key, param);
        return this;
    }

    public OnePresenterHelper runOnWorkThread() {
        onePresenter.runOnWorkThread();
        return this;
    }

    public void obtain() {
        onePresenter.obtainOneData();
    }

    @Override
    public void layoutOneData(@NonNull AbstractOneData data) {
        if(oneView != null) {
            if(code != DEFAULT_CODE) {
                data.setOneCode(code);
            }
            oneView.layoutOneData(data);
        }
    }
}
