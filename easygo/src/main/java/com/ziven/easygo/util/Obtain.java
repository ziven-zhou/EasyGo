package com.ziven.easygo.util;

/**
 * @author Ziven
 * @date 2021/6/22
 */
@FunctionalInterface
public interface Obtain<R> {
    /**
     * Obtain value
     * @return Obtain value
     */
    R obtain();
}
