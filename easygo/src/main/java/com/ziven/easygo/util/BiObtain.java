package com.ziven.easygo.util;

import androidx.annotation.Keep;

/**
 * @author Ziven
 */
@Keep
public interface BiObtain<R1, R2> extends Obtain<BiNulls<R1, R2>> {

    /**
     * Obtain
     * @return BiNulls
     */
    @Override
    BiNulls<R1, R2> obtain();
}
