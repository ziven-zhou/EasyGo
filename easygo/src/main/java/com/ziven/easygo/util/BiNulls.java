package com.ziven.easygo.util;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author Ziven
 * @date 2021/7/31
 */
@Keep
public final class BiNulls<K, V> {

    private final K first;
    private final V second;

    public static <K, V> BiNulls<K, V> of(@Nullable K first, @Nullable V second) {
        return new BiNulls<>(first, second);
    }

    private BiNulls(@Nullable K first, @Nullable V second) {
        this.first = first;
        this.second = second;
    }

    public BiNulls<K, V> doNotNull(@NonNull BiCarry<K, V> carry) {
        Condition.of(first).and(second).doTrue(() -> carry.carry(CheckUtils.checkNull(first), CheckUtils.checkNull(second)));
        return this;
    }

    public BiNulls<K, V> doNull(@NonNull Runnable runnable) {
        Condition.of(first).and(second).doFalse(() -> runnable.run());
        return this;
    }

    public BiNulls<K, V> doNotNull(@NonNull Carry<K> carry1, @NonNull Carry<V> carry2) {
        Condition.of(first).doTrue(() -> carry1.carry(CheckUtils.checkNull(first)));
        Condition.of(second).doTrue(() -> carry2.carry(CheckUtils.checkNull(second)));
        return this;
    }

    public Nulls<K> ofFirstWhenSecondNull() {
        return Condition.of(second).isFalse() ? ofFirst() : Nulls.of();
    }

    public Nulls<V> ofSecondWhenFirstNull() {
        return Condition.of(first).isFalse() ? ofSecond() : Nulls.of();
    }

    public Nulls<K> ofFirst() {
        return Nulls.of(first);
    }

    public Nulls<V> ofSecond() {
        return Nulls.of(second);
    }

    public boolean isNotNull() {
        return Condition.of(first).and(second).isTrue();
    }

    public boolean isNull() {
        return Condition.of(first).and(second).not().isTrue();
    }

    public boolean isAllNull() {
        return Condition.of(first).or(second).isFalse();
    }

    public boolean isOneNull() {
        return isNull() && !isAllNull();
    }
}
