package com.ziven.easygo.task;

import androidx.annotation.NonNull;

/**
 * @author Ziven
 */
public abstract class NormalTask extends FixedIdTask {

    @NonNull
    @Override
    public EasyGoTaskType type() {
        return EasyGoTaskType.RUN_ALL;
    }

    @NonNull
    @Override
    public EasyGoTaskPriority priority() {
        return EasyGoTaskPriority.MEDIUM;
    }
}
