package com.ziven.easygo.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ziven.easygo.design.mvp.IOneView;
import com.ziven.easygo.design.mvp.OneModel;
import com.ziven.easygo.design.mvp.OnePresenter;
import com.ziven.easygo.util.EasyUtils;
import com.ziven.easygo.util.ViewHelper;
import com.ziven.easygo.util.ViewUtils;

/**
 * @author zhiyuan.zhou
 */
@Keep
public abstract class AbstractOneDataFragment extends Fragment implements IOneView {

    private OnePresenter mOnePresenter;
    private ViewProvider<View> mViewProvider;

    public static <T extends AbstractOneDataFragment> T newInstance(@NonNull Class<T> cls) {
        return EasyUtils.newInstance(cls);
    }

    public AbstractOneDataFragment() {
        super();
    }

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

    private void findView(@NonNull View view) {
        if(findLayout()) {
            ViewUtils.forEachView(view, v -> {
                if(v.getId() != View.NO_ID) {
                    getViewProvider().putView(v.getId(), v);
                }
            });
        }
    }

    /**
     * Find layout
     * @return Find
     */
    protected boolean findLayout() {
        return false;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view != null ? view : getLayout(inflater, container);
    }

    private View getLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        Object layout = obtainLayout();
        if(layout instanceof Integer) {
            return inflater.inflate((int) layout, container, false);
        } else if(layout instanceof View) {
            return EasyUtils.transition(layout);
        }
        return null;
    }

    /**
     * Obtain layout
     * @return Layout id or View
     */
    protected abstract  Object obtainLayout();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
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
