package com.ziven.easygo.util;

import androidx.annotation.Keep;

/**
 * @author Ziven
 */
@Keep
@FunctionalInterface
public interface IntTransfer<V> extends Transfer<Integer, V>{
    /**
     * value -> int or null
     * @param value Value
     * @return int or null
     */
    @Override
    Integer transfer(V value);
}
