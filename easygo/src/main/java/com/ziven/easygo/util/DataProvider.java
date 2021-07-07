package com.ziven.easygo.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * @author Ziven
 * @date 2021/5/29
 */
public final class DataProvider<T> {

    private List<T> mList;

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

    public List<T> getList() {
        return mList;
    }

    public DataProvider<T> addList(@Nullable final List<T> list) {
        Condition.of(mList).doFalse(() -> setList(list))
                .and(list).doTrue(() -> Objects.requireNonNull(mList)
                .addAll(Objects.requireNonNull(list)));
        return this;
    }

    public DataProvider<T> add(@Nullable final T data) {
        Condition.of(mList).and(data).doTrue(() -> Objects.requireNonNull(mList).add(data));
        return this;
    }

    public DataProvider<T> addListNoDuplicates(@Nullable final List<T> list) {
        Condition.of(list).doTrue(() -> EasyUtils.forEach(Objects.requireNonNull(list), this::addNoDuplicates));
        return this;
    }

    public DataProvider<T> addNoDuplicates(@Nullable final T data) {
        Condition.of(mList).and(data).ofData(Objects.requireNonNull(mList), Objects.requireNonNull(data)).doFalse(() -> add(data));
        return this;
    }

    public DataProvider<T> sort(@Nullable Comparator<T> comparator) {
        Condition.of(mList).doTrue(() -> Collections.sort( Objects.requireNonNull(mList), comparator));
        return this;
    }

    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Nullable
    public T getFirstData() {
        return getData(0);
    }

    @Nullable
    public T getLastData() {
        return getData(getCount() - 1);
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
            return Objects.requireNonNull(mList).remove(Objects.requireNonNull(data));
        }
        return false;
    }

    public boolean remove(@NonNull List<T> list) {
        if(Condition.of(list).and(mList).isTrue()) {
            return Objects.requireNonNull(mList).removeAll(Objects.requireNonNull(list));
        }
        return false;
    }

    public void clear() {
        Condition.of(mList).doTrue(() -> Objects.requireNonNull(mList).clear());
    }
}
