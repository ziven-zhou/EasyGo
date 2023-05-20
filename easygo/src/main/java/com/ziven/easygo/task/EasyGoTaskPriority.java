package com.ziven.easygo.task;

import androidx.annotation.NonNull;

/**
 * @author Ziven
 */

public enum EasyGoTaskPriority {

    /**
     * From 0 -> 10 : lowest -> highest
     */
    LOWEST(0),

    LEVEL_1(1),

    LEVEL_2(2),

    LEVEL_3(3),

    LEVEL_4(4),

    MEDIUM(5),

    LEVEL_6(6),

    LEVEL_7(7),

    LEVEL_8(8),

    LEVEL_9(9),

    HIGHEST(10);

    private final int priority;

    EasyGoTaskPriority(int priority) {
        this.priority = priority;
    }

    public boolean highAndEqual(@NonNull EasyGoTaskPriority taskPriority) {
        return priority >= taskPriority.priority;
    }

    public boolean lowAndEqual(@NonNull EasyGoTaskPriority taskPriority) {
        return priority <= taskPriority.priority;
    }

    public boolean high(@NonNull EasyGoTaskPriority taskPriority) {
        return priority > taskPriority.priority;
    }

    public boolean equal(@NonNull EasyGoTaskPriority taskPriority) {
        return priority == taskPriority.priority;
    }

    public boolean low(@NonNull EasyGoTaskPriority taskPriority) {
        return priority < taskPriority.priority;
    }
}
