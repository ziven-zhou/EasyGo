package com.ziven.easygo.util;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author Ziven
 * @date 2021/5/22
 */
public final class LogHelper {

    private static String TAG = "LogHelper";
    private static boolean OPEN = false;
    private static String SEPARATOR = " ";

    private boolean isAlways = false;

    @NonNull
    private String mSeparator = SEPARATOR;

    @NonNull
    private final String mTag;

    private final StringBuilder mLog;

    private int mLevel = Log.DEBUG;

    private LogHelper(@NonNull String tag) {
        mTag = tag;
        mLog = new StringBuilder();
    }

    public static void setLog(boolean isOpen) {
        setLog(isOpen, null, null);
    }

    public static void setLog(boolean isOpen, @Nullable String tag) {
        setLog(isOpen, tag, null);
    }

    public static void setLog(boolean isOpen, @Nullable String tag, @Nullable String separator) {
        OPEN = isOpen;
        Condition.of(tag).doTrue(() -> TAG = tag);
        Condition.of(separator).doTrue(() -> SEPARATOR = separator);
    }

    @NonNull
    public static LogHelper of() {
        return new LogHelper(TAG);
    }

    @NonNull
    public static LogHelper of(@NonNull String tag) {
        return new LogHelper(tag);
    }

    @NonNull
    public static LogHelper of(@NonNull Object tag) {
        return new LogHelper(tag.getClass().getSimpleName());
    }

    @NonNull
    private LogHelper separator(@NonNull String separator) {
        mSeparator = separator;
        return this;
    }

    @NonNull
    public LogHelper debug() {
        mLevel = Log.DEBUG;
        return this;
    }

    @NonNull
    public LogHelper info() {
        mLevel = Log.INFO;
        return this;
    }

    @NonNull
    public LogHelper warn() {
        mLevel = Log.WARN;
        return this;
    }

    @NonNull
    public LogHelper error() {
        mLevel = Log.ERROR;
        return this;
    }

    @NonNull
    public LogHelper always() {
        isAlways = true;
        return this;
    }

    @NonNull
    public LogHelper throwable() {
        if(isPrint()) {
            return join(Log.getStackTraceString(new Throwable()));
        }
        return this;
    }

    @NonNull
    public LogHelper throwable(@NonNull Throwable tr) {
        if(isPrint()) {
            return join(Log.getStackTraceString(tr));
        }
        return this;
    }

    @NonNull
    public LogHelper exception(@NonNull Throwable tr) {
        if(isPrint()) {
            return join(tr);
        }
        return this;
    }

    @NonNull
    public LogHelper join(@Nullable Object o) {
        Condition.of(o).and(isPrint()).doTrue(() -> {
            mLog.append(o);
            mLog.append(mSeparator);
        });
        return this;
    }

    public void print() {
        if(isPrint()) {
            printInner();
        }
    }

    private void printInner() {
        switch (mLevel) {
            case Log.DEBUG:
                Log.d(mTag, mLog.toString());
                break;
            case Log.INFO:
                Log.i(mTag, mLog.toString());
                break;
            case Log.WARN:
                Log.w(mTag, mLog.toString());
                break;
            case Log.ERROR:
                Log.e(mTag, mLog.toString());
                break;
            default:
                //Do nothing
                break;
        }
    }

    private boolean isPrint() {
        return OPEN || isAlways;
    }
}
