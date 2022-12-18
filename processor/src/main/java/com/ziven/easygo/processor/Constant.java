package com.ziven.easygo.processor;

import androidx.annotation.Keep;

import com.ziven.easygo.annotation.EasyGoActivity;
import com.ziven.easygo.annotation.EasyGoMethod;

/**
 * @author :zhiyuan.zhou
 * @date :2021/5/31
 */
@Keep
final class Constant {
    private Constant() {
    }

    static final Class<EasyGoMethod> ANNOTATION_CLASS = EasyGoMethod.class;
    static final Class<EasyGoActivity> ANNOTATION_CLASS_ACTIVITY = EasyGoActivity.class;

    static final String PROJECT = "EasyGo";
    static final String PREFIX_OF_LOGGER = PROJECT + "::Processor ";

    static final String CAN_NOT_MODIFY = "DO NOT EDIT THIS FILE!!! IT WAS GENERATED BY EasyGO";

    static final String CLASS_SEPARATOR = "$$";

    static final String METHOD_OBTAIN_EASY_GO_TYPE = "obtainEasyGoType";
    static final String METHOD_EASY_GO_METHOD = "easyGoMethod";
    static final String METHOD_OBTAIN_ACTIVITY_PATH = "obtainActivityPath";

    static final String PATH_ANNOTATION = "com.ziven.easygo.annotation";
    static final String PATH_AUTOWIRED = "com.ziven.easygo.autowired";

    static final String TYPE_EASY_GO_TYPE = "EasyGoType";
}
