package com.ziven.easygo.util;

import androidx.annotation.Keep;

/**
 * @author Ziven
 * @date 2021/5/29
 */
@Keep
@FunctionalInterface
public interface Carry<T> {
    /**
     * Carry
     * @param data Data
     */
    void carry(T data);
}
