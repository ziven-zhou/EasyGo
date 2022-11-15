package com.example.myapplication;

import com.ziven.easygo.ui.AbstractBaseActivity;
import com.ziven.easygo.ui.AbstractOneDataFragment;

/**
 * @author Ziven
 */
public class TestActivity extends AbstractBaseActivity {

    @Override
    protected Object obtainLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected void initLayout() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.test_container, AbstractOneDataFragment.newInstance(TestFragment.class))
                .commitNow();
    }

    @Override
    protected void destroyLayout() {

    }
}
