package com.example.myapplication;

import androidx.multidex.MultiDexApplication;

import com.ziven.easygo.util.LogHelper;
import com.ziven.easygo.util.ResourceUtils;

/**
 * @author :zhiyuan.zhou
 * @date :2019/10/8
 */
public class MyApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ResourceUtils.init(this);
        LogHelper.setLog(true);
    }
}
