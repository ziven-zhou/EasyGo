package com.ziven.easygo.task;

import androidx.annotation.NonNull;

/**
 * @author Ziven
 */
public interface EasyGoTasks {

    /**
     * Add EasyGoTask
     * @param task {@link EasyGoTask}
     * @return Myself
     */
    @NonNull
    EasyGoTasks run(@NonNull EasyGoTask task);

    /**
     * Start execute
     * @return Myself
     */
    @NonNull
    EasyGoTasks start();

    /**
     * Stop execute
     */
    void stop();

    /**
     * End execute
     */
    void end();
}
