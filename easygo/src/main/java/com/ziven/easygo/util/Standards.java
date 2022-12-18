package com.ziven.easygo.util;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

/**
 * @author zhiyuan.zhou
 */
@Keep
public final class Standards<T> {

    private final T data;

    public static <T> Standards<T> with(T data) {
        return new Standards<T>(data, null);
    }

    public static <T> Standards<T> with(T data, @NonNull Obtain<T> obtain) {
        return new Standards<T>(data, obtain);
    }

    private Standards(T data, Obtain<T> obtain) {
        if(data == null && obtain != null) {
            this.data = obtain.obtain();
        } else {
            this.data = data;
        }
    }

    public Standards<T> run(@NonNull Runnable runnable) {
        runnable.run();
        return this;
    }

    public Standards<T> accept(@NonNull Carry<T> carry) {
        if(data != null) {
            carry.carry(data);
        }
        return this;
    }

    public <V> Standards<T> accept(V value, @NonNull BiCarry<T, V> biCarry) {
        if(data != null) {
            biCarry.carry(data, value);
        }
        return this;
    }

    public <R> R apply(@NonNull Transfer<R, T> transfer) {
        return transfer.transfer(data);
    }

    public <R> R get(@NonNull Obtain<R> obtain) {
        return obtain.obtain();
    }

    public T value() {
        return data;
    }
}
