package com.ziven.easygo.util;

import androidx.annotation.NonNull;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author zhiyuan.zhou
 */
public interface Standard {

    /**
     * Run
     * @param runnable Runnable
     * @param <T> Myself
     * @return Myself
     */
    default  <T extends Standard> T run(@NonNull Runnable runnable) {
        runnable.run();
        return parent();
    }

    /**
     * accept
     * @param consumer Consumer
     * @param <T> Myself
     * @return Myself
     */
    default <T extends Standard> T accept(@NonNull Consumer<T> consumer) {
        T parent = parent();
        consumer.accept(parent);
        return parent;
    }

    /**
     * accept
     * @param value Value
     * @param consumer Consumer
     * @param <T1> Myself
     * @param <T2> Value
     * @return Myself
     */
    default <T1 extends Standard, T2> T1 accept(T2 value, @NonNull BiConsumer<T1, T2> consumer) {
        T1 parent = parent();
        consumer.accept(parent, value);
        return parent;
    }

    /**
     * apply
     * @param function Function
     * @param <T> Myself
     * @param <R> Value
     * @return Value
     */
    default <T extends Standard, R> R apply(@NonNull Function<T, R> function) {
        return function.apply(parent());
    }

    /**
     * get
     * @param supplier Supplier
     * @param <T> Value
     * @return Value
     */
    default <T> T get(@NonNull Supplier<T> supplier) {
        return supplier.get();
    }

    /**
     * parent
     * @param <T> Myself
     * @return Myself
     */
    @SuppressWarnings("unchecked")
    default <T extends Standard> T parent() {
        return (T) this;
    }
}
