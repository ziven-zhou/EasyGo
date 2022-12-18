package com.ziven.easygo.ui;

import android.view.View;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

/**
 * @author zhiyuan.zhou
 */
@Keep
public interface OnItemClickListener<T> {

    /**
     * On item click
     * @param data Data
     * @param position Position
     */
    void onItemClick(T data, int position);

    /**
     * On item click
     * @param view View
     * @param data Data
     * @param position Position
     */
    default void onItemClick(@NonNull View view, T data, int position) {
        onItemClick(data, position);
    }

}
