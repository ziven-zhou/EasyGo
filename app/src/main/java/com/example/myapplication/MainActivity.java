package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.ziven.easygo.EasyGos;
import com.ziven.easygo.annotation.EasyGoMethod;
import com.ziven.easygo.ui.AbstractOneDataFragment;
import com.ziven.easygo.util.LogHelper;

/**
 * @author Ziven
 */
public class MainActivity extends AppCompatActivity {

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
                .setView(data -> LogHelper.of("OverallDispatch").always().join(data).print())
                .getPresenter()
                .obtainOneData();

        binding.test2.setOnClickListener(v -> {
            if(testFragment != null && testFragment.isAdded()) {
                return;
            }
            testFragment = AbstractOneDataFragment.newInstance(TestFragment.class);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.test_container, testFragment)
                    .commitNow();
        });
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
