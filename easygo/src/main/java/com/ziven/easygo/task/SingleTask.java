package com.ziven.easygo.task;

import androidx.annotation.NonNull;

/**
 * @author Ziven
 */
public abstract class SingleTask extends NoTimeoutTask {

    @NonNull
    @Override
    public EasyGoTaskType type() {
        return EasyGoTaskType.RUN_NEW;
    }

    @NonNull
    @Override
    public EasyGoTaskPriority priority() {
        return EasyGoTaskPriority.MEDIUM;
    }
}
