package com.example.myapplication;

import androidx.multidex.MultiDexApplication;

import com.ziven.easygo.EasyGos;
import com.ziven.easygo.overall.IOverall;

/**
 * @author :zhiyuan.zhou
 * @date :2019/10/8
 */
public class MyApplication extends MultiDexApplication implements IOverall<OverallDispatch> {

    private final OverallDispatch dispatch = new OverallDispatch();

    @Override
    public void onCreate() {
        super.onCreate();
        EasyGos.initLog(this);
    }

    @Override
    public OverallDispatch getDispatch() {
        return dispatch;
    }
}
