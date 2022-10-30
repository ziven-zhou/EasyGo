package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ziven.easygo.EasyGos;
import com.ziven.easygo.annotation.EasyGoActivity;
import com.ziven.easygo.autowired.EasyGo;
import com.ziven.easygo.design.mvp.AbstractModel;
import com.ziven.easygo.design.mvp.AbstractOneData;
import com.ziven.easygo.ui.AbstractOneDataActivity;
import com.ziven.easygo.util.EasyUtils;

/**
 * @author Ziven
 */
@EasyGoActivity(path = "com.example.myapplication.TestActivity")
public class TestActivity extends AbstractOneDataActivity {

    private TestAdapter testAdapter;

    @Override
    protected Object obtainLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected void initLayout() {
        testAdapter = new TestAdapter()
                .setListener((data, position) -> EasyGos.getEasyGo()
                        .easyGo(EasyGo.Transition
                                .of("com.example.myapplication.MainActivity.testMethod")
                                .param("TestActivity send message:" + position)
                                .carry(msg -> {
                                    testAdapter
                                            .getDataProvider()
                                            .replace(msg + ":" + position, position);
                                    EasyUtils.notifyDataSetChanged(testAdapter);
                                })));

        RecyclerView rv = getView(R.id.recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(testAdapter);
        getOnePresenter().obtainOneData();
    }

    @Override
    protected void destroyLayout() {

    }

    @Override
    public void layoutOneData(@NonNull AbstractOneData data) {
        testAdapter.getDataProvider().setList(data.getOneData());
        EasyUtils.notifyDataSetChanged(testAdapter);
    }

    @Override
    protected Class<? extends AbstractModel<?>> obtainOneModelClass() {
        return TestModel.class;
    }
}
