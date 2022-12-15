package com.ziven.easygo.annotation;

import androidx.annotation.Keep;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author :zhiyuan.zhou
 * @date :2021/6/21
 */
@Keep
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface EasyGoActivity {
    String path();
}
