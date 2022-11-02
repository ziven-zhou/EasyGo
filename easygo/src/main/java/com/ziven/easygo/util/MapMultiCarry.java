package com.ziven.easygo.util;

import java.util.Map;

/**
 * @author Ziven
 */
public interface MapMultiCarry<K, V> {
    /**
     * MapMultiCarry
     * @param values Values
     */
    void carry(Map<K, V> values);
}
