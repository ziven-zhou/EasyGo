package com.ziven.easygo.task;

import android.os.SystemClock;

import androidx.annotation.NonNull;

import com.ziven.easygo.util.LogHelper;

import java.util.LinkedList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Ziven
 */
class RunTask extends NoTimeoutTask {

    private static final String ID = "com.ziven.easygo.task.RunTask";
    private final LinkedList<EasyGoTask> tasks;
    private final AtomicBoolean isStopping;
    private final AtomicBoolean isRunning;
    private final Executor executor;

    static RunTask create(@NonNull LinkedList<EasyGoTask> tasks,
                          @NonNull Executor executor) {
        return new RunTask(tasks, executor);
    }

    private RunTask(@NonNull LinkedList<EasyGoTask> tasks,
                    @NonNull Executor executor) {
        this.tasks = tasks;
        this.executor = executor;
        isStopping = new AtomicBoolean(false);
        isRunning = new AtomicBoolean(false);
    }

    @NonNull
    @Override
    public String id() {
        return ID;
    }

    @Override
    public void execute() {
        if(isStopping.get() || isRunning.get()) {
            return;
        }
        isRunning.set(true);
        EasyGoTask task = tasks.poll();
        LogHelper.log(EasyGoTaskUtils.TAG, "RunTask:" + task);
        if(task == null) {
            isRunning.set(false);
            return;
        }
        executor.execute(() -> {
            if(SystemClock.elapsedRealtime() - task.createTime < timeout()) {
                task.execute();
            }
            isRunning.set(false);
            EasyGoTaskUtils.EXECUTOR.execute(RunTask.this);
        });
    }

    public void start() {
        isStopping.set(false);
        EasyGoTaskUtils.EXECUTOR.execute(RunTask.this);
    }

    public void stop() {
        isStopping.set(true);
    }

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
