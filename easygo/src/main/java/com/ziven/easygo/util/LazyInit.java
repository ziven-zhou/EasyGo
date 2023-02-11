package com.ziven.easygo.util;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

/**
 * @author Ziven
 */
@Keep
public final class LazyInit<T> {

    private Obtain<T> obtain;
    private Class<T> cls;
    private volatile T value;

    public static <V>  LazyInit<V> lazy(@NonNull Obtain<V> obtain) {
        return new LazyInit<>(obtain, null);
    }

    public static <V> LazyInit<V> lazy(@NonNull Class<V> cls) {
        return new LazyInit<>(null, cls);
    }

    public static <V> LazyInit<V> lazy(@Nullable Obtain<V> obtain,
                                       @Nullable Class<V> cls) {
        return new LazyInit<>(obtain, cls);
    }

    private LazyInit(@Nullable Obtain<T> obtain, @Nullable Class<T> cls) {
        this.obtain = obtain;
        this.cls = cls;
    }

    @NonNull
    public T value() {
        if(value == null) {
            synchronized (LazyInit.class) {
                if(value == null) {
                    value = newInstance();
                }
                obtain = null;
                cls = null;
            }
        }
        return Objects.requireNonNull(value);
    }

    private T newInstance() {
        if(obtain != null) {
            return obtain.obtain();
        }
        if(cls != null) {
            return EasyUtils.newInstance(cls);
        }
        return null;
    }
}
