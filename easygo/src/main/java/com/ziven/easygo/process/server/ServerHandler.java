package com.ziven.easygo.process.server;

import android.os.Messenger;

import androidx.annotation.NonNull;

import com.ziven.easygo.process.ProcessCommunication;
import com.ziven.easygo.process.ProcessHandler;

/**
 * @author Ziven
 */
public class ServerHandler extends ProcessHandler {

    @Override
    protected boolean dispatchMessage(int what, @NonNull Object message) {
        if(what == ProcessCommunication.WHAT_MESSENGER) {
            setSender((Messenger) message);
            return true;
        }
        return false;
    }
}
