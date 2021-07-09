package com.example.testLibrary;

import android.content.Intent;
import android.view.ViewGroup;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ziven.easygo.annotation.EasyGoActivity;
import com.ziven.easygo.autowired.EasyGo;
import com.ziven.easygo.design.funnel.FilterHelper;
import com.ziven.easygo.design.mvp.AbstractModel;
import com.ziven.easygo.design.mvp.AbstractOneData;
import com.ziven.easygo.simply.EasyGoReceiver;
import com.ziven.easygo.ui.AbstractEasyAdapter;
import com.ziven.easygo.ui.AbstractOneDataActivity;
import com.ziven.easygo.ui.AbstractEasyViewHolder;
import com.ziven.easygo.util.EasyUtils;
import com.ziven.easygo.util.LogHelper;
import com.ziven.easygo.util.Nulls;
import com.ziven.easygo.util.ThreadUtils;
import com.ziven.easygo.util.TimeHelper;

import java.util.List;

/**
 * @author :zhiyuan.zhou
 * @date :2019/10/12
 */
@EasyGoActivity(path = "LoginActivity")
public class TestActivity extends AbstractOneDataActivity {

    private TestAdapter mTestAdapter;

    @Override
    protected Object obtainLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected void initLayout() {
        EasyGoReceiver.get().setReceiver((context, intent, action) -> {
            LogHelper.of("TestActivityTestReceiver Test").always().join(action).print();
        }, Intent.ACTION_SCREEN_ON, Intent.ACTION_SCREEN_OFF);
        EasyGo.get().register(this);

        FilterHelper.get()
                .withEmpty(this)
                .add(grain -> {
                    List<TestBean> list = grain.getGain();
                    Nulls.of(list).doNotNull(l -> {
                        EasyUtils.forEach(l, bean -> LogHelper.of("TestActivityTag").always().join(bean).print());
                        l.add(TestBean.of("I am New"));
                    });
                });

        getView(R.id.test).setOnClickListener(v -> getOnePresenter().runOnWorkThread().obtainOneData());
        getView(R.id.test2).setOnClickListener(v -> getOnePresenter().setParam("single").runOnWorkThread().obtainOneData());

        mTestAdapter = new TestAdapter();
        RecyclerView recyclerView = getView(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mTestAdapter);
    }

    private static class TestAdapter extends AbstractEasyAdapter<TestBean, TestHolder> {

        @NonNull
        @Override
        public TestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new TestHolder(parent, R.layout.item_test);
        }
    }

    private static class TestHolder extends AbstractEasyViewHolder<TestBean> {

        public TestHolder(@NonNull ViewGroup parent, int layoutId) {
            super(parent, layoutId);
        }

        @Override
        protected void bindLayout(TestBean data, int position) {
            LogHelper.of("TestActivityTag").always().join("bindLayout::").join(data).print();
            setText(R.id.title, data.getTitle());
            setListener(R.id.parent, v -> {
                if(TimeHelper.doubleClick()) {
                    return;
                }

                ThreadUtils.timesInterval(5, 1000, t -> LogHelper.of("TestTimesInterval").always().join("I am:").join(t).print());

                LogHelper.of("TimeHelperTagTest").always().join(TimeHelper.obtainTodayStart()).join(TimeHelper.withDefFormat(TimeHelper.obtainTodayStart())).join(TimeHelper.obtainTodayEnd()).join(TimeHelper.withDefFormat(TimeHelper.obtainTodayEnd())).join(TimeHelper.isNewDay("123456")).print();
                EasyGo.get().easyGo(EasyGo.Transition.of("MainActivity/testEasyGo")
                        .param(data.getTitle()).carry(r -> LogHelper.of("TestActivityTag").always().join(r).print()));
            });
        }
    }

    @Override
    protected Class<? extends AbstractModel<?>> obtainOneModelClass() {
        return TestModel.class;
    }

    @Override
    protected void destroyLayout() {
        EasyGo.get().unregister(this);
        FilterHelper.get().clearFilter(this);
    }

    @MainThread
    @Override
    public void layoutOneData(@NonNull AbstractOneData data) {
        FilterHelper.get().filterValue(this, data.getOneData());
        mTestAdapter.getDataProvider().setList(EasyUtils.transition(data.getOneData()));
        mTestAdapter.notifyDataSetChanged();
    }
}
