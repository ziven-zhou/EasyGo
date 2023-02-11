package com.ziven.easygo.util;

import androidx.annotation.NonNull;

/**
 * @author Ziven
 */
public final class MultiNulls {

    private static final int COUNT = 3;

    private final Object[] values;
    private final int count;

    public static MultiNulls of(Object... values) {
        return of(values.length).setValues(values);
    }

    public static MultiNulls of(int count) {
        return new MultiNulls(count);
    }

    private MultiNulls(int count) {
        if(count < COUNT) {
            throw new RuntimeException("Use BiNulls or Nulls.");
        }
        this.count = count;
        this.values = new Object[count];
    }

    public MultiNulls setValues(Object... values) {
        if(values.length != count) {
            throw new RuntimeException("Count is illegal, count=" + count + " values.length=" + values.length);
        }
        System.arraycopy(values, 0, this.values, 0, count);
        return this;
    }

    public Object[] values() {
        return values;
    }

    public <T> T first() {
        return get(0);
    }

    public <T> T second() {
        return get(1);
    }

    public <T> T last() {
        return get(count - 1);
    }

    public <T> T get(int position) {
        return position >= 0 && position < count
                ? EasyUtils.transition(values[position])
                : null;
    }

    public <T> Nulls<T> firstNulls() {
        return Nulls.of(first());
    }

    public <T> Nulls<T> secondNulls() {
        return Nulls.of(second());
    }

    public <T> Nulls<T> lastNulls() {
        return Nulls.of(last());
    }

    public <T> Nulls<T> getNulls(int position) {
        return Nulls.of(get(position));
    }

    public <T1, T2> BiNulls<T1, T2> getBiNulls(int position1, int position2) {
        return BiNulls.of(get(position1), get(position2));
    }

    public <T1, T2> BiNulls<T1, T2> firstSecond() {
        return getBiNulls(0, 1);
    }

    public <T1, T2> BiNulls<T1, T2> secondLast() {
        return getBiNulls(1, count - 1);
    }

    public <T1, T2> BiNulls<T1, T2> firstLast() {
        return getBiNulls(0, count - 1);
    }

    public <T1, T2, T3> void firstSecondLastNonNull(@NonNull Carry<T1> firstCarry,
                                                    @NonNull Carry<T2> secondCarry,
                                                    @NonNull Carry<T3> lastCarry) {
        T1 first = first();
        if(first != null) {
            firstCarry.carry(first);
        }
        T2 second = second();
        if(second != null) {
            secondCarry.carry(second);
        }
        T3 last = last();
        if(last != null) {
            lastCarry.carry(last);
        }
    }

    public <T1, T2, T3> void firstSecondLast(@NonNull Carry<T1> firstCarry,
                                             @NonNull Carry<T2> secondCarry,
                                             @NonNull Carry<T3> lastCarry) {
        firstCarry.carry(first());
        secondCarry.carry(second());
        lastCarry.carry(last());
    }

    public void forEachNonNull(@NonNull Carry<Object> carry) {
        forEach(value -> {
            if(value != null) {
                carry.carry(value);
            }
        });
    }

    public void forEachNulls(@NonNull Carry<Nulls<Object>> carry) {
        EasyUtils.forEach(values, value -> carry.carry(Nulls.of(value)));
    }

    public void forEach(@NonNull Carry<Object> carry) {
        EasyUtils.forEach(values, carry);
    }

    public <T> void forEachTypeNonNull(@NonNull Carry<T> carry) {
        forEach(value -> {
            if(value != null) {
                carry.carry(EasyUtils.transition(value));
            }
        });
    }

    public <T> void forEachTypeNulls(@NonNull Carry<Nulls<T>> carry) {
        EasyUtils.forEach(values, value -> carry.carry(Nulls.of(EasyUtils.transition(value))));
    }

    public <T> void forEachType(@NonNull Carry<T> carry) {
        EasyUtils.forEach(values, value -> carry.carry(EasyUtils.transition(value)));
    }

    public boolean isAllNull() {
        boolean isAllNull = true;
        for(Object value : values) {
            if(value != null) {
                isAllNull = false;
                break;
            }
        }
        return isAllNull;
    }

    public boolean isNoneNull() {
        boolean isNoneNull = true;
        for(Object value : values) {
            if(value == null) {
                isNoneNull = false;
                break;
            }
        }
        return isNoneNull;
    }

    public boolean hasNull() {
        for(Object value : values) {
            if(value == null) {
                return true;
            }
        }
        return false;
    }

    public boolean hasNotNull() {
        for(Object value : values) {
            if(value != null) {
                return true;
            }
        }
        return false;
    }
}
