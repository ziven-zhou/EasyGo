package com.ziven.easygo.util;

import android.view.View;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

/**
 * @author Ziven
 * @date 2021/5/29
 */
@Keep
public final class DataProvider<T> {

    private List<T> mList;

    public static <T> DataProvider<T> ofData(T data) {
        DataProvider<T> p = ofEmpty();
        p.add(data);
        return p;
    }

    public static <T> DataProvider<T> ofNull() {
        return new DataProvider<>(null);
    }

    public static <T> DataProvider<T> ofEmpty() {
        return new DataProvider<>(new ArrayList<>(8));
    }

    public static <T> DataProvider<T> of(@NonNull List<T> list) {
        return new DataProvider<>(list);
    }

    private DataProvider(@Nullable List<T> list) {
        mList = list;
    }

    public DataProvider<T> setList(@Nullable final List<T> list) {
        mList = list;
        return this;
    }

    @Nullable
    public List<T> getList() {
        return mList;
    }

    @NonNull
    public Nulls<List<T>> getListNulls() {
        return Nulls.of(getList());
    }

    @Nullable
    public ListIterator<T> listIterator() {
        return mList == null ? null : mList.listIterator();
    }

    @NonNull
    public Nulls<ListIterator<T>> listIteratorNulls() {
        return Nulls.of(listIterator());
    }

    @NonNull
    public List<T> getListIfNullObtainEmpty() {
        return getListIfNullObtain(EasyUtils::newList);
    }

    @NonNull
    public List<T> getListIfNullObtain(@NonNull Obtain<List<T>> list) {
        return mList != null ? mList : list.obtain();
    }

    public DataProvider<T> addList(@Nullable final List<T> list) {
        Condition.ofJust(mList)
                .doFalse(() -> setList(list))
                .and(list)
                .doTrue(() -> Objects.requireNonNull(mList).addAll(Objects.requireNonNull(list)));
        return this;
    }

    public DataProvider<T> add(@Nullable final T data) {
        Condition.ofJust(mList)
                .and(data)
                .doTrue(() -> Objects.requireNonNull(mList).add(data));
        return this;
    }

    public DataProvider<T> add(@Nullable final T data, int position) {
        Condition.ofJust(mList)
                .and(data)
                .ofMoreAndEqual(position, 0)
                .doTrue(() -> {
                    List<T> list = Objects.requireNonNull(mList);
                    if(position < list.size()) {
                        list.add(position, data);
                    } else {
                        list.add(data);
                    }
                });
        return this;
    }

    public DataProvider<T> replace(@Nullable final T data, int position) {
        Condition.ofJust(mList)
                .and(data)
                .ofMoreAndEqual(position, 0)
                .doTrue(() -> {
                    List<T> list = Objects.requireNonNull(mList);
                    if(position < list.size()) {
                        list.remove(position);
                        list.add(position, data);
                    } else {
                        list.add(data);
                    }
                });
        return this;
    }

    public DataProvider<T> addListNoDuplicates(@Nullable final List<T> list) {
        Condition.ofJust(list)
                .doTrue(() -> EasyUtils.forEach(Objects.requireNonNull(list), this::addNoDuplicates));
        return this;
    }

    public DataProvider<T> addNoDuplicates(@Nullable final T data) {
        Condition.ofJust(mList)
                .and(data)
                .ofData(Objects.requireNonNull(mList), Objects.requireNonNull(data))
                .doFalse(() -> add(data));
        return this;
    }

    public DataProvider<T> sort(@Nullable Comparator<T> comparator) {
        Condition.of(mList)
                .doTrue(() -> Collections.sort( Objects.requireNonNull(mList), comparator));
        return this;
    }

    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @NonNull
    public Nulls<T> getFirstDataNulls() {
        return Nulls.of(getFirstData());
    }

    @Nullable
    public T getFirstData() {
        return getData(0);
    }

    @NonNull
    public Nulls<T> getLastDataNulls() {
        return Nulls.of(getLastData());
    }

    @Nullable
    public T getLastData() {
        return getData(getCount() - 1);
    }

    @NonNull
    public Nulls<T> getDataNulls(int position) {
        return Nulls.of(getData(position));
    }

    @Nullable
    public T getData(int position) {
        if(Condition.ofFalse().ofMoreEqualAndLess(0, position, getCount()).isTrue()) {
            return mList.get(position);
        }
        return null;
    }

    @Nullable
    public T remove(int position) {
        if(Condition.ofFalse().ofMoreEqualAndLess(0, position, getCount()).isTrue()) {
            return mList.remove(position);
        }
        return null;
    }

    public boolean remove(T data) {
        if(Condition.of(data).and(mList).isTrue()) {
            return Objects
                    .requireNonNull(mList)
                    .remove(Objects.requireNonNull(data));
        }
        return false;
    }

    public boolean remove(@NonNull List<T> list) {
        if(Condition.of(list).and(mList).isTrue()) {
            return Objects
                    .requireNonNull(mList)
                    .removeAll(Objects.requireNonNull(list));
        }
        return false;
    }

    public void clear() {
        Condition.of(mList)
                .doTrue(() -> Objects.requireNonNull(mList).clear());
    }

    public DataProvider<T> forEach(@NonNull BiCarry<T, Integer> carry) {
        EasyUtils.forEach(getList(), carry);
        return this;
    }

    public DataProvider<T> forEach(@NonNull Carry<T> carry) {
        EasyUtils.forEach(getList(), carry);
        return this;
    }

    public DataProvider<T> forEachBreak(@NonNull BiTransfer<Boolean, T, Integer> transfer) {
        EasyUtils.forEachBreak(getList(), transfer);
        return this;
    }

    @NonNull
    public Nulls<T> findFirstNulls(@NonNull IsTransfer<T> transfer) {
        return Nulls.of(findFirst(transfer));
    }

    @Nullable
    public T findFirst(@NonNull IsTransfer<T> transfer) {
        if(mList != null) {
            for(T value : mList) {
                if(transfer.transfer(value)) {
                    return value;
                }
            }
        }
        return null;
    }

    @NonNull
    public List<T> findAll(@NonNull IsTransfer<T> transfer) {
        List<T> list = new ArrayList<>();
        if(mList != null) {
            for(T value : mList) {
                if(transfer.transfer(value)) {
                    list.add(value);
                }
            }
        }
        return list;
    }

    public DataProvider<T> notifyDataSetChanged(@Nullable RecyclerView.Adapter<?> adapter) {
        EasyUtils.notifyDataSetChanged(adapter);
        return this;
    }

    public <V extends View> ViewHelper<V> viewHelper(@NonNull V view) {
        return ViewHelper.create(view);
    }
}
