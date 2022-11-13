package com.ziven.easygo.process;

import android.os.Parcelable;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;

/**
 * @author zhiyuan.zhou
 */
public interface ProcessReceiver {
    /**
     * Receive Message:String
     * @param what What
     * @param message Message
     */
    @MainThread
    default void receiveString(int what, @NonNull String message) {}
    /**
     * Receive Message:boolean
     * @param what What
     * @param message Message
     */
    @MainThread
    default void receiveBoolean(int what, boolean message) {}
    /**
     * Receive Message:int
     * @param what What
     * @param message Message
     */
    @MainThread
    default void receiveInt(int what, int message) {}
    /**
     * Receive Message:long
     * @param what What
     * @param message Message
     */
    @MainThread
    default void receiveLong(int what, long message) {}
    /**
     * Receive Message:float
     * @param what What
     * @param message Message
     */
    @MainThread
    default void receiveFloat(int what, float message) {}
    /**
     * Receive Message:double
     * @param what What
     * @param message Message
     */
    @MainThread
    default void receiveDouble(int what, double message) {}
    /**
     * Receive Message:Parcelable
     * @param what What
     * @param message Message
     */
    @MainThread
    default void receiveParcelable(int what, @NonNull Parcelable message) {}
    /**
     * Receive Message:nothing
     * @param what What
     */
    @MainThread
    default void notifyDataChanged(int what) {}
}
