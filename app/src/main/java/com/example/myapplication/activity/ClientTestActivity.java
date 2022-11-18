package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.myapplication.BaseTestActivity;
import com.example.myapplication.MyApplication;
import com.ziven.easygo.process.ProcessCommunication;
import com.ziven.easygo.process.ProcessReceiver;

/**
 * @author Ziven
 */
public class ClientTestActivity extends BaseTestActivity implements ProcessReceiver {

    @Override
    protected void initLayout2() {
        ProcessCommunication.client(this);
    }

    @Override
    public void condition1() {
        ProcessCommunication
                .clientSender()
                .sendString("I am client.");
    }

    @Override
    public void condition2() {
        ProcessCommunication
                .clientSender()
                .sendBoolean(true);
    }

    @Override
    public void condition3() {
        ProcessCommunication
                .clientSender()
                .sendInt(2);
    }

    @Override
    public void condition4() {
        ProcessCommunication
                .clientSender()
                .sendLong(3L);
    }

    @Override
    public void condition5() {
        ProcessCommunication
                .clientSender()
                .sendFloat(4.5f);
    }

    @Override
    public void condition6() {
        ProcessCommunication
                .clientSender()
                .sendDouble(6.5D);
    }

    @Override
    public void condition7() {
        ProcessCommunication
                .clientSender()
                .notifyDataChanged(7);
    }

    @Override
    protected void button2Click() {
        sendBroadcast(new Intent(MyApplication.ACTION));
    }

    @Override
    protected void destroyLayout() {
        ProcessCommunication.unbindService();
    }

    @Override
    public void receiveString(int what, @NonNull String message) {
        log("client receiveString:", what, message);
    }

    @Override
    public void receiveBoolean(int what, boolean message) {
        log("client receiveBoolean:", what, message);
    }

    @Override
    public void receiveInt(int what, int message) {
        log("client receiveInt:", what, message);
    }

    @Override
    public void receiveLong(int what, long message) {
        log("client receiveLong:", what, message);
    }

    @Override
    public void receiveFloat(int what, float message) {
        log("client receiveFloat:", what, message);
    }

    @Override
    public void receiveDouble(int what, double message) {
        log("client receiveDouble:", what, message);
    }

    @Override
    public void receiveParcelable(int what, @NonNull Parcelable message) {
        log("client receiveParcelable:", what, message);
    }

    @Override
    public void notifyDataChanged(int what) {
        log("client notifyDataChanged:", what);
    }
}
