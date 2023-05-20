package com.ziven.easygo.task;

import androidx.annotation.NonNull;

import com.ziven.easygo.util.ThreadUtils;

import java.util.concurrent.Executor;

/**
 * @author Ziven
 */
public final class EasyGoTaskUtils {

    static final String TAG = "EasyGoTaskUtils";
    static final StrIdFactory ID_FACTORY = StrIdFactory.newFactory();
    static final Executor EXECUTOR = ThreadUtils.newExecutor();

    private EasyGoTaskUtils() {}

    public static NormalTask createNormalTask(@NonNull Runnable runnable) {
        return new NormalTask() {
            @Override
            public void execute() {
                runnable.run();
            }
        };
    }

    public static SingleTask createSingleTask(@NonNull Runnable runnable,
                                              @NonNull String id) {
        return new SingleTask() {
            @NonNull
            @Override
            public String id() {
                return id;
            }

            @Override
            public void execute() {
                runnable.run();
            }
        };
    }

    public static FixedIdTask createFixedIdTask(@NonNull Runnable runnable,
                                                @NonNull EasyGoTaskType type,
                                                @NonNull EasyGoTaskPriority priority) {
        return new FixedIdTask() {
            @Override
            public void execute() {
                runnable.run();
            }

            @NonNull
            @Override
            public EasyGoTaskType type() {
                return type;
            }

            @NonNull
            @Override
            public EasyGoTaskPriority priority() {
                return priority;
            }
        };
    }

    public static AutoIdTask createAutoIdTask(@NonNull Runnable runnable,
                                              @NonNull EasyGoTaskType type,
                                              @NonNull EasyGoTaskPriority priority) {
        return new AutoIdTask() {
            @Override
            public void execute() {
                runnable.run();
            }

            @NonNull
            @Override
            public EasyGoTaskType type() {
                return type;
            }

            @NonNull
            @Override
            public EasyGoTaskPriority priority() {
                return priority;
            }
        };
    }

    public static NoTimeoutTask createNoTimeoutTask(@NonNull Runnable runnable,
                                                    @NonNull String id,
                                                    @NonNull EasyGoTaskType type,
                                                    @NonNull EasyGoTaskPriority priority) {
        return new NoTimeoutTask() {
            @NonNull
            @Override
            public String id() {
                return id;
            }

            @Override
            public void execute() {
                runnable.run();
            }

            @NonNull
            @Override
            public EasyGoTaskType type() {
                return type;
            }

            @NonNull
            @Override
            public EasyGoTaskPriority priority() {
                return priority;
            }
        };
    }

    public static EasyGoTask createEasyGoTask(@NonNull Runnable runnable,
                                              @NonNull String id,
                                              @NonNull EasyGoTaskType type,
                                              @NonNull EasyGoTaskPriority priority,
                                              long timeout) {
        return new EasyGoTask() {

            @NonNull
            @Override
            public String id() {
                return id;
            }

            @Override
            public void execute() {
                runnable.run();
            }

            @NonNull
            @Override
            public EasyGoTaskType type() {
                return type;
            }

            @NonNull
            @Override
            public EasyGoTaskPriority priority() {
                return priority;
            }

            @Override
            public long timeout() {
                return timeout;
            }
        };
    }

    public static EasyGoTasks create(@NonNull Executor executor) {
        return EasyGoTasksImpl.create(executor);
    }
}
