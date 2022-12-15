package com.ziven.easygo.annotation;

import androidx.annotation.Keep;

import java.util.List;

/**
 * @author :Ziven
 * @date :2021/6/2
 */
@Keep
public interface IEasyGoMethod {
    /**
     * Obtain EasyGoType
     * @return EasyGoType
     */
    List<EasyGoType> obtainEasyGoType();


    /**
     * Call method
     * @param target Target
     * @param pathName Path
     * @param params Params
     * @return Return
     */
    Object easyGoMethod(Object target, String pathName, List<Object> params);
}
