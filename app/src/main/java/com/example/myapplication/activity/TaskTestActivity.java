package com.example.myapplication.activity;

import androidx.annotation.NonNull;

import com.example.myapplication.BaseTestActivity;
import com.ziven.easygo.annotation.EasyGoActivity;
import com.ziven.easygo.task.EasyGoTaskPriority;
import com.ziven.easygo.task.EasyGoTaskType;
import com.ziven.easygo.task.EasyGoTaskUtils;
import com.ziven.easygo.task.EasyGoTasks;
import com.ziven.easygo.util.ThreadUtils;

/**
 * @author Ziven
 */
@EasyGoActivity(path = "TaskTestActivity")
public class TaskTestActivity extends BaseTestActivity {

    private final EasyGoTasks easyGoTasks = EasyGoTaskUtils.create(ThreadUtils.newExecutor());

    @Override
    protected void initLayout2() {
        easyGoTasks
                .run(EasyGoTaskUtils.createNormalTask(() -> printInfo("createNormalTask")))
                .run(EasyGoTaskUtils.createSingleTask(() -> printInfo("createSingleTask1"), "SingleTask"))
                .run(EasyGoTaskUtils.createSingleTask(() -> printInfo("createSingleTask2"), "SingleTask"))
                .run(EasyGoTaskUtils.createFixedIdTask(() -> printInfo("createFixedIdTask"), EasyGoTaskType.RUN_ALL, EasyGoTaskPriority.LOWEST))
                .run(EasyGoTaskUtils.createAutoIdTask(() -> printInfo("createAutoIdTask"), EasyGoTaskType.RUN_ALL, EasyGoTaskPriority.MEDIUM))
                .run(EasyGoTaskUtils.createNoTimeoutTask(() -> printInfo("createNoTimeoutTask"), "SingleTask", EasyGoTaskType.RUN_OLD, EasyGoTaskPriority.HIGHEST));
    }

    private void printInfo(@NonNull String info) {
        log("EasyGoTaskUtils 1: " + info);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log("EasyGoTaskUtils 2: " + info);
    }

    @Override
    protected void destroyLayout() {

    }
}
