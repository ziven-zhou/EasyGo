package com.ziven.easygo.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ziven.easygo.design.mvp.IOneView;
import com.ziven.easygo.design.mvp.OneModel;
import com.ziven.easygo.design.mvp.OnePresenter;

/**
 * @author zhiyuan.zhou
 */
public abstract class AbstractOneDataFragment extends Fragment implements IOneView {

    private OnePresenter mOnePresenter;

    public AbstractOneDataFragment(int contentLayoutId) {
        super(contentLayoutId);
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
