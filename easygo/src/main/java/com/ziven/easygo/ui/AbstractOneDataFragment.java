package com.ziven.easygo.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ziven.easygo.design.mvp.IOneView;
import com.ziven.easygo.design.mvp.OneModel;
import com.ziven.easygo.design.mvp.OnePresenter;
import com.ziven.easygo.util.EasyUtils;
import com.ziven.easygo.util.ViewHelper;

/**
 * @author zhiyuan.zhou
 */
public abstract class AbstractOneDataFragment extends Fragment implements IOneView {

    private OnePresenter mOnePresenter;
    private ViewProvider<View> mViewProvider;

    public AbstractOneDataFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    private ViewProvider<View> getViewProvider() {
        if(mViewProvider == null) {
            mViewProvider = ViewProvider.newInstance();
        }
        return mViewProvider;
    }

    @NonNull
    protected ViewHelper<View> getViewHelper(@IdRes int id) {
        ViewHelper<View> helper = getViewProvider().getViewHelperNullable(id);
        if(helper != null) {
            return helper;
        }
        getView2(id);
        return getViewProvider().getViewHelper(id);
    }

    protected <T extends View> T getView2(@IdRes int id) {
        if(id == View.NO_ID) {
            return null;
        }
        View view = getViewProvider().getView(id);
        if(view != null) {
            return EasyUtils.transition(view);
        }
        View parent = getView();
        if(parent == null || (view = parent.findViewById(id)) == null) {
            return null;
        }
        getViewProvider().putViewNonNull(id, view);
        return EasyUtils.transition(view);
    }

    /**
     * Obtain Model Class
     * @return Model Class
     */
    @NonNull
    protected abstract Class<? extends OneModel> obtainOneModelClass();

    protected OnePresenter getOnePresenter() {
        return mOnePresenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Class<? extends OneModel> cls = obtainOneModelClass();
        mOnePresenter = obtainOnePresenter(cls);
        if(mOnePresenter == null) {
            mOnePresenter = OnePresenter.of(OnePresenter.class, cls);
        }
        mOnePresenter.setView(this);
        createdActivity();
    }

    @Nullable
    protected OnePresenter obtainOnePresenter(@NonNull Class<? extends OneModel> cls) {
        return null;
    }

    /**
     * Created Activity
     */
    protected void createdActivity() {}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createdView(view);
    }

    /**
     * Init View
     * @param root Root View
     */
    protected abstract void createdView(@NonNull View root);

    @Override
    public void onDestroyView() {
        destroyView();
        super.onDestroyView();
    }


    /**
     * Destroy View
     */
    protected abstract void destroyView();

    @Override
    public void onDestroy() {
        destroyActivity();
        getOnePresenter().onDestroy();
        super.onDestroy();
    }

    protected void destroyActivity() {}
}
