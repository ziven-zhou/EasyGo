package com.example.myapplication.activity;

import android.content.Context;
import android.content.Intent;

import com.example.myapplication.BaseTestActivity;
import com.ziven.easygo.EasyGos;
import com.ziven.easygo.annotation.EasyGoActivity;
import com.ziven.easygo.simply.EasyGoReceiver;
import com.ziven.easygo.util.LogHelper;

/**
 * @author Ziven
 */
@EasyGoActivity(path = "ReceiverTestActivity")
public class ReceiverTestActivity extends BaseTestActivity implements EasyGoReceiver.IReceiver {

    @Override
    protected void initLayout2() {
        EasyGos.getEasyGoReceiver()
                .setReceiverReturnThis(this, Intent.ACTION_SCREEN_OFF);
    }

    @Override
    public void received(Context context, Intent intent, String action) {
        LogHelper.of("EasyGoReceiverTag").join("received:").join(action).print();
    }

    @Override
    protected void destroyLayout() {
        EasyGos.getEasyGoReceiver().unregister(this);
    }
}
