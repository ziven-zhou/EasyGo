package com.ziven.easygo.design.mvp;

import androidx.annotation.Nullable;

/**
 * @author Ziven
 * @date 2021/5/28
 */
public class OneData extends AbstractOneData {

    public static OneData of() {
        return new OneData();
    }

    public static OneData of(@Nullable Object data) {
        return new OneData().setOneData(data);
    }

    public static OneData of(int code) {
        return new OneData().setOneCode(code);
    }

    public static OneData of(int code, @Nullable Object data) {
        return new OneData().setOneCode(code).setOneData(data);
    }

    private OneData() {
    }
}
