package com.ziven.easygo.util;

import androidx.annotation.Keep;

/**
 * @author :zhiyuan.zhou
 * @date :2021/6/22
 */
@Keep
public interface Transfer<R, V> {
    /**
     * Transfer
     * @param value Value
     * @return Return Value
     */
    R transfer(V value);
}
