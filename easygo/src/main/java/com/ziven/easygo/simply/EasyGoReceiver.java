package com.ziven.easygo.simply;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import androidx.annotation.NonNull;

import com.ziven.easygo.util.Condition;
import com.ziven.easygo.util.EasyUtils;
import com.ziven.easygo.util.LogHelper;
import com.ziven.easygo.util.Nulls;
import com.ziven.easygo.util.ResourceUtils;
import com.ziven.easygo.util.ThreadUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @author :zhiyuan.zhou
 * @date :2021/7/2
 */
public class EasyGoReceiver extends BroadcastReceiver {

    private final HashMap<String, List<IReceiver>> mReceivers;
    private boolean mRegister = false;
    private IAction mAction;

    private static class Instance {
        private static final EasyGoReceiver INSTANCE = new EasyGoReceiver();
    }

    public static EasyGoReceiver get() {
        return Instance.INSTANCE;
    }

    private EasyGoReceiver() {
        mReceivers = new HashMap<>();
    }

    public EasyGoReceiver setAction(@NonNull IAction action) {
        mAction = action;
        return this;
    }

    public void register() {
        register(ResourceUtils.getContext());
    }

    public void register(@NonNull final Context context) {
        if(mRegister) {
            LogHelper.of("EasyGoReceiverTag").join("Has register.").print();
            return;
        }
        Nulls.of(mAction)
                .doNull(() -> {
                    if (context instanceof IAction) {
                        mAction = EasyUtils.transition(context);
                    }
                })
                .justNext(mAction)
                .notNullNext(IAction::actions)
                .doNotNull(a -> {
                    final IntentFilter filter = new IntentFilter();
                    EasyUtils.forEach(a, filter::addAction);
                    context.registerReceiver(this, filter);
                    mRegister = true;
                });
    }

    public void unregister() {
        unregister(ResourceUtils.getContext());
    }

    public void unregister(@NonNull final Context context) {
        Condition.of(mRegister).doTrue(() -> {
            context.unregisterReceiver(this);
            mRegister = false;
        });
        EasyUtils.forEach(mReceivers, (key, value) -> value.clear());
        mReceivers.clear();
    }

    public EasyGoReceiver setReceiverReturnThis(@NonNull IReceiver receiver, @NonNull String... actions) {
        setReceiver(receiver, actions);
        return this;
    }

    public IReceiver setReceiver(@NonNull IReceiver receiver, @NonNull String... actions) {
        ThreadUtils.runOnMainThread(() -> EasyUtils.forEach(actions, a -> Nulls.of(mReceivers.get(a))
                .doNull(() -> mReceivers.put(a, EasyUtils.newList(receiver)))
                .doNotNull(list -> EasyUtils.addNoDuplicates(list, receiver))));
        return receiver;
    }

    public void removeReceiver(@NonNull IReceiver receiver) {
        ThreadUtils.runOnMainThread(() -> EasyUtils.forEach(mReceivers, list -> list.remove(receiver)));
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        LogHelper.of("EasyGoReceiverTag").join(action).print();
        Nulls.of(mReceivers.get(action)).doNotNull(list -> EasyUtils.forEach(list, r -> r.received(context, intent, action)));
    }

    public interface IAction {
        /**
         * Filter Actions
         * @return Actions
         */
        String[] actions();
    }

    public interface IReceiver {
        /**
         * Receiver Broadcast
         * @param context Context
         * @param intent Intent
         * @param action Action
         */
        void received(Context context, Intent intent, String action);
    }
}
