package com.ziven.easygo.process.client;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Messenger;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import com.ziven.easygo.process.ProcessCommunication;

/**
 * @author Ziven
 */
@Keep
public class ClientConnection implements ServiceConnection {

    private final Messenger messenger;
    private final ClientHandler client;

    public ClientConnection(@NonNull ClientHandler client) {
        this.client = client;
        this.messenger = new Messenger(client);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        ProcessCommunication.log("onServiceConnected:", name);
        client.setSender(new Messenger(service));
        client.getProcessSender().sendParcelable(ProcessCommunication.WHAT_MESSENGER, messenger);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        ProcessCommunication.log("onServiceDisconnected:", name);
        client.setSender(null);
    }
}
