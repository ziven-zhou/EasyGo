package com.ziven.easygo.ui;

/**
 * @author Ziven
 * @date 2021/5/29
 */
public abstract class AbstractTypeAdapter<D extends AbstractTypeData, T extends AbstractEasyViewHolder<D>> extends AbstractEasyAdapter<D, T> {

    @Override
    public int getItemViewType(int position) {
        D data = getDataProvider().getData(position);
        return data == null ? AbstractTypeData.TYPE_NO : data.getViewType();
    }
}
