package com.example.myapplication;

import android.content.Intent;

import androidx.multidex.MultiDexApplication;

import com.ziven.easygo.EasyGos;
import com.ziven.easygo.design.mvvm.ModelViewUtil;
import com.ziven.easygo.overall.IOverall;
import com.ziven.easygo.simply.EasyGoReceiver.IAction;

/**
 * @author :zhiyuan.zhou
 * @date :2019/10/8
 */
public class MyApplication extends MultiDexApplication
        implements IOverall<OverallDispatch>, IAction {

    public static final String ACTION = "com.ziven.easygo.action";
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

    @Override
    public String[] actions() {
        return new String[] {
                Intent.ACTION_SCREEN_OFF,
                Intent.ACTION_SCREEN_ON,
                ACTION
        };
    }

    static {
        ModelViewUtil.put("test", TestViewModel.class);
    }
}
