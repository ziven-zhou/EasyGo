package com.example.myapplication.activity;

import com.example.myapplication.BaseTestActivity;
import com.ziven.easygo.annotation.EasyGoActivity;
import com.ziven.easygo.autowired.EasyGo;

/**
 * @author Ziven
 */
@EasyGoActivity(path = "DefaultTestActivity")
public class DefaultTestActivity extends BaseTestActivity {

    @Override
    protected void initLayout2() {
        log("DefaultTestActivityTag"
                + " data=" + getIntent().getStringExtra("data")
                + " position=" + getIntent().getIntExtra("position", 0)
        );
    }

    @Override
    protected void button2Click() {
        EasyGo.easyGo(TestViewModelActivity.class);
    }

    @Override
    protected void destroyLayout() {

    }
}
