package com.ziven.easygo.design.mvp;

import androidx.annotation.CallSuper;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ziven.easygo.util.Condition;
import com.ziven.easygo.util.EasyUtils;
import com.ziven.easygo.util.LogHelper;

/**
 * @author :zhiyuan.zhou
 * @date :2019/10/12
 */
@Keep
public abstract class AbstractPresenter<V extends IView, M extends AbstractModel<? extends IPresenter>> {

    private static final String TAG = "AbstractPresenter";

    private V mView;
    private M mModel;

    private volatile boolean hasDestroyed = false;

    public static <P extends AbstractPresenter<? extends IView, ? extends AbstractModel<? extends IPresenter>>>
    P of(Class<P> cls) {
        return EasyUtils.newInstance(cls);
    }

    public static <P extends AbstractPresenter<? extends IView, ? extends AbstractModel<? extends IPresenter>>>
    P of(Class<P> cls1, Class<? extends AbstractModel<? extends IPresenter>> cls2) {
        P presenter = of(cls1);
        try {
            presenter.setModel(AbstractModel.of(cls2).setPresenter(presenter.getPresenter()));
        } catch (NullPointerException e) {
            LogHelper.of(TAG).always().throwable(e).print();
        }

        return presenter;
    }

    protected AbstractPresenter() {

    }

    protected AbstractPresenter(@Nullable V view) {
        setViewModel(view, null);
    }

    protected AbstractPresenter(@Nullable M model) {
        setViewModel(null, model);
    }

    protected AbstractPresenter(@Nullable V view, @Nullable M model) {
        setViewModel(view, model);
    }

    public final <P extends AbstractPresenter<V, M>> P setViewModel(@Nullable V view, @Nullable M model) {
        if(!hasDestroyed()) {
            mView = view;
            mModel = model;
            Condition.of(mModel).doTrue(() -> mModel.setPresenter(getPresenter()));
        }
        return EasyUtils.transition(this);
    }

    public final <P extends AbstractPresenter<V, M>> P setView(@Nullable V view) {
        return setViewModel(view, mModel);
    }

    protected final V getView() {
        if(mView == null) {
            setViewModel(newView(), mModel);
        }
        return mView;
    }

    @Nullable
    protected V newView() {
        return null;
    }

    public final <P extends AbstractPresenter<V, M>> P setModel(@Nullable M model) {
        return setViewModel(mView, model);
    }

    protected final M getModel() {
        if(mModel == null) {
            setViewModel(mView, newModel());
        }
        return mModel;
    }

    @Nullable
    protected M newModel() {
        return null;
    }

    protected void doNotNullModel(@NonNull Condition.Doo doo) {
        Condition.of(getModel()).doTrue(doo);
    }

    protected void doNotNullView(@NonNull Condition.Doo doo) {
        Condition.of(getView()).doTrue(doo);
    }

    protected final boolean hasDestroyed() {
        return hasDestroyed;
    }

    /**
     * Get IPresenter
     * @return IPresenter
     */
    protected abstract IPresenter getPresenterInstance();

    public  <P extends IPresenter> P getPresenter() {
        return EasyUtils.transition(getPresenterInstance());
    }

    @CallSuper
    public void onDestroy() {
        Condition.of(mModel).doTrue(() -> mModel.onDestroy());
        setViewModel(null, null);
        hasDestroyed = true;
    }
}
