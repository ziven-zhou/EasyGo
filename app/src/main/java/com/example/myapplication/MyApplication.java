package com.example.myapplication;

import androidx.multidex.MultiDexApplication;

import com.ziven.easygo.EasyGos;

/**
 * @author :zhiyuan.zhou
 * @date :2019/10/8
 */
public class MyApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        EasyGos.initLog(this);
    }
}
