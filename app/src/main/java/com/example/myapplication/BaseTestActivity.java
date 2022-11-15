package com.example.myapplication;

import com.ziven.easygo.ui.AbstractBaseActivity;

import com.ziven.easygo.util.Conditions;
import com.ziven.easygo.util.EasyUtils;

/**
 * @author Ziven
 */
public abstract class BaseTestActivity extends AbstractBaseActivity implements Conditions {

    private int clickCount = 0;

    @Override
    protected Object obtainLayout() {
        return R.layout.activity_base_test;
    }

    @Override
    protected void initLayout() {
        setTitle(getIntent().getStringExtra("title_name"));

        getView(R.id.button)
                .setOnClickListener(
                        v -> EasyUtils.conditions(this, clickCount++));

        initLayout2();
    }

    /**
     * Init Layout2
     */
    protected abstract void initLayout2();
}
