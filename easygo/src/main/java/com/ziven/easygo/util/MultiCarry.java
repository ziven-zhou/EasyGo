package com.ziven.easygo.util;

/**
 * @author :zhiyuan.zhou
 * @date :2021/7/1
 */
@FunctionalInterface
public interface MultiCarry<T> {
    /**
     * MultiCarry
     * @param values Values
     */
    void carry(T[] values);
}
