package com.ziven.easygo.util;

import androidx.annotation.Keep;

/**
 * @author Ziven
 */
@Keep
@FunctionalInterface
public interface StrTransfer<V> extends Transfer<String, V>{
    /**
     * value -> String or null
     * @param value Value
     * @return String or null
     */
    @Override
    String transfer(V value);
}
