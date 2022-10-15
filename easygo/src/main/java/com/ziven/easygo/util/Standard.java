package com.ziven.easygo.util;

import androidx.annotation.NonNull;

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
     * @param carry Carry
     * @param <T> Myself
     * @return Myself
     */
    default <T extends Standard> T accept(@NonNull Carry<T> carry) {
        T parent = parent();
        carry.carry(parent);
        return parent;
    }

    /**
     * accept
     * @param value Value
     * @param biCarry BiCarry
     * @param <T1> Myself
     * @param <T2> Value
     * @return Myself
     */
    default <T1 extends Standard, T2> T1 accept(T2 value, @NonNull BiCarry<T1, T2> biCarry) {
        T1 parent = parent();
        biCarry.carry(parent, value);
        return parent;
    }

    /**
     * apply
     * @param transfer Transfer
     * @param <T> Myself
     * @param <R> Value
     * @return Value
     */
    default <T extends Standard, R> R apply(@NonNull Transfer<R, T> transfer) {
        return transfer.transfer(parent());
    }

    /**
     * get
     * @param obtain Obtain
     * @param <T> Value
     * @return Value
     */
    default <T> T get(@NonNull Obtain<T> obtain) {
        return obtain.obtain();
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
