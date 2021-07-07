package com.example.testLibrary;

import android.content.Context;

import androidx.annotation.Nullable;

import com.ziven.easygo.design.mvp.OneData;
import com.ziven.easygo.design.mvp.OneModel;
import com.ziven.easygo.util.EasyUtils;
import com.ziven.easygo.util.LogHelper;
import com.ziven.easygo.util.ThreadUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author :zhiyuan.zhou
 * @date :2019/10/12
 */
public class TestModel extends OneModel {

    @Override
    protected void obtainOneData(@Nullable Context c, @Nullable Map<Object, Object> params) {

        final boolean isSingle = params != null && params.containsValue("single");
        final List<TestBean> list = new ArrayList<>();
        ThreadUtils.ThreadEasyGo.of()
                .workerThread(r -> r + ",I has worked!")
                .workerThread(r -> r + ",I has worked!")
                .workerThread(r -> r + ",I has worked!")
                .workerThread(r -> r + ",I has worked!")
                .workerThread(r -> r + ",I has worked!")
                .parallelMain(new ThreadUtils.MultipleTransfer() {
                    @Override
                    public void singleTransfer(int position, Object value) {
                        if(isSingle) {
                            LogHelper.of("TestModelTag").always().join(position).join(value).print();
                            list.add(TestBean.of(EasyUtils.transition(value)));
                            if(list.size() == 5) {
                                obtainedOneData(OneData.of().setOneData(list));
                            }
                        }
                    }

                    @Override
                    public void multipleTransfer(Object... values) {
                        if(!isSingle) {
                            List<TestBean> list = new ArrayList<>();
                            list.add(TestBean.of(EasyUtils.transition(values[0])));
                            list.add(TestBean.of(EasyUtils.transition(values[1])));
                            list.add(TestBean.of(EasyUtils.transition(values[2])));
                            list.add(TestBean.of(EasyUtils.transition(values[3])));
                            list.add(TestBean.of(EasyUtils.transition(values[4])));
                            obtainedOneData(OneData.of().setOneData(list));
                            LogHelper.of("TestModelTag").always().join(list).print();
                        }
                    }
                }, "I am 1", "I am 2", "I am 3", "I am 4", "I am 5");
    }
}
