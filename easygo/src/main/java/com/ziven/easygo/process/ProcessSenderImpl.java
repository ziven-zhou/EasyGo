package com.ziven.easygo.process;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author Ziven
 */
public class ProcessSenderImpl implements ProcessSender {

    private Messenger sender;

    public void setSender(@Nullable Messenger sender) {
        this.sender = sender;
    }

    @NonNull
    private Message obtainMessage(int what, @NonNull Parcelable message) {
        Message msg = Message.obtain();
        msg.what = what;
        Bundle bundle = new Bundle();
        bundle.putParcelable(ProcessCommunication.KEY, message);
        msg.setData(bundle);
        return msg;
    }

    /**
     * Send Message
     * @param what What
     * @param message Message
     */
    private void sendMessage(int what, @NonNull Parcelable message) {
        if(sender == null) {
            ProcessCommunication.log("messenger is null.");
            return;
        }
        ProcessCommunication.log("send message: what=" + what, "message=" + message);
        try {
            sender.send(obtainMessage(what, message));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendString(int what, @NonNull String message) {
        sendMessage(what, new ProcessParcelable(ProcessCommunication.WHAT_STRING).setMessage(message));
    }

    @Override
    public void sendBoolean(int what, boolean message) {
        sendMessage(what, new ProcessParcelable(ProcessCommunication.WHAT_BOOLEAN).setMessage(message));
    }

    @Override
    public void sendInt(int what, int message) {
        sendMessage(what, new ProcessParcelable(ProcessCommunication.WHAT_INT).setMessage(message));
    }

    @Override
    public void sendLong(int what, long message) {
        sendMessage(what, new ProcessParcelable(ProcessCommunication.WHAT_LONG).setMessage(message));
    }

    @Override
    public void sendFloat(int what, float message) {
        sendMessage(what, new ProcessParcelable(ProcessCommunication.WHAT_FLOAT).setMessage(message));
    }

    @Override
    public void sendDouble(int what, double message) {
        sendMessage(what, new ProcessParcelable(ProcessCommunication.WHAT_DOUBLE).setMessage(message));
    }

    @Override
    public void sendParcelable(int what, @NonNull Parcelable message) {
        sendMessage(what, message);
    }
}
