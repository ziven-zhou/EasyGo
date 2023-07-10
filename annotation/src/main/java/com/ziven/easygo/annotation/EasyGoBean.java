package com.ziven.easygo.annotation;

import androidx.annotation.Keep;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Ziven
 */
@Keep
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
@Repeatable(EasyGoBeans.class)
public @interface EasyGoBean {
    String name();
    String type() default "";
    String list() default "";
    String set() default "";
    String[] map() default {};
    String[] more() default {};
}
