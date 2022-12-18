package com.ziven.easygo.design.funnel;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author Ziven
 * @date 2021/5/29
 */
@Keep
public class OneFilterGrain extends AbstractFilterGrain {

    private OneFilterGrain() {
    }

    private OneFilterGrain(@Nullable Object grain) {
        super(grain);
    }

    public static OneFilterGrain of() {
        return new OneFilterGrain();
    }

    public static OneFilterGrain of(@NonNull Object grain) {
        return new OneFilterGrain(grain);
    }
}
