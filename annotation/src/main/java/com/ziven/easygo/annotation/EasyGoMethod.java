package com.ziven.easygo.annotation;

import androidx.annotation.Keep;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author :Ziven
 * @date :2021/6/2
 */
@Keep
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface EasyGoMethod {
    String path();
    EasyGoType.ThreadMode threadMode() default EasyGoType.ThreadMode.CURRENT;
}
