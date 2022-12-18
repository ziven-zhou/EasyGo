package com.ziven.easygo.ui;

import androidx.annotation.Keep;

/**
 * @author Ziven
 * @date 2021/5/29
 */
@Keep
public abstract class AbstractTypeAdapter<D extends AbstractTypeData, T extends AbstractEasyViewHolder<D>> extends AbstractEasyAdapter<D, T> {

    @Override
    public int getItemViewType(int position) {
        D data = getDataProvider().getData(position);
        return data == null ? AbstractTypeData.TYPE_NO : data.getViewType();
    }
}
