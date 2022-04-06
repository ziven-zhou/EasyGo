package com.ziven.easygo.ui;

/**
 * @author zhiyuan.zhou
 */
public interface OnItemClickListener<T> {

    /**
     * On item click
     * @param data Data
     * @param position Position
     */
    void onItemClick(T data, int position);

}
