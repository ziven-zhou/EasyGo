package com.ziven.easygo.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.NonNull;

/**
 * @author :zhiyuan.zhou
 * @date :2021/7/2
 */
public final class PackageUtils {
    private PackageUtils() {}

    public static String getPackageName() {
        return getPackageName(ResourceUtils.getContext());
    }

    public static String getPackageName(@NonNull Context context) {
        return context.getPackageName();
    }

    public static String getVersionName() {
        return getVersionName(ResourceUtils.getContext());
    }

    public static String getVersionName(@NonNull Context context) {
        PackageInfo info = getPackageInfo(context, getPackageName(context));
        return info == null ? null : info.versionName;
    }

    public static long getVersionCode() {
        return getVersionCode(ResourceUtils.getContext());
    }

    public static long getVersionCode(@NonNull Context context) {
        PackageInfo info = getPackageInfo(context, getPackageName(context));
        if(info == null) {
            return 0;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            return info.getLongVersionCode();
        }
        return info.versionCode;
    }

    public static PackageInfo getPackageInfo(@NonNull String packageName) {
        return getPackageInfo(ResourceUtils.getContext(), packageName);
    }

    public static PackageInfo getPackageInfo(@NonNull Context context, @NonNull String packageName) {
        try {
            return getPm(context).getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ApplicationInfo getApplicationInfo(@NonNull String packageName) {
        return getApplicationInfo(ResourceUtils.getContext(), packageName);
    }

    public static ApplicationInfo getApplicationInfo(@NonNull Context context, @NonNull String packageName) {
        try {
            return getPm(context).getApplicationInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PackageManager getPm() {
        return getPm(ResourceUtils.getContext());
    }

    public static PackageManager getPm(@NonNull Context context) {
        return context.getPackageManager();
    }
}
