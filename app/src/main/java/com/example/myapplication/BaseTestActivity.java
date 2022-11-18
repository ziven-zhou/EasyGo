package com.example.myapplication;

import com.ziven.easygo.ui.AbstractBaseActivity;

import com.ziven.easygo.util.Conditions;
import com.ziven.easygo.util.EasyUtils;
import com.ziven.easygo.util.LogHelper;

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

        getView(R.id.button2)
                .setOnClickListener(
                        v -> button2Click());

        initLayout2();
    }

    protected void button2Click() {}

    /**
     * Init Layout2
     */
    protected abstract void initLayout2();

    protected void log(Object... logs) {
        LogHelper helper = LogHelper.of("BaseTestActivityTag");
        EasyUtils.forEach(logs, helper::join);
        helper.print();
    }
}
