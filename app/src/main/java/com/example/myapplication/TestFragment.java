package com.example.myapplication;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ziven.easygo.EasyGos;
import com.ziven.easygo.design.mvp.AbstractOneData;
import com.ziven.easygo.design.mvp.OneModel;
import com.ziven.easygo.ui.AbstractOneDataFragment;
import com.ziven.easygo.util.EasyUtils;
import com.ziven.easygo.util.LogHelper;

/**
 * @author Ziven
 */
public class TestFragment extends AbstractOneDataFragment {

    private final TestAdapter testAdapter = new TestAdapter();

    @Override
    public void layoutOneData(@NonNull AbstractOneData data) {
        testAdapter
                .getDataProvider()
                .setList(data.getOneData());

        EasyUtils.notifyDataSetChanged(testAdapter);
    }

    @NonNull
    @Override
    protected Class<? extends OneModel> obtainOneModelClass() {
        return TestModel.class;
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
        testAdapter.setListener((data, position) -> {
            LogHelper.of("TestFragmentTag")
                    .join("position=" + position)
                    .join("data=" + data);

            EasyGos.getDispatch()
                    .setView(value -> EasyGos.getEasyGo()
                            .easyGo(root.getContext(),
                                    (String) value.getOneData(),
                                    intent -> intent.putExtra("title_name", (String) value.getOneData())))
                    .getPresenter()
                    .setParam(OverallDispatch.ACTIVITY_PATH_TAG, position)
                    .obtainOneData();
        });

        getViewHelper(R.id.recycler_view)
                .setLayoutManager(new LinearLayoutManager(root.getContext()))
                .setAdapter(testAdapter);
        getOnePresenter().obtainOneData();
    }

    @Override
    protected void destroyView() {

    }
}
