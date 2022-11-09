package com.ziven.easygo.util;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author :zhiyuan.zhou
 * @date :2021/7/1
 */
public final class TimeHelper {

    public static final long MILLI1000 = 1000;
    public static final long SECOND = MILLI1000;
    public static final long MINUTE = 60 * SECOND;
    public static final long HOUR = 60 * MINUTE;
    public static final long DAY = 24 * HOUR;

    private static final String DEF_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final long DOUBLE_CLICK_DUR = 300;
    private static final String DOUBLE_CLICK_KEY = "easy_go_double_click";
    private final ConcurrentHashMap<String, Long> mStartTime;

    private static class Instance {
        private static final TimeHelper INSTANCE = new TimeHelper();
    }

    public static TimeHelper get() {
        return Instance.INSTANCE;
    }

    private TimeHelper() {
        mStartTime = new ConcurrentHashMap<>();
    }

    public void start(@NonNull String key) {
        mStartTime.put(key, System.currentTimeMillis());
    }

    public long end(@NonNull String key) {
        Long start = mStartTime.remove(key);
        if(start == null) {
            LogHelper.of("TimeHelperTag").always().join(key).join("not start.").print();
            return 0;
        }
        return System.currentTimeMillis() - start;
    }

    public void endPrint(@NonNull String key) {
        endPrint("TimeEndPrint", key);
    }

    public void endPrint(@NonNull String tag, @NonNull String key) {
        LogHelper.of(tag).always().join(key).join(":").join(end(key)).print();
    }

    public static long obtainTodayStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static long obtainTodayEnd() {
        return obtainTodayStart() + DAY - 1;
    }

    @NonNull
    public static Date transition(long time) {
        return new Date(time);
    }

    public static long transition(@NonNull Date date) {
        return date.getTime();
    }

    public static String withDefFormat(long time) {
        return withDefFormat(transition(time));
    }

    public static String transition(long time, @NonNull SimpleDateFormat format) {
        return transition(transition(time), format);
    }

    public static String withDefFormat(Date date) {
        return transition(date, new SimpleDateFormat(DEF_PATTERN, Locale.getDefault()));
    }

    public static String transition(@NonNull Date date, @NonNull final SimpleDateFormat format) {
         return Objects.requireNonNull(format).format(date);
    }

    public static boolean isNewDay(@NonNull String key) {
        long endToday = ResourceUtils.sp().getLong(key, 0);
        if(endToday > System.currentTimeMillis()) {
            return false;
        }
        ResourceUtils.edit().putLong(key, obtainTodayEnd()).apply();
        return true;
    }

    public static boolean doubleClick() {
        return doubleClick(DOUBLE_CLICK_DUR);
    }

    public static boolean doubleClick(long duration) {
        long end = TimeHelper.get().end(DOUBLE_CLICK_KEY);
        if(end > 0 && end < duration) {
            LogHelper.of("TimeHelperTag").join("Double click.").print();
            return true;
        }
        TimeHelper.get().start(DOUBLE_CLICK_KEY);
        return false;
    }
}
