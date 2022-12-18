package com.ziven.easygo.util;

import androidx.annotation.Keep;

import java.util.Map;

/**
 * @author Ziven
 */
@Keep
public interface MapMultiCarry<K, V> {
    /**
     * MapMultiCarry
     * @param values Values
     */
    void carry(Map<K, V> values);
}
