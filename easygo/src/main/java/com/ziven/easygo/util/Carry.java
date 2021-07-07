package com.ziven.easygo.util;

/**
 * @author Ziven
 * @date 2021/5/29
 */
@FunctionalInterface
public interface Carry<T> {
    /**
     * Carry
     * @param data Data
     */
    void carry(T data);
}
