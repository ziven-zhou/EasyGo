package com.ziven.easygo.util;

import androidx.annotation.NonNull;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author zhiyuan.zhou
 */
public final class Standards<T> {

    private final T data;

    public static <T> Standards<T> with(T data) {
        return new Standards<T>(data, null);
    }

    public static <T> Standards<T> with(T data, @NonNull Supplier<T> supplier) {
        return new Standards<T>(data, supplier);
    }

    private Standards(T data, Supplier<T> supplier) {
        if(data == null && supplier != null) {
            this.data = supplier.get();
        } else {
            this.data = data;
        }
    }

    public Standards<T> run(@NonNull Runnable runnable) {
        runnable.run();
        return this;
    }

    public Standards<T> accept(@NonNull Consumer<T> consumer) {
        if(data != null) {
            consumer.accept(data);
        }
        return this;
    }

    public <V> Standards<T> accept(V value, @NonNull BiConsumer<T, V> consumer) {
        if(data != null) {
            consumer.accept(data, value);
        }
        return this;
    }

    public <R> R apply(@NonNull Function<T, R> function) {
        return function.apply(data);
    }

    public <R> R get(@NonNull Supplier<R> supplier) {
        return supplier.get();
    }

    public T value() {
        return data;
    }
}
