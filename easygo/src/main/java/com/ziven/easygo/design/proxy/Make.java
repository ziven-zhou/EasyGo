package com.ziven.easygo.design.proxy;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Ziven
 */
@Keep
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Make {
    @NonNull Class<? extends Workshop<?>> workshop();
}
