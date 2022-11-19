package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.ziven.easygo.EasyGos;
import com.ziven.easygo.autowired.EasyGo;

/**
 * @author Ziven
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.test.setOnClickListener(
                v -> EasyGo.easyGo(v.getContext(), TestActivity.class));

        EasyGos.getEasyGoReceiver().register();
    }

    @Override
    protected void onDestroy() {
        EasyGos.getEasyGoReceiver().unregister();
        super.onDestroy();
    }
}
