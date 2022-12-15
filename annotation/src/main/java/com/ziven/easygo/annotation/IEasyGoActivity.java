package com.ziven.easygo.annotation;

import androidx.annotation.Keep;

import java.util.Map;

/**
 * @author :zhiyuan.zhou
 * @date :2021/6/21
 */
@Keep
public interface IEasyGoActivity {
    /**
     * Obtain Activity Path
     * @return Activity Path
     */
    @SuppressWarnings("rawtypes")
    Map<String, Class> obtainActivityPath();
}
