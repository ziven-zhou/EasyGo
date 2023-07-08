package com.ziven.easygo.design.proxy;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import com.ziven.easygo.util.Carry;
import com.ziven.easygo.util.EasyUtils;
import com.ziven.easygo.util.ThreadUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Ziven
 */
@Keep
public final class Proxy<T> {

    private static final int NUMBER_0 = 0;
    private static final int NUMBER_1 = 1;
    private static final int NUMBER_2 = 2;
    private static final int NUMBER_3 = 3;
    private static final int NUMBER_4 = 4;
    private static final int NUMBER_5 = 5;
    private static final int NUMBER_6 = 6;

    private final Map<Class<?>, Workshop<?>> workshops;

    public static <T> T proxy(@NonNull Class<T> cls) {
        Proxy<T> proxy = new Proxy<>();
        return proxy.create(cls);
    }

    private Proxy() {
        workshops = new ConcurrentHashMap<>();
    }

    private T create(@NonNull Class<T> cls) {
        Object target =
                java.lang.reflect.Proxy.newProxyInstance(
                        cls.getClassLoader(),
                        new Class<?>[] { cls },
                        (proxy, method, args)
                                -> method.getDeclaringClass() == Object.class
                                ? method.invoke(proxy, args)
                                : call(getWorkshop(method), args)
                );
        return EasyUtils.transitionSafety(target);
    }

    private Workshop<?> getWorkshop(@NonNull Method method) {
        Make make = method.getAnnotation(Make.class);
        if(make == null) {
            return null;
        }
        Class<? extends Workshop<?>> cls = make.workshop();
        Workshop<?> workshop = workshops.get(cls);
        if(workshop != null) {
            return workshop;
        }
        workshop = EasyUtils.newInstance(cls);
        if(workshop == null) {
            return null;
        }
        workshops.put(cls, workshop);
        return workshop;
    }

    private <R> Call<R> call(Workshop<?> workshop, Object[] args) {
        return new Call<R>() {
            @Override
            public R call() {
                return make(workshop, args);
            }

            @Override
            public void call(@NonNull Carry<R> carry) {
                ThreadUtils.workerToMain(
                        () -> make(workshop, args),
                        data -> carry.carry(EasyUtils.transitionSafety(data))
                );
            }
        };
    }

    private <R> R make(Workshop<?> workshop, Object[] args) {
        if(workshop == null) {
            return null;
        }
        if(args == null || args.length == NUMBER_0) {
            return EasyUtils.transitionSafety(
                    workshop.make()
            );
        }
        if(args.length == NUMBER_1 && workshop instanceof Workshop.P1) {
            return EasyUtils.transitionSafety(
                    ((Workshop.P1<?, ?>) workshop).make(
                            EasyUtils.transitionSafety(args[NUMBER_0])
                    )
            );
        }
        if(args.length == NUMBER_2 && workshop instanceof Workshop.P2) {
            return EasyUtils.transitionSafety(
                    ((Workshop.P2<?, ?, ?>) workshop).make(
                            EasyUtils.transitionSafety(args[NUMBER_0]),
                            EasyUtils.transitionSafety(args[NUMBER_1])
                    )
            );
        }
        if(args.length == NUMBER_3 && workshop instanceof Workshop.P3) {
            return EasyUtils.transitionSafety(
                    ((Workshop.P3<?, ?, ?, ?>) workshop).make(
                            EasyUtils.transitionSafety(args[NUMBER_0]),
                            EasyUtils.transitionSafety(args[NUMBER_1]),
                            EasyUtils.transitionSafety(args[NUMBER_2])
                    )
            );
        }
        if(args.length == NUMBER_4 && workshop instanceof Workshop.P4) {
            return EasyUtils.transitionSafety(
                    ((Workshop.P4<?, ?, ?, ?, ?>) workshop).make(
                            EasyUtils.transitionSafety(args[NUMBER_0]),
                            EasyUtils.transitionSafety(args[NUMBER_1]),
                            EasyUtils.transitionSafety(args[NUMBER_2]),
                            EasyUtils.transitionSafety(args[NUMBER_3])
                    )
            );
        }
        if(args.length == NUMBER_5 && workshop instanceof Workshop.P5) {
            return EasyUtils.transitionSafety(
                    ((Workshop.P5<?, ?, ?, ?, ?, ?>) workshop).make(
                            EasyUtils.transitionSafety(args[NUMBER_0]),
                            EasyUtils.transitionSafety(args[NUMBER_1]),
                            EasyUtils.transitionSafety(args[NUMBER_2]),
                            EasyUtils.transitionSafety(args[NUMBER_3]),
                            EasyUtils.transitionSafety(args[NUMBER_4])
                    )
            );
        }
        if(args.length == NUMBER_6 && workshop instanceof Workshop.P6) {
            return EasyUtils.transitionSafety(
                    ((Workshop.P6<?, ?, ?, ?, ?, ?, ?>) workshop).make(
                            EasyUtils.transitionSafety(args[NUMBER_0]),
                            EasyUtils.transitionSafety(args[NUMBER_1]),
                            EasyUtils.transitionSafety(args[NUMBER_2]),
                            EasyUtils.transitionSafety(args[NUMBER_3]),
                            EasyUtils.transitionSafety(args[NUMBER_4]),
                            EasyUtils.transitionSafety(args[NUMBER_5])
                    )
            );
        }
        return EasyUtils.transitionSafety(workshop.make());
    }
}
