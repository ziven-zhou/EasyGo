package com.ziven.easygo.autowired;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import com.ziven.easygo.annotation.EasyGoActivity;
import com.ziven.easygo.annotation.EasyGoMethod;
import com.ziven.easygo.annotation.EasyGoType;
import com.ziven.easygo.annotation.IEasyGoActivity;
import com.ziven.easygo.annotation.IEasyGoMethod;
import com.ziven.easygo.util.Carry;
import com.ziven.easygo.util.LogHelper;
import com.ziven.easygo.util.Nulls;
import com.ziven.easygo.util.Obtain;
import com.ziven.easygo.util.ResourceUtils;
import com.ziven.easygo.util.ThreadUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author :Ziven
 * @date :2021/6/2
 */
@Keep
public final class EasyGo {
    private static final String TAG = "EasyGoTag";
    private static final String PATH_AUTOWIRED = "com.ziven.easygo.autowired.";
    private static final String EASY_GO = "EasyGo";
    private static final String CLASS_SEPARATOR = "$$";
    private final Map<String, EasyGoType> mEasyGoTypes;
    @SuppressWarnings("rawtypes")
    private Map<String, Class> mEasyGoActivity;

    private static class Instance {
        private static final EasyGo INSTANCE = new EasyGo();
    }

    public static EasyGo get() {
        return Instance.INSTANCE;
    }

    private EasyGo() {
        mEasyGoTypes = new HashMap<>(8);
    }

    public void register(@NonNull Object target) {
        IEasyGoMethod method = getEasyGoMethod(target.getClass(), EasyGoMethod.class.getSimpleName());
        if(method == null) {
            LogHelper.of(TAG).always().debug().join("IEasyGoMethod obtain fail.").print();
            return;
        }
        List<EasyGoType> types = method.obtainEasyGoType();
        if(types == null) {
            LogHelper.of(TAG).always().debug().join("Has no EasyGoMethod in this target.").print();
            return;
        }
        for (EasyGoType type : types) {
            type.setTarget(target);
            type.setEasyGoMethod(method);
            mEasyGoTypes.put(type.getPathName(), type);
        }
    }

    public void  unregister(@NonNull Object target) {
        Iterator<EasyGoType> iterator = mEasyGoTypes.values().iterator();
        EasyGoType type;
        while (iterator.hasNext()) {
            type = iterator.next();
            if(type.getTarget() == target) {
                iterator.remove();
            }
        }
    }

    private IEasyGoMethod getEasyGoMethod(@NonNull Class<?> cls, @NonNull String suffix) {
        String targetClassName = PATH_AUTOWIRED + cls.getSimpleName() + CLASS_SEPARATOR + suffix;
        try {
            return  (IEasyGoMethod) Class.forName(targetClassName).getConstructor().newInstance();
        } catch (IllegalAccessException
                | InstantiationException
                | InvocationTargetException
                | NoSuchMethodException
                | ClassNotFoundException e) {
            LogHelper.of(TAG).always().debug().throwable(e).print();
        }
        return null;
    }

    public void easyGo(@NonNull Transition transition) {
        try {
            easyGoInner(transition);
        } catch (Throwable th) {
            LogHelper.of(TAG).always().debug().throwable(th).print();
        }
    }

    private void easyGoInner(@NonNull final Transition transition) {
        final EasyGoType type = mEasyGoTypes.get(transition.pathName);
        if(type == null) {
            LogHelper.of(TAG).always().debug().join("Transition fail:no <").join(transition.pathName).join(">path.").print();
            return;
        }
        switch (type.getThreadMode()) {
            case MAIN:
                ThreadUtils.runOnMainThread(() -> {
                    final Object r = type.getEasyGoMethod().easyGoMethod(type.getTarget(), transition.pathName, transition.params);
                    Nulls.of(transition.carry).doNotNull(carry -> carry.carry(r));
                });
                break;
            case WORKER:
                ThreadUtils.runOnWorkerThread(() -> {
                    final Object r = type.getEasyGoMethod().easyGoMethod(type.getTarget(), transition.pathName, transition.params);
                    Nulls.of(transition.carry).doNotNull(carry -> carry.carry(r));
                });
                break;
            default:
                final Object r = type.getEasyGoMethod().easyGoMethod(type.getTarget(), transition.pathName, transition.params);
                Nulls.of(transition.carry).doNotNull(carry -> carry.carry(r));
                break;
        }
    }

    @Keep
    public final static class Transition {
        private final String pathName;
        private final List<Object> params;
        private Carry<Object> carry;

        public static Transition of(@NonNull String pathName) {
            return new Transition(pathName);
        }

        private Transition(@NonNull String pathName) {
            this.pathName = pathName;
            params = new ArrayList<>(0);
        }

        public Transition carry(@NonNull Carry<Object> carry) {
            this.carry = carry;
            return this;
        }

        public Transition param(@NonNull Object param) {
            params.add(param);
            return this;
        }
    }

    public void easyGo(@NonNull final Activity activity, @NonNull final String activityPath, int requestCode) {
        easyGo(activity, activityPath, new Intent(), requestCode);
    }

    public void easyGo(@NonNull final Activity activity, @NonNull final String activityPath, @NonNull final Intent intent, int requestCode) {
        easyGo(activityPath, cls -> {
            intent.setComponent(new ComponentName(activity, cls));
            activity.startActivityForResult(intent, requestCode);
        });
    }

    public void easyGo(@NonNull final String activityPath) {
        easyGo(ResourceUtils.getContext(), activityPath);
    }

    public void easyGo(@NonNull final Context context, @NonNull final String activityPath) {
        easyGo(context, activityPath, new Intent());
    }

    public void easyGo(@NonNull final String activityPath, @NonNull Obtain<Intent> obtain) {
        easyGo(ResourceUtils.getContext(), activityPath, obtain);
    }

    public void easyGo(@NonNull final Context context, @NonNull final String activityPath, @NonNull Obtain<Intent> obtain) {
        easyGo(context, activityPath, obtain.obtain());
    }

    public void easyGo2(@NonNull final String activityPath, @NonNull Carry<Intent> carry) {
        easyGo(ResourceUtils.getContext(), activityPath, carry);
    }

    public void easyGo(@NonNull final Context context, @NonNull final String activityPath, @NonNull Carry<Intent> carry) {
        Intent intent = new Intent();
        carry.carry(intent);
        easyGo(context, activityPath, intent);
    }

    public void easyGo(@NonNull final Context context, @NonNull final String activityPath, @NonNull final Intent intent) {
        easyGo(activityPath, cls -> {
            intent.setComponent(new ComponentName(context, cls));
            if(!(context instanceof Activity)) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            context.startActivity(intent);
        });
    }

    public void easyGo(@NonNull final String activityPath, @NonNull final Carry<Class<?>> carry) {
        Nulls.of(mEasyGoActivity)
                .nullNext(this::getEasyGoActivity)
                .doNotNull(act -> mEasyGoActivity = act.obtainActivityPath())
                .justNext(mEasyGoActivity)
                .notNullNext(act -> act.get(activityPath))
                .doNotNull(carry::carry);
    }

    private IEasyGoActivity getEasyGoActivity() {
        String className = PATH_AUTOWIRED + EASY_GO + CLASS_SEPARATOR + EasyGoActivity.class.getSimpleName();
        try {
            return  (IEasyGoActivity) Class.forName(className).getConstructor().newInstance();
        } catch (IllegalAccessException
                | InstantiationException
                | InvocationTargetException
                | NoSuchMethodException
                | ClassNotFoundException e) {
            LogHelper.of(TAG).always().debug().throwable(e).print();
        }
        return null;
    }

    public static void easyGo(@NonNull final Class<?> cls) {
        easyGo(ResourceUtils.getContext(), cls);
    }

    public static void easyGo(@NonNull final Context context, @NonNull final Class<?> cls) {
        easyGo(context, new Intent(context, cls));
    }

    public static void easyGo(@NonNull final Class<?> cls, @NonNull Carry<Intent> carry) {
        easyGo(ResourceUtils.getContext(), cls, carry);
    }

    public static void easyGo(@NonNull final Context context, @NonNull final Class<?> cls, @NonNull Carry<Intent> carry) {
        Intent intent = new Intent(context, cls);
        carry.carry(intent);
        easyGo(context, intent);
    }

    public static void easyGo(@NonNull final Context context, @NonNull final Intent intent) {
        if(!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    public static void easyGo(@NonNull final Activity activity, @NonNull final Class<?> cls, int requestCode) {
        easyGo(activity, new Intent(activity, cls), requestCode);
    }

    public static void easyGo(@NonNull final Activity activity, @NonNull final Class<?> cls, int requestCode, @NonNull Carry<Intent> carry) {
        Intent intent = new Intent(activity, cls);
        carry.carry(intent);
        easyGo(activity, intent, requestCode);
    }

    public static void easyGo(@NonNull final Activity activity, @NonNull final Intent intent, int requestCode) {
        activity.startActivityForResult(intent, requestCode);
    }
}
