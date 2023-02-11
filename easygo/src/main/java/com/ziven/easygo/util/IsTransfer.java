package com.ziven.easygo.util;

import androidx.annotation.Keep;

/**
 * @author Ziven
 */
@Keep
@FunctionalInterface
public interface IsTransfer<V> extends Transfer<Boolean, V> {
    /**
     * value -> true or false or null
     * @param value Value
     * @return true or false or null
     */
    @Override
    Boolean transfer(V value);
}
