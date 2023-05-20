package com.ziven.easygo.task;

import android.os.SystemClock;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

/**
 * @author Ziven
 */
public abstract class EasyGoTask implements Runnable {

    public final long createTime = SystemClock.elapsedRealtime();

    @Override
    public void run() {
        execute();
    }

    /**
     * Task Id
     * @return Id
     */
    @NonNull
    public abstract String id();

    /**
     * Execute Runnable
     */
    public abstract void execute();

    /**
     * Obtain EasyGoTaskType
     * @return {@link EasyGoTaskType}
     */
    @NonNull
    public abstract EasyGoTaskType type();

    /**
     * Obtain EasyGoTaskPriority
     * @return {@link EasyGoTaskPriority}
     */
    @NonNull
    public abstract EasyGoTaskPriority priority();


    /**
     * Timeout: not execute
     * @return millisecond
     */
    public abstract long timeout();

    @Override
    public int hashCode() {
        return Objects.hashCode(id());
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj instanceof EasyGoTask) {
            return TextUtils.equals(id(), ((EasyGoTask) obj).id());
        }
        return false;
    }

    @NonNull
    @Override
    public String toString() {
        return "EasyGoTask{" +
                " id=" + id() +
                " type=" + type() +
                " priority=" + priority() +
                " timeout=" + timeout() +
                " }";
    }
}
