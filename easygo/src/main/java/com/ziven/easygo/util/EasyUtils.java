package com.ziven.easygo.util;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author :zhiyuan.zhou
 * @date :2021/5/27
 */
@Keep
public final class EasyUtils {

    private static final String TAG = "EasyUtils";
    public static final String EMPTY = "";

    private EasyUtils() {
    }

    @NonNull
    public static <T> T newIfNull(T instance, @NonNull Obtain<T> obtain) {
        return instance == null ? obtain.obtain() : instance;
    }

    @NonNull
    public static <T> T newIfNull(T instance, @NonNull Class<T> cls) {
        return instance == null ? Objects.requireNonNull(newInstance(cls)) : instance;
    }

    @NonNull
    public static <T> T newIfNull(T instance, String cls) {
        return instance == null ? Objects.requireNonNull(newInstance(cls)) : instance;
    }

    @NonNull
    public static <T> T instanceDo(@NonNull Object target, @NonNull Class<T> cls) {
        if(instanceOf(target, cls)) {
            return transition(target);
        }
        throw new ClassCastException("Target is:" + target + ",Class is:" + cls);
    }

    public static <T> boolean instanceDo(@NonNull Object target, @NonNull Class<T> cls, @NonNull Carry<T> carry) {
        if(instanceOf(target, cls)) {
            carry.carry(transition(target));
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public static <T> T transition(@Nullable Object target) {
        return (T) target;
    }

    public static <T> T transition(@NonNull Class<T> cls, @Nullable Object target) {
        try {
            return cls.cast(target);
        } catch(ClassCastException e) {
            LogHelper.of(TAG).always().throwable(e).print();
        }
        return null;
    }

    public static <T> boolean instanceOf(@Nullable Object target, @NonNull Class<T> cls) {
        return cls.isInstance(target);
    }

    @Nullable
    public static <T> T newInstance(@NonNull Class<T> cls) {
        try {
            return cls.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            LogHelper.of(TAG).always().throwable(e).print();
        }
        return null;
    }

    @Nullable
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(String clsName) {
        if(TextUtils.isEmpty(clsName)) {
            return null;
        }
        try {
            return (T) Class.forName(clsName).newInstance();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> void forEach(@Nullable Collection<T> collection, @NonNull Carry<T> carry) {
        if(collection == null || collection.isEmpty()) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            collection.forEach(carry::carry);
        } else {
            for(T data : collection) {
                carry.carry(data);
            }
        }
    }

    public static <T> void forEachBreak(@Nullable Collection<T> collection, @NonNull Transfer<Boolean, T> transfer) {
        if(collection == null || collection.isEmpty()) {
            return;
        }
        for(T data : collection) {
            if(!transfer.transfer(data)) {
                break;
            }
        }
    }

    public static <T> void forEach(@Nullable List<T> list, @NonNull BiCarry<T, Integer> carry) {
        if(list == null || list.isEmpty()) {
            return;
        }
        for(int i=0; i<list.size(); i++) {
            carry.carry(list.get(i), i);
        }
    }

    public static <T> void forEachBreak(@Nullable List<T> list, @NonNull BiTransfer<Boolean, T, Integer> transfer) {
        if(list == null || list.isEmpty()) {
            return;
        }
        for(int i=0; i<list.size(); i++) {
            if(!transfer.transfer(list.get(i), i)) {
                break;
            }
        }
    }

    public static <K, V> void forEach(@Nullable Map<K, V> map, @NonNull Carry<V> carry) {
        if(map == null || map.isEmpty()) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            map.forEach((key, value) -> carry.carry(value));
        } else {
            for (V value : map.values()) {
                carry.carry(value);
            }
        }
    }

    public static <K, V> void forEachKey(@Nullable Map<K, V> map, @NonNull Carry<K> carry) {
        if(map == null || map.isEmpty()) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            map.forEach((key, value) -> carry.carry(key));
        } else {
            for (K key : map.keySet()) {
                carry.carry(key);
            }
        }
    }

    public static <K, V> void forEach(@Nullable Map<K, V> map, @NonNull BiCarry<K, V> carry) {
        if(map == null || map.isEmpty()) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            map.forEach(carry::carry);
        } else {
            for (Map.Entry<K, V> entry : map.entrySet()) {
                carry.carry(entry.getKey(), entry.getValue());
            }
        }
    }

    public static <K, V> void forEachBreak(@Nullable Map<K, V> map, @NonNull BiTransfer<Boolean, K, V> transfer) {
        if(map == null || map.isEmpty()) {
            return;
        }
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if(!transfer.transfer(entry.getKey(), entry.getValue())) {
                return;
            }
        }
    }

    public static <T> void forEach(@Nullable T[] values, @NonNull Carry<T> carry) {
        if(values == null || values.length == 0) {
            return;
        }
        for(T data : values) {
            carry.carry(data);
        }
    }

    public static <T> void forEachBreak(@Nullable T[] values, @NonNull Transfer<Boolean, T> transfer) {
        if(values == null || values.length == 0) {
            return;
        }
        for(T data : values) {
            if(!transfer.transfer(data)) {
                break;
            }
        }
    }

    public static <T> void forEach(@Nullable T[] values, @NonNull BiCarry<T, Integer> carry) {
        if(values == null || values.length == 0) {
            return;
        }
        for (int i=0; i<values.length; i++) {
            carry.carry(values[i], i);
        }
    }

    public static <T> void forEachBreak(@Nullable T[] values, @NonNull BiTransfer<Boolean, T, Integer> transfer) {
        if(values == null || values.length == 0) {
            return;
        }
        for (int i=0; i<values.length; i++) {
            if(!transfer.transfer(values[i], i)) {
                break;
            }
        }
    }

    public static <T, R> ArrayList<R> transfer(@NonNull List<T> list, @NonNull Transfer<R, T> transfer) {
        final ArrayList<R> returnList = new ArrayList<>(list.size());
        EasyUtils.forEach(list, data -> returnList.add(transfer.transfer(data)));
        return returnList;
    }

    @SafeVarargs
    @NonNull
    public static <T> ArrayList<T> newList(@NonNull T... values) {
        final ArrayList<T> list = new ArrayList<>(values.length);
        forEach(values, (Carry<T>) list::add);
        return list;
    }

    @NonNull
    public static <T> ArrayList<T> newList() {
        return new ArrayList<>();
    }

    @NonNull
    public static <K, V> HashMap<K, V> newMap() {
        return new HashMap<>(8);
    }

    @NonNull
    public static <K, V> HashMap<K, V> newMap(@NonNull K key, @NonNull V value) {
        final HashMap<K, V> map = new HashMap<>(8);
        map.put(key, value);
        return map;
    }

    @SafeVarargs
    @NonNull
    public static <K, V> HashMap<K, V> newMap(@NonNull Pair<K, V>... pairs) {
        final HashMap<K, V> map = new HashMap<>(8);
        forEach(pairs, pair -> map.put(pair.first, pair.second));
        return map;
    }

    public static <T> void addNoDuplicates(@NonNull final List<T> list, T data) {
        Condition.of(list.contains(data)).doFalse(() -> list.add(data));
    }

    public static  <T> void addNoDuplicates(@NonNull final List<T> list, @NonNull final List<T> add) {
        forEach(add, data -> addNoDuplicates(list, data));
    }

    public static <T> void addValue(@Nullable List<T> list, T data) {
        Condition.ofJust(list).and(data).doTrue(() -> Objects.requireNonNull(list).add(data));
    }

    @Nullable
    public static <T> T getValue(@Nullable List<T> list, int position) {
        return list != null && position >= 0 && position < list.size() ? list.get(position): null;
    }

    public static <K, V> void addValue(@Nullable Map<K, V> map, @Nullable K key, @Nullable V value) {
        Condition.ofJust(map).and(key).and(value).doTrue(() -> Objects.requireNonNull(map).put(key, value));
    }

    public static <K, V> void removeValue(@Nullable Map<K, V> map, @Nullable K key) {
        Condition.of(map).and(key).doTrue(() -> Objects.requireNonNull(map).remove(key));
    }

    @Nullable
    public static <K, V> V getValue(@Nullable Map<K, V> map, @Nullable K key) {
        return Condition.of(map).and(key).isTrue() ? Objects.requireNonNull(map).get(key) : null;
    }

    public static boolean notNull(@Nullable Object object) {
        return Condition.of(object).isTrue();
    }

    public static boolean isNull(@Nullable Object object) {
        return Condition.of(object).isFalse();
    }

    public static <T> boolean notEmpty(@Nullable Collection<T> collection) {
        return Condition.of(collection).isTrue();
    }

    public static <T> boolean isEmpty(@Nullable Collection<T> collection) {
        return Condition.of(collection).isFalse();
    }

    public static <K, V> boolean notEmpty(@Nullable Map<K, V> map) {
        return Condition.of(map).isTrue();
    }

    public static <K, V> boolean isEmpty(@Nullable Map<K, V> map) {
        return Condition.of(map).isFalse();
    }

    public static boolean notEmpty(@Nullable String s) {
        return Condition.of(s).isTrue();
    }

    public static boolean isEmpty(@Nullable String s) {
        return Condition.of(s).isFalse();
    }

    public static <T> boolean hasValue(@Nullable Collection<T> collection, @Nullable T value) {
        return collection != null && value != null && collection.contains(value);
    }

    public static <K, V> boolean hasValue(@Nullable Map<K, V> map, @Nullable V value) {
        return map != null && value != null && map.containsValue(value);
    }

    public static <K, V> boolean hasKey(@Nullable Map<K, V> map, @Nullable K key) {
        return map != null && key != null && map.containsKey(key);
    }

    public static void trueEnd1(@NonNull final Conditions condition, @NonNull final Boolean... conditions) {
        trueEnd2(condition, conditions);
    }

    public static void trueEnd2(@NonNull final Conditions condition, @NonNull final Boolean[] conditions) {
        forEachBreak(conditions, (c, p) -> {
            if(c) {
                conditions(condition, p);
                return false;
            }
            return true;
        });
    }

    public static void falseEnd1(@NonNull final Conditions condition, @NonNull final Boolean... conditions) {
        falseEnd2(condition, conditions);
    }

    public static void falseEnd2(@NonNull final Conditions condition, @NonNull final Boolean[] conditions) {
        forEachBreak(conditions, (c, p) -> {
            if(!c) {
                conditions(condition, p);
                return false;
            }
            return true;
        });
    }

    public static void trueAll1(@NonNull final Conditions condition, @NonNull final Boolean... conditions) {
        trueAll2(condition, conditions);
    }

    public static void trueAll2(@NonNull final Conditions condition, @NonNull final Boolean[] conditions) {
        forEachBreak(conditions, (c, p) -> {
            if(c) {
                conditions(condition, p);
            }
            return true;
        });
    }

    public static void falseAll1(@NonNull final Conditions condition, @NonNull final Boolean... conditions) {
        falseAll2(condition, conditions);
    }

    public static void falseAll2(@NonNull final Conditions condition, @NonNull final Boolean[] conditions) {
        forEachBreak(conditions, (c, p) -> {
            if(!c) {
                conditions(condition, p);
            }
            return true;
        });
    }

    public static void typeConditions(@NonNull final TypeConditions condition, Object value) {
        typeConditions(new Conditions() {
            @Override
            public void condition1() {
                condition.condition1(EasyUtils.transition(value));
            }

            @Override
            public void condition2() {
                condition.condition2(EasyUtils.transition(value));
            }

            @Override
            public void condition3() {
                condition.condition3(EasyUtils.transition(value));
            }

            @Override
            public void condition4() {
                condition.condition4(EasyUtils.transition(value));
            }

            @Override
            public void condition5() {
                condition.condition5(EasyUtils.transition(value));
            }

            @Override
            public void condition6() {
                condition.condition6(EasyUtils.transition(value));
            }

            @Override
            public void condition7() {
                condition.condition7(EasyUtils.transition(value));
            }

            @Override
            public void condition8() {
                condition.condition8(EasyUtils.transition(value));
            }

            @Override
            public void condition9() {
                condition.condition9(EasyUtils.transition(value));
            }

            @Override
            public void other() {
                condition.other(value);
            }
        }, value);
    }

    public static void typeConditions(@NonNull final Conditions condition, Object value) {
        if(value instanceof String) {
            conditions(condition, Conditions.CONDITION1);
        } else if(value instanceof Boolean) {
            conditions(condition, Conditions.CONDITION2);
        } else if(value instanceof Integer) {
            conditions(condition, Conditions.CONDITION3);
        } else if(value instanceof Long) {
            conditions(condition, Conditions.CONDITION4);
        } else if(value instanceof Float) {
            conditions(condition, Conditions.CONDITION5);
        } else if(value instanceof Double) {
            conditions(condition, Conditions.CONDITION6);
        } else if(value instanceof Set<?>) {
            conditions(condition, Conditions.CONDITION7);
        } else if(value instanceof Map<?, ?>) {
            conditions(condition, Conditions.CONDITION8);
        } else if(value instanceof List<?>) {
            conditions(condition, Conditions.CONDITION9);
        }  else {
            conditions(condition, Conditions.OTHER);
        }
    }

    public static void conditions(@NonNull final Conditions condition, int position) {
        condition.condition(position);
        switch (position) {
            case Conditions.CONDITION1:
                condition.condition1();
                break;
            case Conditions.CONDITION2:
                condition.condition2();
                break;
            case Conditions.CONDITION3:
                condition.condition3();
                break;
            case Conditions.CONDITION4:
                condition.condition4();
                break;
            case Conditions.CONDITION5:
                condition.condition5();
                break;
            case Conditions.CONDITION6:
                condition.condition6();
                break;
            case Conditions.CONDITION7:
                condition.condition7();
                break;
            case Conditions.CONDITION8:
                condition.condition8();
                break;
            case Conditions.CONDITION9:
                condition.condition9();
                break;
            default:
                condition.other();
                break;
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public static <T extends RecyclerView.ViewHolder> void notifyDataSetChanged(@Nullable RecyclerView.Adapter<T> adapter) {
        Nulls.of(adapter).doNotNull(RecyclerView.Adapter::notifyDataSetChanged);
    }

    public static void runSafety(@Nullable Runnable runnable) {
        runSafety(runnable, "runSafety");
    }

    public static void runSafety(@Nullable Runnable runnable, @NonNull String logTag) {
        if(runnable != null) {
            try {
                runnable.run();
            } catch (Throwable th) {
                LogHelper.of(logTag).always().throwable(th).print();
            }
        }
    }

    @NonNull
    public static <T> Runnable carryToRunnable(@NonNull Carry<T> carry, T data) {
        return () -> carry.carry(data);
    }

    @NonNull
    public static <T> Runnable carryToRunnable(@NonNull Carry<T> carry) {
        return carryToRunnable(carry, null);
    }

    @NonNull
    public static <R, V> Runnable transferToRunnable(@NonNull Transfer<R, V> transfer, V data) {
        return () -> transfer.transfer(data);
    }

    @NonNull
    public static <R, V> Runnable transferToRunnable(@NonNull Transfer<R, V> transfer) {
        return transferToRunnable(transfer, null);
    }

    @NonNull
    public static <R, V> Obtain<R> transferToObtain(@NonNull Transfer<R, V> transfer, V data) {
        return () -> transfer.transfer(data);
    }

    @NonNull
    public static <R, V> Obtain<R> transferToObtain(@NonNull Transfer<R, V> transfer) {
        return transferToObtain(transfer, null);
    }

    @NonNull
    public static <R, V> Carry<R> transferToCarry(@NonNull Transfer<R, V> transfer, V data) {
        return value -> transfer.transfer(data);
    }

    @NonNull
    public static <R, V> Carry<R> transferToCarry(@NonNull Transfer<R, V> transfer) {
        return transferToCarry(transfer, null);
    }

    @NonNull
    public static <R, V> Transfer<R, V> runnableToTransfer(@NonNull Runnable runnable) {
        return value -> {
            runnable.run();
            return null;
        };
    }

    @NonNull
    public static <R, V> Transfer<R, V> carryToTransfer(@NonNull Carry<V> carry) {
        return value -> {
            carry.carry(value);
            return null;
        };
    }

    @NonNull
    public static <R, V> Transfer<R, V> obtainToTransfer(@NonNull Obtain<R> obtain) {
        return value -> obtain.obtain();
    }

    @NonNull
    public static <T> Carry<T> runnableToCarry(@NonNull Runnable runnable) {
        return data -> runnable.run();
    }

    @NonNull
    public static <T> Obtain<T> runnableToObtain(@NonNull Runnable runnable, T data) {
        return () -> {
            runnable.run();
            return data;
        };
    }

    @NonNull
    public static <T> Obtain<T> runnableToObtain(@NonNull Runnable runnable) {
        return runnableToObtain(runnable, null);
    }

    @NonNull
    public static <T> Carry<T> obtainToCarry1(@NonNull Obtain<T> obtain) {
        return obtainToCarry(obtain);
    }

    @NonNull
    public static <C, O> Carry<C> obtainToCarry(@NonNull Obtain<O> obtain) {
        return data -> obtain.obtain();
    }

    @NonNull
    public static <T> Obtain<T> carryToObtain(@NonNull Carry<T> carry, T data) {
        return carryToObtain(carry, data, data);
    }

    @NonNull
    public static <O, C> Obtain<O> carryToObtain(@NonNull Carry<C> carry) {
        return carryToObtain(carry, null, null);
    }

    @NonNull
    public static <O, C> Obtain<O> carryToObtain1(@NonNull Carry<C> carry, C data) {
        return carryToObtain(carry, null, data);
    }

    @NonNull
    public static <O, C> Obtain<O> carryToObtain2(@NonNull Carry<C> carry, O data) {
        return carryToObtain(carry, data, null);
    }

    @NonNull
    public static <O, C> Obtain<O> carryToObtain(@NonNull Carry<C> carry,
                                                 O obtainData,
                                                 C carryData) {
        return () -> {
            carry.carry(carryData);
            return obtainData;
        };
    }
}
