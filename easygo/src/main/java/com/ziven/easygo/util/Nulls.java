package com.ziven.easygo.util;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

/**
 * @author Ziven
 * @date 2021/5/30
 */
@Keep
public final class Nulls<T> {

    private static final Nulls<?> NULL = of(null);

    @Nullable
    private final T mObject;

    private Nulls(@Nullable T object) {
        mObject = object;
    }

    public static <T> Nulls<T> of() {
        return EasyUtils.transition(NULL);
    }

    public static <T> Nulls<T> of(@Nullable T object) {
        return new Nulls<>(object);
    }

    public Nulls<T> nullNewThis(@NonNull Nee<T> nee) {
        if(Condition.of(mObject).isFalse()) {
            return of(nee.nee());
        }
        return this;
    }

    public <N> Nulls<N> justNext(@Nullable N object) {
        return of(object);
    }

    public <N> Nulls<N> nullNext(@NonNull Nee<N> nee) {
        if(Condition.of(mObject).isFalse()) {
            return of(nee.nee());
        }
        return EasyUtils.transition(NULL);
    }

    public <O> Nulls<O> notNullNext(@NonNull Oee<T, O> oee) {
        if(Condition.of(mObject).isTrue()) {
            return of(oee.oee(Objects.requireNonNull(mObject)));
        }
        return EasyUtils.transition(NULL);
    }

    public Nulls<T> doNull(@NonNull Condition.Doo doo) {
        Condition.of(mObject).doFalse(doo);
        return this;
    }

    public Nulls<T> doNotNull(@NonNull Goo<T> goo) {
        Condition.of(mObject).doTrue(() -> goo.goo(Objects.requireNonNull(mObject)));
        return this;
    }

    public boolean isNull() {
        return Condition.of(mObject).isFalse();
    }

    public boolean isNotNull() {
        return Condition.of(mObject).isTrue();
    }

    public T just() {
        return mObject;
    }

    @FunctionalInterface
    public interface Goo<T> {
        /**
         * Do something
         * @param object Object
         */
        void goo(@NonNull T object);
    }

    @FunctionalInterface
    public interface Oee<T, O> {
        /**
         * Do something
         * @param object Object
         * @return  Object
         */
        O oee(@NonNull T object);
    }

    @FunctionalInterface
    public interface Nee<O> {
        /**
         * Do something
         * @return  Object
         */
        O nee();
    }
}
