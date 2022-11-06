package com.example.myapplication;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ziven.easygo.design.mvp.AbstractOneData;
import com.ziven.easygo.design.mvp.OneData;
import com.ziven.easygo.design.mvp.OneModel;
import com.ziven.easygo.ui.AbstractOneDataFragment;
import com.ziven.easygo.util.LogHelper;

import java.util.Map;

/**
 * @author Ziven
 */
public class TestFragment extends AbstractOneDataFragment {

    @Override
    public void layoutOneData(@NonNull AbstractOneData data) {
        getViewHelper(R.id.test).setText(data.getOneData());
    }

    @NonNull
    @Override
    protected Class<? extends OneModel> obtainOneModelClass() {
        return TestFragmentModel.class;
    }

    @Override
    protected boolean findLayout() {
        return true;
    }

    @Override
    protected Object obtainLayout() {
        return R.layout.fragment_test;
    }

    @Override
    protected void createdView(@NonNull View root) {
        getViewHelper(R.id.test).setOnClickListener(v
                -> getOnePresenter().obtainOneData());
    }

    @Override
    protected void destroyView() {

    }

    public static class TestFragmentModel extends OneModel {

        private int count = 0;

        @Override
        protected void obtainOneData(@Nullable Context c, @Nullable Map<Object, Object> params) {
            LogHelper.of("TestFragmentTag").join("Obtain data").print();
            obtainedOneData(OneData.of("Obtained data:" + count++));
        }
    }
}
