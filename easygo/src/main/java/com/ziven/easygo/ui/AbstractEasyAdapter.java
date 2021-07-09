package com.ziven.easygo.ui;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ziven.easygo.util.DataProvider;

/**
 * @author Ziven
 * @date 2021/5/29
 */
public abstract class AbstractEasyAdapter<D, T extends AbstractEasyViewHolder<D>> extends RecyclerView.Adapter<T> {

    private final DataProvider<D> mDataProvider;

    public AbstractEasyAdapter() {
        mDataProvider = DataProvider.ofEmpty();
    }

    @NonNull
    public DataProvider<D> getDataProvider() {
        return mDataProvider;
    }

    @Override
    public void onBindViewHolder(@NonNull T holder, int position) {
        holder.bindLayout(mDataProvider.getData(position), position);
    }

    @Override
    public int getItemCount() {
        return mDataProvider.getCount();
    }
}
