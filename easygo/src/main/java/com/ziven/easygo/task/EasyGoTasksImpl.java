package com.ziven.easygo.task;

import androidx.annotation.NonNull;

import java.util.LinkedList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Ziven
 */
class EasyGoTasksImpl implements EasyGoTasks {

    private final LinkedList<EasyGoTask> tasks;
    private final AtomicBoolean isEnding;
    private final RunTask runTask;

    static EasyGoTasksImpl create(@NonNull Executor executor) {
        return new EasyGoTasksImpl(executor);
    }

    private EasyGoTasksImpl(@NonNull Executor executor) {
        isEnding = new AtomicBoolean(false);
        tasks = new LinkedList<>();
        runTask = RunTask.create(tasks, executor);
    }

    @NonNull
    @Override
    public EasyGoTasks run(@NonNull EasyGoTask task) {
        if(!isEnding.get()) {
            EasyGoTaskUtils.EXECUTOR.execute(AddTask.create(tasks, task));
            start();
        }
        return this;
    }

    @NonNull
    @Override
    public EasyGoTasks start() {
        if(!isEnding.get()) {
            runTask.start();
        }
        return this;
    }

    @Override
    public void stop() {
        runTask.stop();
    }

    @Override
    public void end() {
        stop();
        isEnding.set(true);
        EasyGoTaskUtils.EXECUTOR.execute(tasks::clear);
    }
}
