package com.ziven.easygo.process.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Messenger;

import androidx.annotation.Nullable;

import com.ziven.easygo.process.ProcessCommunication;

/**
 * @author zhiyuan.zhou
 */
public class ServerService extends Service {

    private Messenger messenger;

    @Nullable
    private IBinder getBinder() {
        if(messenger == null) {
            ProcessCommunication
                    .serverHandler()
                    .doNotNull(handler -> messenger = new Messenger(handler));
        }
        return messenger != null ? messenger.getBinder() : null;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        ProcessCommunication.log("onBind:" + intent);
        return getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        ProcessCommunication.log("onUnbind:" + intent);
        ProcessCommunication
                .serverHandler()
                .doNotNull(handler -> handler.setSender(null));
        return super.onUnbind(intent);
    }
}
