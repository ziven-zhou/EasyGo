package com.ziven.easygo.util;

import androidx.annotation.Keep;

/**
 * @author Ziven
 * @date 2021/6/22
 */
@Keep
@FunctionalInterface
public interface Obtain<R> {
    /**
     * Obtain value
     * @return Obtain value
     */
    R obtain();
}
