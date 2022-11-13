package com.ziven.easygo.process;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.ziven.easygo.process.client.ClientHandler;
import com.ziven.easygo.process.server.ServerHandler;
import com.ziven.easygo.util.EasyUtils;
import com.ziven.easygo.util.LogHelper;
import com.ziven.easygo.util.Nulls;
import com.ziven.easygo.util.ResourceUtils;

/**
 * @author zhiyuan.zhou
 */
public final class ProcessCommunication {

    private static final String TAG = "ProcessCommunication:";
    public static final int WHAT_MESSENGER = -1000;
    public static final int WHAT_NOTIFY = -1001;
    public static final int WHAT_STRING = -1002;
    public static final int WHAT_BOOLEAN = -1003;
    public static final int WHAT_INT = -1004;
    public static final int WHAT_LONG = -1005;
    public static final int WHAT_FLOAT = -1006;
    public static final int WHAT_DOUBLE = -1007;
    public static final int WHAT_PARCELABLE = -1008;
    private static final String SERVER = "com.ziven.easygo.process.server.ServerService";
    private static final ServerClientDispatch CLIENT_DISPATCH = new ServerClientDispatch();
    private static final ServerClientDispatch SERVER_DISPATCH = new ServerClientDispatch();


    private ProcessCommunication() {}

    private static ServerClientDispatch clientDispatch() {
        return CLIENT_DISPATCH;
    }

    private static ServerClientDispatch serverDispatch() {
        return SERVER_DISPATCH;
    }

    public static void client(@NonNull ProcessReceiver receiver) {
        initClient();
        setClientReceiver(receiver);
        bindService();
    }

    public static void server(@NonNull ProcessReceiver receiver) {
        initServer();
        setServerReceiver(receiver);
    }

    public static void initClient() {
        clientDispatch().setProcessHandler(new ClientHandler());
    }

    public static void initServer() {
        serverDispatch().setProcessHandler(new ServerHandler());
    }

    public static ProcessSender clientSender() {
        return clientDispatch().getProcessSender();
    }

    public static ProcessSender serverSender() {
        return serverDispatch().getProcessSender();
    }

    @NonNull
    public static Nulls<ServerHandler> serverHandler() {
         return serverDispatch().serverHandler();
    }

    public static void setClientReceiver(@NonNull ProcessReceiver receiver) {
        clientDispatch().setProcessReceiver(receiver);
    }

    public static void setServerReceiver(@NonNull ProcessReceiver receiver) {
        serverDispatch().setProcessReceiver(receiver);
    }

    public static void bindService() {
        Intent intent = new Intent();
        intent.setClassName(ResourceUtils.getContext(), SERVER);
        bindService(intent);
    }

    public static void bindService(int flags) {
        Intent intent = new Intent();
        intent.setClassName(ResourceUtils.getContext(), SERVER);
        bindService(intent, flags);
    }

    public static void bindService(@NonNull Intent intent) {
        log("bindService intent=" + intent);
        clientDispatch().clientHandler()
                .doNotNull(client -> client.bindService(intent));
    }

    public static void bindService(@NonNull Intent intent, int flags) {
        log("bindService intent=" + intent, "flags=" + flags);
        clientDispatch().clientHandler()
                .doNotNull(client -> client.bindService(intent, flags));
    }

    public static void unbindService() {
        log("unbindService");
        clientDispatch().clientHandler()
                .doNotNull(ClientHandler::unbindService);
    }

    public static void log(Object... logs) {
        LogHelper helper = LogHelper.of(TAG);
        EasyUtils.forEach(logs, helper::join);
        helper.print();
    }
}
