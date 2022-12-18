package com.ziven.easygo.util;

import androidx.annotation.Keep;

/**
 * @author :zhiyuan.zhou
 * @date :2021/7/1
 */
@Keep
@FunctionalInterface
public interface MultiCarry<T> {
    /**
     * MultiCarry
     * @param values Values
     */
    void carry(T[] values);
}
