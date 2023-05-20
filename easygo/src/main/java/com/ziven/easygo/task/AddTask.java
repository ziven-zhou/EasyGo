package com.ziven.easygo.task;

import androidx.annotation.NonNull;

import com.ziven.easygo.util.LogHelper;

import java.util.LinkedList;

class AddTask extends NoTimeoutTask {

    private static final String ID = "com.ziven.easygo.task.AddTask";
    private final LinkedList<EasyGoTask> tasks;
    private final EasyGoTask task;

    static AddTask create(@NonNull LinkedList<EasyGoTask> tasks,
                          @NonNull EasyGoTask task) {
        return new AddTask(tasks, task);
    }

    private AddTask(@NonNull LinkedList<EasyGoTask> tasks,
                    @NonNull EasyGoTask task) {
        this.tasks = tasks;
        this.task = task;
    }

    @NonNull
    @Override
    public String id() {
        return ID;
    }

    @Override
    public void execute() {
        LogHelper.log(EasyGoTaskUtils.TAG, "AddTask:" + task);
        if(tasks.isEmpty()) {
            tasks.add(task);
            return;
        }

        if(task.type() == EasyGoTaskType.RUN_OLD
                && tasks.contains(task)) {
            LogHelper.log(EasyGoTaskUtils.TAG, "RUN_OLD:" + task);
            return;
        }

        if(task.type() == EasyGoTaskType.RUN_NEW) {
            tasks.remove(task);
            LogHelper.log(EasyGoTaskUtils.TAG, "RUN_NEW:" + task);
        }

        int insert = tasks.size();
        for(int i=0; i<tasks.size(); i++) {
            if(tasks.get(i).priority().low(task.priority())) {
                insert = i;
                break;
            }
        }
        tasks.add(insert, task);
    }

    @NonNull
    @Override
    public EasyGoTaskType type() {
        return EasyGoTaskType.RUN_ALL;
    }

    @NonNull
    @Override
    public EasyGoTaskPriority priority() {
        return EasyGoTaskPriority.HIGHEST;
    }
}
