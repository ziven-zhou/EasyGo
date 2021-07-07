package com.ziven.easygo.util;

/**
 * @author Ziven
 * @date 2021/5/29
 */
@FunctionalInterface
public interface BiCarry<K, V> {
    /**
     * BiCarry
     * @param key Key
     * @param value Value
     */
    void carry(K key, V value);
}
