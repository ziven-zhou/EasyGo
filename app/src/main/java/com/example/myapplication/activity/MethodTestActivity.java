package com.example.myapplication.activity;

import com.example.myapplication.BaseTestActivity;
import com.ziven.easygo.annotation.EasyGoActivity;
import com.ziven.easygo.annotation.EasyGoMethod;
import com.ziven.easygo.autowired.EasyGo;
import com.ziven.easygo.util.LogHelper;

/**
 * @author Ziven
 */
@EasyGoActivity(path = "MethodTestActivity")
public class MethodTestActivity extends BaseTestActivity {

    @Override
    protected void initLayout2() {
        EasyGo.get().register(this);
    }

    @Override
    public void condition(int condition) {
        LogHelper.of("MethodTestActivityTag").join(condition).print();
        EasyGo.get().easyGo(EasyGo.Transition
                .of("testMethod")
                .param("Send message")
                .carry(msg -> LogHelper.of("MethodTestActivityTag").join(msg).print()));
    }

    @EasyGoMethod(path = "testMethod")
    public String testMethod(String param) {
        LogHelper.of("MethodTestActivityTag").join(param).print();
        return "Get message";
    }

    @Override
    protected void destroyLayout() {
        EasyGo.get().unregister(this);
    }
}
