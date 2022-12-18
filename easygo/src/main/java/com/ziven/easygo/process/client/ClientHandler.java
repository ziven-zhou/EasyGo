package com.ziven.easygo.process.client;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import com.ziven.easygo.process.ProcessHandler;
import com.ziven.easygo.util.ResourceUtils;

/**
 * @author Ziven
 */
@Keep
public class ClientHandler extends ProcessHandler {

    private final ClientConnection connection;

    public ClientHandler() {
        super();
        connection = new ClientConnection(this);
    }

    public void bindService(@NonNull Intent intent) {
        bindService(intent, Context.BIND_AUTO_CREATE);
    }

    public void bindService(@NonNull Intent intent, int flags) {
        ResourceUtils
                .getContext()
                .bindService(intent, connection, flags);
    }

    public void unbindService() {
        ResourceUtils
                .getContext()
                .unbindService(connection);
    }

    @Override
    protected boolean dispatchMessage(int what, @NonNull Object message) {
        return false;
    }
}
