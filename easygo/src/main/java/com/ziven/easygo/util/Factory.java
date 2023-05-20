package com.ziven.easygo.util;

import androidx.annotation.NonNull;

/**
 * @author Ziven
 */
public interface Factory<T> extends Obtain<T> {

    /**
     * Obtain
     * @return T
     */
    @NonNull
    @Override
    default T obtain() {
        return create();
    }

    /**
     * Create
     * @return T
     */
    @NonNull
    T create();
}
