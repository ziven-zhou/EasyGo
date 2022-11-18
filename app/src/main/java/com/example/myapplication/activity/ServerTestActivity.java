package com.example.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.myapplication.BaseTestActivity;
import com.example.myapplication.MyApplication;
import com.ziven.easygo.EasyGos;
import com.ziven.easygo.annotation.EasyGoActivity;
import com.ziven.easygo.autowired.EasyGo;
import com.ziven.easygo.process.ProcessCommunication;
import com.ziven.easygo.process.ProcessReceiver;
import com.ziven.easygo.simply.EasyGoReceiver;
import com.ziven.easygo.util.Conditions;
import com.ziven.easygo.util.EasyUtils;

/**
 * @author Ziven
 */
@EasyGoActivity(path = "ServerTestActivity")
public class ServerTestActivity extends BaseTestActivity
        implements ProcessReceiver, EasyGoReceiver.IReceiver {

    private int receivedCount = 0;

    @Override
    protected void initLayout2() {
        ProcessCommunication.server(this);

        EasyGos.getEasyGoReceiver()
                .setReceiverReturnThis(this, MyApplication.ACTION);
    }

    @Override
    public void condition(int condition) {
        EasyGo.easyGo(ClientTestActivity.class);
    }

    @Override
    public void received(Context context, Intent intent, String action) {

        EasyUtils.conditions(new Conditions() {
            @Override
            public void condition1() {
                ProcessCommunication
                        .serverSender()
                        .sendString(100, "I am server.");
            }

            @Override
            public void condition2() {
                ProcessCommunication
                        .serverSender()
                        .sendBoolean(101, false);
            }

            @Override
            public void condition3() {
                ProcessCommunication
                        .serverSender()
                        .sendInt(102, 10);
            }

            @Override
            public void condition4() {
                ProcessCommunication
                        .serverSender()
                        .sendLong(103, 11L);
            }

            @Override
            public void condition5() {
                ProcessCommunication
                        .serverSender()
                        .sendFloat(104, 12.5F);
            }

            @Override
            public void condition6() {
                ProcessCommunication
                        .serverSender()
                        .sendDouble(105, 13.5D);
            }

            @Override
            public void condition7() {
                ProcessCommunication
                        .serverSender()
                        .notifyDataChanged(105);
            }
        }, receivedCount++);
    }

    @Override
    protected void destroyLayout() {
        EasyGos.getEasyGoReceiver().removeReceiver(this);
    }

    @Override
    public void receiveString(int what, @NonNull String message) {
        log("server receiveString:", what, message);
    }

    @Override
    public void receiveBoolean(int what, boolean message) {
        log("server receiveBoolean:", what, message);
    }

    @Override
    public void receiveInt(int what, int message) {
        log("server receiveInt:", what, message);
    }

    @Override
    public void receiveLong(int what, long message) {
        log("server receiveLong:", what, message);
    }

    @Override
    public void receiveFloat(int what, float message) {
        log("server receiveFloat:", what, message);
    }

    @Override
    public void receiveDouble(int what, double message) {
        log("server receiveDouble:", what, message);
    }

    @Override
    public void receiveParcelable(int what, @NonNull Parcelable message) {
        log("server receiveParcelable:", what, message);
    }

    @Override
    public void notifyDataChanged(int what) {
        log("server notifyDataChanged:", what);
    }
}
