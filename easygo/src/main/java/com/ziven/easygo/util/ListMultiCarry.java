package com.ziven.easygo.util;

import androidx.annotation.Keep;

import java.util.List;

/**
 * @author Ziven
 */
@Keep
@FunctionalInterface
public interface ListMultiCarry<T> {
    /**
     * ListMultiCarry
     * @param values Values
     */
    void carry(List<T> values);
}
