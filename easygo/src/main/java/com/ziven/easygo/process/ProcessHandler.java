package com.ziven.easygo.process;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author Ziver
 */
public abstract class ProcessHandler extends Handler {

    private final ProcessSenderImpl sender;
    private final ProcessReceiverImpl receiver;

    public ProcessHandler() {
        super(Looper.getMainLooper());
        sender = new ProcessSenderImpl();
        receiver = new ProcessReceiverImpl();
    }

    @NonNull
    public ProcessSenderImpl getProcessSender() {
        return sender;
    }

    public void setSender(@Nullable Messenger sender) {
        this.sender.setSender(sender);
        if(sender == null) {
            setProcessReceiver(null);
        }
    }

    public void setProcessReceiver(@Nullable ProcessReceiver receiver) {
        this.receiver.setProcessReceiver(receiver);
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        ProcessCommunication.log("receive message: what=" + msg.what, "message=" + msg.obj);
        Bundle bundle = msg.getData();
        bundle.setClassLoader(ProcessParcelable.class.getClassLoader());
        Parcelable parcelable = bundle.getParcelable(ProcessCommunication.KEY);
        if(dispatchMessage(msg.what, parcelable)) {
            return;
        }
        if(parcelable instanceof ProcessParcelable) {
            dispatchOtherMessage(msg.what, (ProcessParcelable) parcelable);
        } else {
            receiver.receiveParcelable(msg.what, parcelable);
        }
    }

    /**
     * Dispatch Message
     * @param what What
     * @param message Message
     * @return false do dispatchMessage(int what, Object message)
     */
    protected abstract boolean dispatchMessage(int what, @NonNull Object message);

    private void dispatchOtherMessage(int what, @NonNull ProcessParcelable message) {
        if(what == ProcessCommunication.WHAT_NOTIFY) {
            receiver.notifyDataChanged(message.getIntMessage());
            return;
        }
        switch (message.getWhat()) {
            case ProcessCommunication.WHAT_STRING:
                receiver.receiveString(what, message.getStringMessage());
                break;
            case ProcessCommunication.WHAT_BOOLEAN:
                receiver.receiveBoolean(what, message.isBooleanMessage());
                break;
            case ProcessCommunication.WHAT_INT:
                receiver.receiveInt(what, message.getIntMessage());
                break;
            case ProcessCommunication.WHAT_LONG:
                receiver.receiveLong(what, message.getLongMessage());
                break;
            case ProcessCommunication.WHAT_FLOAT:
                receiver.receiveFloat(what, message.getFloatMessage());
                break;
            case ProcessCommunication.WHAT_DOUBLE:
                receiver.receiveDouble(what, message.getDoubleMessage());
                break;
            default:
                break;
        }
    }
}
