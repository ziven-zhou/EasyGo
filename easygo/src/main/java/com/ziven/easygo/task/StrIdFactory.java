package com.ziven.easygo.task;

import androidx.annotation.NonNull;

import com.ziven.easygo.util.Factory;

/**
 * @author Ziven
 */
public class StrIdFactory implements Factory<String> {

    private static final String TAG = "StrIdFactory";
    private final IntIdFactory factory;

    public static StrIdFactory newFactory() {
        return new StrIdFactory();
    }

    private StrIdFactory() {
        factory = IntIdFactory.newFactory();
    }

    @NonNull
    @Override
    public String create() {
        return TAG + factory.create();
    }
}
