package com.ziven.easygo.process;

import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ziven.easygo.util.Nulls;

/**
 * @author Ziven
 */
public class ProcessReceiverImpl implements ProcessReceiver {

    private ProcessReceiver receiver;

    public void setProcessReceiver(@Nullable ProcessReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void receiveString(int what, @NonNull String message) {
        Nulls.of(receiver)
                .doNotNull(r -> r.receiveString(what, message));
    }

    @Override
    public void receiveBoolean(int what, boolean message) {
        Nulls.of(receiver)
                .doNotNull(r -> r.receiveBoolean(what, message));
    }

    @Override
    public void receiveInt(int what, int message) {
        Nulls.of(receiver)
                .doNotNull(r -> r.receiveInt(what, message));
    }

    @Override
    public void receiveLong(int what, long message) {
        Nulls.of(receiver)
                .doNotNull(r -> r.receiveLong(what, message));
    }

    @Override
    public void receiveFloat(int what, float message) {
        Nulls.of(receiver)
                .doNotNull(r -> r.receiveFloat(what, message));
    }

    @Override
    public void receiveDouble(int what, double message) {
        Nulls.of(receiver)
                .doNotNull(r -> r.receiveDouble(what, message));
    }

    @Override
    public void receiveParcelable(int what, @NonNull Parcelable message) {
        Nulls.of(receiver)
                .doNotNull(r -> r.receiveParcelable(what, message));
    }

    @Override
    public void notifyDataChanged(int what) {
        Nulls.of(receiver)
                .doNotNull(r -> r.notifyDataChanged(what));
    }
}
