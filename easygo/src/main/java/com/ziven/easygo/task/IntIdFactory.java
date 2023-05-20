package com.ziven.easygo.task;

import androidx.annotation.NonNull;

import com.ziven.easygo.util.Factory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Ziven
 */
public class IntIdFactory implements Factory<Integer> {

    private final AtomicInteger counter;

    public static IntIdFactory newFactory() {
        return new IntIdFactory();
    }

    private IntIdFactory() {
        counter = new AtomicInteger(0);
    }

    @NonNull
    @Override
    public Integer create() {
        return counter.getAndIncrement();
    }
}
