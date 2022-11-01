package com.ziven.easygo.util;

import java.util.List;

/**
 * @author Ziven
 */
@FunctionalInterface
public interface ListMultiCarry<T> {
    /**
     * ListMultiCarry
     * @param values Values
     */
    void carry(List<T> values);
}
