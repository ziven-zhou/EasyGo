package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.ziven.easygo.annotation.EasyGoMethod;
import com.ziven.easygo.autowired.EasyGo;
import com.ziven.easygo.simply.EasyGoReceiver;
import com.ziven.easygo.util.LogHelper;

public class MainActivity extends AppCompatActivity implements EasyGoReceiver.IAction {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EasyGoReceiver.get().register(this);
        EasyGo.get().register(this);
        binding.test.setOnClickListener(view ->
                EasyGo.get().easyGo(MainActivity.this.getApplicationContext(), "LoginActivity"));


        EasyGoReceiver.IReceiver receiver = new EasyGoReceiver.IReceiver() {
            @Override
            public void received(Context context, Intent intent, String action) {
                LogHelper.of("TestActivityTestReceiver, main").always().join(action).print();
            }
        };

        EasyGoReceiver.get().setReceiver(receiver, Intent.ACTION_SCREEN_OFF);
    }

    @EasyGoMethod(path = "MainActivity/testEasyGo")
    public String testEasyGo(String param) {
        LogHelper.of("TestActivityTag").always().join("MainActivity/testEasyGo").join(param).print();
        return "I am MainActivity/testEasyGo.";
    }

    @Override
    protected void onDestroy() {
        EasyGoReceiver.get().unregister(this);
        EasyGo.get().register(this);
        super.onDestroy();
    }

    @Override
    public String[] actions() {
        return new String[] {Intent.ACTION_SCREEN_ON, Intent.ACTION_SCREEN_OFF};
    }
}
