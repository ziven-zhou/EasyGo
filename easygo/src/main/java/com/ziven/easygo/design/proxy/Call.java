package com.ziven.easygo.design.proxy;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import com.ziven.easygo.util.Carry;

/**
 * @author Ziven
 */
@Keep
public interface Call<R> {

    /**
     * Call synchronous
     * @return Data
     */
    R call();


    /**
     * Call asynchronous
     * @param carry Data carry
     */
    void call(@NonNull Carry<R> carry);
}
