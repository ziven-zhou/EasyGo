package com.ziven.easygo.design.mvp;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ziven.easygo.util.Condition;
import com.ziven.easygo.util.EasyUtils;

/**
 * @author :zhiyuan.zhou
 * @date :2019/10/14
 */
public abstract class AbstractModel<P extends IPresenter> {

    private P mPresenter;

    public static <M extends AbstractModel<? extends IPresenter>> M of(Class<M> cls) {
        return EasyUtils.newInstance(cls);
    }

    public AbstractModel() {

    }

    public AbstractModel(@Nullable P presenter) {
        mPresenter = presenter;
    }

    public final <M extends AbstractModel<? extends IPresenter>> M  setPresenter(@Nullable P presenter) {
        mPresenter = presenter;
        return EasyUtils.transition(this);
    }

    public final P getPresenter() {
        return mPresenter;
    }

    protected void doNotNullPresenter(@NonNull Condition.Doo doo) {
        Condition.of(getPresenter()).doTrue(doo);
    }

    @CallSuper
    public void onDestroy() {
        mPresenter = null;
    }
}
