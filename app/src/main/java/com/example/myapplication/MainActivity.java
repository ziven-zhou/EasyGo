package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.ziven.easygo.EasyGos;
import com.ziven.easygo.annotation.EasyGoMethod;

/**
 * @author Ziven
 */
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.test.setOnClickListener(v -> EasyGos.getEasyGo().easyGo(this, "com.example.myapplication.TestActivity"));
        EasyGos.getEasyGo().register(this);
    }

    @EasyGoMethod(path = "com.example.myapplication.MainActivity.testMethod")
    public String testMethod(String param) {
        binding.test.setText(param);
        return "MainActivity get message";
    }

    @Override
    protected void onDestroy() {
        EasyGos.getEasyGo().unregister(this);
        super.onDestroy();
    }
}
