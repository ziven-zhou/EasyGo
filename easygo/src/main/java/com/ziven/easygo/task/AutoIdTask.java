package com.ziven.easygo.task;

import androidx.annotation.NonNull;

/**
 * @author Ziven
 */
public abstract class AutoIdTask extends NoTimeoutTask {

    private final String ID = EasyGoTaskUtils.ID_FACTORY.create();

    @NonNull
    @Override
    public String id() {
        return ID;
    }
}
