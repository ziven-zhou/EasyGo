package com.ziven.easygo;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.ziven.easygo.autowired.EasyGo;
import com.ziven.easygo.design.funnel.FilterHelper;
import com.ziven.easygo.overall.IDispatch;
import com.ziven.easygo.overall.IOverall;
import com.ziven.easygo.overall.OverallModel;
import com.ziven.easygo.simply.EasyGoReceiver;
import com.ziven.easygo.ui.ViewProvider;
import com.ziven.easygo.util.DataProvider;
import com.ziven.easygo.util.LogHelper;
import com.ziven.easygo.util.MapDataProvider;
import com.ziven.easygo.util.ResourceUtils;
import com.ziven.easygo.util.TimeHelper;
import com.ziven.easygo.util.ViewHelper;

import java.util.List;
import java.util.Map;

/**
 * @author Ziven
 */
public final class EasyGos {
    private EasyGos() {}

    public static void init(@NonNull Context context) {
        init(context, false);
    }

    /**
     * For Debug: Log Tag = "LogHelper"
     * @param context Context
     */
    public static void initLog(@NonNull Context context) {
        init(context, true);
    }

    /**
     * For Debug
     * @param context Context
     * @param tag Log Tag
     */
    public static void initLog(@NonNull Context context, @NonNull String tag) {
        ResourceUtils.init(context);
        LogHelper.setLog(true, tag);
    }

    public static void init(@NonNull Context context, boolean log) {
        ResourceUtils.init(context);
        LogHelper.setLog(log);
    }

    @NonNull
    public static IDispatch getDispatch() {
        Context context = ResourceUtils.getContext();
        return context instanceof IOverall<?>
                ? ((IOverall<? extends IDispatch>) context).getDispatch()
                : () -> OverallModel.EMPTY;
    }

    public static EasyGo getEasyGo() {
        return EasyGo.get();
    }

    public static TimeHelper getTimeHelper() {
        return TimeHelper.get();
    }

    public static FilterHelper getFilterHelper() {
        return FilterHelper.get();
    }

    public static EasyGoReceiver getEasyGoReceiver() {
        return EasyGoReceiver.get();
    }

    public static <T>  DataProvider<T> newDataProvider(@NonNull List<T> list) {
        return DataProvider.of(list);
    }

    public static <T>  DataProvider<T> newDataProviderEmpty() {
        return newDataProvider(true);
    }

    public static <T>  DataProvider<T> newDataProviderNull() {
        return newDataProvider(false);
    }

    public static <T>  DataProvider<T> newDataProvider(boolean isEmpty) {
        return isEmpty
                ? DataProvider.ofEmpty()
                : DataProvider.ofNull();
    }

    public static <K, V> MapDataProvider<K, V> newMapDataProvider() {
        return MapDataProvider.of();
    }

    public static <K, V> MapDataProvider<K, V> newMapDataProvider(Map<K, V> map) {
        return map == null ? MapDataProvider.of() : MapDataProvider.of(map);
    }

    public static <T extends View> ViewHelper<T> newViewHelper() {
        return ViewHelper.create(null);
    }

    public static <T extends View> ViewHelper<T> newViewHelper(T view) {
        return ViewHelper.create(view);
    }

    public static <T extends View> ViewProvider<T> newViewProvider() {
        return ViewProvider.newInstance();
    }

    public static LogHelper newLogHelper() {
        return LogHelper.of();
    }

    public static LogHelper newLogHelper(Object o) {
        return o == null
                ? LogHelper.of()
                : o instanceof String
                ? LogHelper.of((String) o)
                : LogHelper.of(o);
    }
}
