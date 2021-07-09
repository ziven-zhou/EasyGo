package com.ziven.easygo.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ziven.easygo.design.mvp.AbstractModel;
import com.ziven.easygo.design.mvp.AbstractPresenter;
import com.ziven.easygo.design.mvp.IOneView;
import com.ziven.easygo.design.mvp.OnePresenter;

/**
 * @author Ziven
 * @date 2021/5/29
 */
public abstract class AbstractOneDataActivity extends AbstractBaseActivity implements IOneView {

    private OnePresenter mOnePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mOnePresenter = AbstractPresenter.of(OnePresenter.class, obtainOneModelClass()).setView(this);
        super.onCreate(savedInstanceState);
    }

    /**
     * obtain OneModel
     * @return Class of OneModel
     */
    protected abstract Class<? extends AbstractModel<?>> obtainOneModelClass();

    protected OnePresenter getOnePresenter() {
        return mOnePresenter;
    }

    @Override
    protected void onDestroy() {
        mOnePresenter.onDestroy();
        super.onDestroy();
    }
}
