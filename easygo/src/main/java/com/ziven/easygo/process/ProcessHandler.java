package com.ziven.easygo.process;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ziven.easygo.util.EasyUtils;
import com.ziven.easygo.util.TypeConditions;

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
        if(!dispatchMessage(msg.what, msg.obj)) {
            dispatchOtherMessage(msg.what, msg.obj);
        }
    }

    /**
     * Dispatch Message
     * @param what What
     * @param message Message
     * @return false do dispatchMessage(int what, Object message)
     */
    protected abstract boolean dispatchMessage(int what, @NonNull Object message);

    private void dispatchOtherMessage(int what, @NonNull Object message) {
        if(what == ProcessCommunication.WHAT_NOTIFY) {
            receiver.notifyDataChanged((int) message);
        } else {
            EasyUtils.typeConditions(new TypeConditions() {
                @Override
                public void condition1(@NonNull String value) {
                    receiver.receiveString(what, value);
                }

                @Override
                public void condition2(boolean value) {
                    receiver.receiveBoolean(what, value);
                }

                @Override
                public void condition3(int value) {
                    receiver.receiveInt(what, value);
                }

                @Override
                public void condition4(long value) {
                    receiver.receiveLong(what, value);
                }

                @Override
                public void condition5(float value) {
                    receiver.receiveFloat(what, value);
                }

                @Override
                public void condition6(double value) {
                    receiver.receiveDouble(what, value);
                }

                @Override
                public void other(Object value) {
                    receiver.receiveParcelable(what, (Parcelable) value);
                }
            }, message);
        }
    }
}
