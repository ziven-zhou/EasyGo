package com.ziven.easygo.util;

import androidx.annotation.Keep;

/**
 * @author :zhiyuan.zhou
 * @date :2021/7/1
 */
@Keep
@FunctionalInterface
public interface BiTransfer<R, V1, V2> {
    /**
     * BiTransfer
     * @param value1 Value1
     * @param value2 Value2
     * @return Return Value
     */
    R transfer(V1 value1, V2 value2);
}
