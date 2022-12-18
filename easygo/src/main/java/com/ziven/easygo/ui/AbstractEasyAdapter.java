package com.ziven.easygo.ui;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.ziven.easygo.util.DataProvider;
import com.ziven.easygo.util.EasyUtils;

/**
 * @author Ziven
 * @date 2021/5/29
 */
@Keep
public abstract class AbstractEasyAdapter<D, T extends AbstractEasyViewHolder<D>> extends RecyclerView.Adapter<T> {

    private final DataProvider<D> mDataProvider;
    private OnItemClickListener<D> mListener;
    private boolean isSetListener = false;

    public AbstractEasyAdapter() {
        mDataProvider = DataProvider.ofEmpty();
    }

    @NonNull
    public DataProvider<D> getDataProvider() {
        return mDataProvider;
    }

    @Override
    public void onBindViewHolder(@NonNull T holder, int position) {
        D data = mDataProvider.getData(position);
        holder.setListener(mListener);
        if(isSetListener) {
            holder.setOnItemClickListener(holder.itemView, data, position);
        }
        holder.bindLayout(data, position);
    }

    @Override
    public int getItemCount() {
        return mDataProvider.getCount();
    }

    public <MY extends AbstractEasyAdapter<D, T>> MY setListener(@Nullable OnItemClickListener<D> listener) {
        return setListener(true, listener);
    }

    public <MY extends AbstractEasyAdapter<D, T>> MY setListener(boolean setListener, @Nullable OnItemClickListener<D> listener) {
        isSetListener = setListener;
        mListener = listener;
        return EasyUtils.transition(this);
    }
}
