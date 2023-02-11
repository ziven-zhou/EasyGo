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

    private K first;
    private V second;

    public static <K, V> BiNulls<K, V> of(@Nullable K first, @Nullable V second) {
        return new BiNulls<>(first, second);
    }

    public static <K, V> BiNulls<K, V> of() {
        return of(null, null);
    }

    public static <K, V> BiNulls<K, V> ofFirst(@Nullable K first) {
        return of(first, null);
    }

    public static <K, V> BiNulls<K, V> ofSecond(@Nullable V second) {
        return of(null, second);
    }

    private BiNulls(@Nullable K first, @Nullable V second) {
        this.first = first;
        this.second = second;
    }

    public BiNulls<K, V> putFirst(K first) {
        this.first = first;
        return this;
    }

    public BiNulls<K, V> putSecond(V second) {
        this.second = second;
        return this;
    }

    public BiNulls<K, V> put(K first, V second) {
        this.first = first;
        this.second = second;
        return this;
    }

    /**
     * Do just
     * @param carry BiCarry
     * @return Myself
     */
    public BiNulls<K, V> doJust(@NonNull BiCarry<K, V> carry) {
        carry.carry(first, second);
        return this;
    }

    /**
     * first != null and second != null
     * @param carry BiCarry
     * @return Myself
     */
    public BiNulls<K, V> doNotNull(@NonNull BiCarry<K, V> carry) {
        Condition.of(first).and(second).doTrue(() -> carry.carry(CheckUtils.checkNull(first), CheckUtils.checkNull(second)));
        return this;
    }

    /**
     * first == null or second == null
     * @param runnable Runnable
     * @return Myself
     */
    public BiNulls<K, V> doNull(@NonNull Runnable runnable) {
        Condition.of(first).and(second).doFalse(runnable::run);
        return this;
    }

    /**
     * first != null do carry1, second != null carry2
     * @param carry1 Carry of first
     * @param carry2 Carry of second
     * @return Myself
     */
    public BiNulls<K, V> doNotNull(@NonNull Carry<K> carry1, @NonNull Carry<V> carry2) {
        Condition.of(first).doTrue(() -> carry1.carry(CheckUtils.checkNull(first)));
        Condition.of(second).doTrue(() -> carry2.carry(CheckUtils.checkNull(second)));
        return this;
    }

    /**
     * second == null
     * @return Nulls of first
     */
    @NonNull
    public Nulls<K> ofFirstWhenSecondNull() {
        return Condition.of(second).isFalse() ? ofFirst() : Nulls.of();
    }

    /**
     * first == null
     * @return Nulls of second
     */
    @NonNull
    public Nulls<V> ofSecondWhenFirstNull() {
        return Condition.of(first).isFalse() ? ofSecond() : Nulls.of();
    }

    @NonNull
    public Nulls<K> ofFirst() {
        return Nulls.of(first);
    }

    @NonNull
    public Nulls<V> ofSecond() {
        return Nulls.of(second);
    }

    @Nullable
    public K first() {
        return first;
    }

    @Nullable
    public V second() {
        return second;
    }

    /**
     * first != null and second != null
     * @return all not null
     */
    public boolean isNotNull() {
        return Condition.of(first).and(second).isTrue();
    }

    /**
     * first == null or second == null
     * @return one or two not null
     */
    public boolean isNull() {
        return Condition.of(first).and(second).not().isTrue();
    }

    /**
     * first == null and second == null
     * @return all null
     */
    public boolean isAllNull() {
        return Condition.of(first).or(second).isFalse();
    }

    /**
     * first == null and second != null
     * first != null and second == null
     * @return just one null
     */
    public boolean isOneNull() {
        return isNull() && !isAllNull();
    }
}
