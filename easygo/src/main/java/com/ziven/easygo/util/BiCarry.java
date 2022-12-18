package com.ziven.easygo.util;

import androidx.annotation.Keep;

/**
 * @author Ziven
 * @date 2021/5/29
 */
@Keep
@FunctionalInterface
public interface BiCarry<K, V> {
    /**
     * BiCarry
     * @param key Key
     * @param value Value
     */
    void carry(K key, V value);
}
