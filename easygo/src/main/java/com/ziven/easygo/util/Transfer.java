package com.ziven.easygo.util;

/**
 * @author :zhiyuan.zhou
 * @date :2021/6/22
 */
public interface Transfer<R, V> {
    /**
     * Transfer
     * @param value Value
     * @return Return Value
     */
    R transfer(V value);
}
