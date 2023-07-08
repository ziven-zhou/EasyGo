package com.example.myapplication.activity;

import com.example.myapplication.BaseTestActivity;
import com.ziven.easygo.annotation.EasyGoActivity;
import com.ziven.easygo.autowired.EasyGo;
import com.ziven.easygo.design.proxy.Call;
import com.ziven.easygo.design.proxy.Make;
import com.ziven.easygo.design.proxy.Proxy;
import com.ziven.easygo.design.proxy.Workshop;

/**
 * @author Ziven
 */
@EasyGoActivity(path = "DefaultTestActivity")
public class DefaultTestActivity extends BaseTestActivity {

    private ProxyInterface mProxy;
    private Call<String> mCall;

    @Override
    protected void initLayout2() {
        log("DefaultTestActivityTag"
                + " data=" + getIntent().getStringExtra("data")
                + " position=" + getIntent().getIntExtra("position", 0)
        );
        mProxy = Proxy.proxy(ProxyInterface.class);
        mCall = mProxy.obtain();
    }

    @Override
    public void condition(int condition) {
        log("DefaultTestActivityTag", "output="
                + mProxy.obtain(condition, "input").call());

        mCall.call(
                data -> log("DefaultTestActivityTag", "output=" + data)
        );
    }

    @Override
    public void condition1() {

    }

    @Override
    public void condition2() {

    }

    @Override
    public void condition3() {

    }

    @Override
    public void condition4() {

    }

    @Override
    public void condition5() {

    }

    @Override
    public void condition6() {

    }

    @Override
    public void condition7() {

    }

    @Override
    public void condition8() {

    }

    @Override
    public void condition9() {

    }

    @Override
    public void other() {

    }

    @Override
    protected void button2Click() {
        EasyGo.easyGo(TestViewModelActivity.class);
    }

    @Override
    protected void destroyLayout() {

    }

    interface ProxyInterface {
        @Make(workshop = ProxyWorkshop.class)
        Call<String> obtain();

        @Make(workshop = ProxyWorkshopP2.class)
        Call<String> obtain(int p1, String p2);
    }

    public static class ProxyWorkshop implements Workshop<String> {

        @Override
        public String make() {
            return "ProxyWorkshop2:" + Thread.currentThread();
        }
    }

    public static class ProxyWorkshopP2 implements Workshop.P2<String, Integer, String> {

        @Override
        public String make(Integer param1, String param2) {
            return param1 + ":" + param2 + " :" + Thread.currentThread();
        }
    }
}
