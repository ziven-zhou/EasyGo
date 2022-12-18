package com.ziven.easygo.process;

import android.os.Parcelable;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

/**
 * @author zhiyuan.zhou
 */
@Keep
public interface ProcessSender {

    /**
     * Send Message:String
     * @param message Message
     */
    default void sendString(@NonNull String message) {
        sendString(ProcessCommunication.WHAT_STRING, message);
    }
    /**
     * Send Message:boolean
     * @param message Message
     */
    default void sendBoolean(boolean message) {
        sendBoolean(ProcessCommunication.WHAT_BOOLEAN, message);
    }
    /**
     * Send Message:int
     * @param message Message
     */
    default void sendInt(int message) {
        sendInt(ProcessCommunication.WHAT_INT, message);
    }
    /**
     * Send Message:long
     * @param message Message
     */
    default void sendLong(long message) {
        sendLong(ProcessCommunication.WHAT_LONG, message);
    }
    /**
     * Send Message:float
     * @param message Message
     */
    default void sendFloat(float message) {
        sendFloat(ProcessCommunication.WHAT_FLOAT, message);
    }
    /**
     * Send Message:double
     * @param message Message
     */
    default void sendDouble(double message) {
        sendDouble(ProcessCommunication.WHAT_DOUBLE, message);
    }
    /**
     * Send Message:Parcelable
     * @param message Message
     */
    default void sendParcelable(@NonNull Parcelable message) {
        sendParcelable(ProcessCommunication.WHAT_PARCELABLE, message);
    }
    /**
     * Send Message:String
     * @param what What
     * @param message Message
     */
    void sendString(int what, @NonNull String message);
    /**
     * Send Message:boolean
     * @param what What
     * @param message Message
     */
    void sendBoolean(int what, boolean message);
    /**
     * Send Message:int
     * @param what What
     * @param message Message
     */
    void sendInt(int what, int message);
    /**
     * Send Message:long
     * @param what What
     * @param message Message
     */
    void sendLong(int what, long message);
    /**
     * Send Message:float
     * @param what What
     * @param message Message
     */
    void sendFloat(int what, float message);
    /**
     * Send Message:double
     * @param what What
     * @param message Message
     */
    void sendDouble(int what, double message);
    /**
     * Send Message:Parcelable
     * @param what What
     * @param message Message
     */
    void sendParcelable(int what, @NonNull Parcelable message);
    /**
     * Send Message:nothing
     */
    default void notifyDataChanged() {
        notifyDataChanged(ProcessCommunication.WHAT_NOTIFY);
    }
    /**
     * Send Message:nothing
     * @param what What
     */
    default void notifyDataChanged(int what) {
        sendInt(ProcessCommunication.WHAT_NOTIFY, what);
    }
}
