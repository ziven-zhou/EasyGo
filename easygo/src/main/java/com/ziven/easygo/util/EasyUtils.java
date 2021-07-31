package com.ziven.easygo.util;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Pair;

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
public final class EasyUtils {

    private static final String TAG = "EasyUtils";
    public static final String EMPTY = "";

    private EasyUtils() {
    }

    @NonNull
    public static <T> T instanceDo(@Nullable Object target, @NonNull Class<T> cls) {
        if(instanceOf(target, cls)) {
            return transition(target);
        }
        throw new ClassCastException("Target is:" + target + ",Class is:" + cls);
    }

    @NonNull
    public static <T> void instanceDo(@Nullable Object target, @NonNull Class<T> cls, @NonNull Carry<T> carry) {
        if(instanceOf(target, cls)) {
            carry.carry(transition(target));
        }
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

    public static <T> T newInstance(@NonNull Class<T> cls) {
        try {
            return cls.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            LogHelper.of(TAG).always().throwable(e).print();
        }
        return null;
    }

    public static <T> void forEach(@NonNull Collection<T> collection, @NonNull Carry<T> carry) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            collection.forEach(carry::carry);
        } else {
            for(T data : collection) {
                carry.carry(data);
            }
        }
    }

    public static <T> void forEachBreak(@NonNull Collection<T> collection, @NonNull Transfer<Boolean, T> transfer) {
        for(T data : collection) {
            if(!transfer.transfer(data)) {
                break;
            }
        }
    }

    public static <T> void forEach(@NonNull List<T> list, @NonNull BiCarry<T, Integer> carry) {
        for(int i=0; i<list.size(); i++) {
            carry.carry(list.get(i), i);
        }
    }

    public static <T> void forEachBreak(@NonNull List<T> list, @NonNull BiTransfer<Boolean, T, Integer> transfer) {
        for(int i=0; i<list.size(); i++) {
            if(!transfer.transfer(list.get(i), i)) {
                break;
            }
        }
    }

    public static <K, V> void forEach(@NonNull Map<K, V> map, @NonNull Carry<V> carry) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            map.forEach((key, value) -> carry.carry(value));
        } else {
            for (V value : map.values()) {
                carry.carry(value);
            }
        }
    }

    public static <K, V> void forEachKey(@NonNull Map<K, V> map, @NonNull Carry<K> carry) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            map.forEach((key, value) -> carry.carry(key));
        } else {
            for (K key : map.keySet()) {
                carry.carry(key);
            }
        }
    }

    public static <K, V> void forEach(@NonNull Map<K, V> map, @NonNull BiCarry<K, V> carry) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            map.forEach(carry::carry);
        } else {
            for (Map.Entry<K, V> entry : map.entrySet()) {
                carry.carry(entry.getKey(), entry.getValue());
            }
        }
    }

    public static <T> void forEach(@NonNull T[] values, @NonNull Carry<T> carry) {
        for(T data : values) {
            carry.carry(data);
        }
    }

    public static <T> void forEachBreak(@NonNull T[] values, @NonNull Transfer<Boolean, T> transfer) {
        for(T data : values) {
            if(!transfer.transfer(data)) {
                break;
            }
        }
    }

    public static <T> void forEach(@NonNull T[] values, @NonNull BiCarry<T, Integer> carry) {
        for (int i=0; i<values.length; i++) {
            carry.carry(values[i], i);
        }
    }

    public static <T> void forEachBreak(@NonNull T[] values, @NonNull BiTransfer<Boolean, T, Integer> transfer) {
        for (int i=0; i<values.length; i++) {
            if(!transfer.transfer(values[i], i)) {
                break;
            }
        }
    }

    @SafeVarargs
    @NonNull
    public static <T> ArrayList<T> newList(@NonNull T... values) {
        final ArrayList<T> list = new ArrayList<>(values.length);
        forEach(values, (Carry<T>) list::add);
        return list;
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

    @Nullable
    public static <T> T getValue(@Nullable List<T> list, int position) {
        return list != null && position >= 0 && position < list.size() ? list.get(position): null;
    }

    @Nullable
    public static <K, V> V getValue(@Nullable Map<K, V> map, @Nullable K key) {
        return Condition.of(map).and(key).isTrue() ? Objects.requireNonNull(map).get(key) : null;
    }

    public static <T> boolean isEmpty(@Nullable Collection<T> collection) {
        return Condition.of(collection).isFalse();
    }

    public static <K, V> boolean isEmpty(@Nullable Map<K, V> map) {
        return Condition.of(map).isFalse();
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
}
