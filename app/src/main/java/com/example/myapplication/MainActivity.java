package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.ziven.easygo.EasyGos;
import com.ziven.easygo.annotation.EasyGoMethod;
import com.ziven.easygo.process.ProcessCommunication;
import com.ziven.easygo.process.ProcessReceiver;
import com.ziven.easygo.simply.EasyGoReceiver;
import com.ziven.easygo.ui.AbstractOneDataFragment;
import com.ziven.easygo.util.LogHelper;

/**
 * @author Ziven
 */
public class MainActivity extends AppCompatActivity implements EasyGoReceiver.IReceiver {

    ActivityMainBinding binding;
    TestFragment testFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.test.setOnClickListener(v -> EasyGos.getEasyGo().easyGo(this, "com.example.myapplication.TestActivity"));
        EasyGos.getEasyGo().register(this);

        EasyGos.getDispatch()
                .setView(data -> LogHelper.of("OverallDispatch").join(data).print())
                .getPresenter()
                .obtainOneData();

        EasyGos.getEasyGoReceiver()
                .setAction(() -> new String[] {
                        Intent.ACTION_SCREEN_OFF,
                        Intent.ACTION_SCREEN_ON,
                })
                .setReceiverReturnThis(this, Intent.ACTION_SCREEN_OFF)
                .register(this);

        binding.test2.setOnClickListener(v -> {
            if(testFragment != null && testFragment.isAdded()) {
                return;
            }
            testFragment = AbstractOneDataFragment.newInstance(TestFragment.class);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.test_container, testFragment)
                    .commitNow();

            ProcessCommunication.clientSender().sendString("I am client.");
            ProcessCommunication.serverSender().sendString("I am server.");
        });

        ProcessCommunication.server(new ProcessReceiver() {
            @Override
            public void receiveString(int what, @NonNull String message) {
                ProcessCommunication.log("server receiveString:" + message);
            }
        });

        ProcessCommunication.client(new ProcessReceiver() {
            @Override
            public void receiveString(int what, @NonNull String message) {
                ProcessCommunication.log("client receiveString:" + message);
            }
        });
    }

    @EasyGoMethod(path = "com.example.myapplication.MainActivity.testMethod")
    public String testMethod(String param) {
        binding.test.setText(param);
        return "MainActivity get message";
    }

    @Override
    public void received(Context context, Intent intent, String action) {
        LogHelper.of("EasyGoReceiverTag").join("received:").join(action).print();
    }

    @Override
    protected void onDestroy() {
        EasyGos.getEasyGo().unregister(this);
        EasyGos.getEasyGoReceiver().removeReceiver(this);
        EasyGos.getEasyGoReceiver().unregister(this);
        ProcessCommunication.unbindService();
        super.onDestroy();
    }
}
