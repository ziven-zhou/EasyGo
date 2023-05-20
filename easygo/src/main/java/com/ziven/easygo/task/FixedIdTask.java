package com.ziven.easygo.task;

import androidx.annotation.NonNull;

/**
 * @author Ziven
 */
public abstract class FixedIdTask extends NoTimeoutTask {

    private static final String ID = "com.ziven.easygo.task.FixedIdTask";

    @NonNull
    @Override
    public String id() {
        return ID;
    }
}
