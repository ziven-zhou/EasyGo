package com.ziven.easygo.util;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Ziven
 */
@Keep
public interface TypeConditions {

    /**
     * Condition1
     * {@link EasyUtils#conditions(Conditions, int)}
     * {@link Conditions#CONDITION1}
     * {@link EasyUtils#typeConditions(Conditions, Object)}
     * {@link String}
     * @param value Value
     */
    default void condition1(@NonNull String value) {}
    /**
     * Condition2
     * {@link EasyUtils#conditions(Conditions, int)}
     * {@link Conditions#CONDITION2}
     * {@link EasyUtils#typeConditions(Conditions, Object)}
     * {@link Boolean}
     * @param value Value
     */
    default void condition2(boolean value) {}
    /**
     * Condition3
     * {@link EasyUtils#conditions(Conditions, int)}
     * {@link Conditions#CONDITION3}
     * {@link EasyUtils#typeConditions(Conditions, Object)}
     * {@link Integer}
     * @param value Value
     */
    default void condition3(int value) {}
    /**
     * Condition4
     * {@link EasyUtils#conditions(Conditions, int)}
     * {@link Conditions#CONDITION4}
     * {@link EasyUtils#typeConditions(Conditions, Object)}
     * {@link Long}
     * @param value Value
     */
    default void condition4(long value) {}
    /**
     * Condition5
     * {@link EasyUtils#conditions(Conditions, int)}
     * {@link Conditions#CONDITION5}
     * {@link EasyUtils#typeConditions(Conditions, Object)}
     * {@link Float}
     * @param value Value
     */
    default void condition5(float value) {}
    /**
     * Condition6
     * {@link EasyUtils#conditions(Conditions, int)}
     * {@link Conditions#CONDITION6}
     * {@link EasyUtils#typeConditions(Conditions, Object)}
     * {@link Double}
     * @param value Value
     */
    default void condition6(double value) {}
    /**
     * Condition7
     * {@link EasyUtils#conditions(Conditions, int)}
     * {@link Conditions#CONDITION7}
     * {@link EasyUtils#typeConditions(Conditions, Object)}
     * {@link java.util.Set}
     * @param value Value
     */
    default void condition7(@NonNull Set<?> value) {}
    /**
     * Condition8
     * {@link EasyUtils#conditions(Conditions, int)}
     * {@link Conditions#CONDITION8}
     * {@link EasyUtils#typeConditions(Conditions, Object)}
     * {@link java.util.Map}
     * @param value Value
     */
    default void condition8(@NonNull Map<?, ?> value) {}
    /**
     * Condition9
     * {@link EasyUtils#conditions(Conditions, int)}
     * {@link Conditions#CONDITION9}
     * {@link EasyUtils#typeConditions(Conditions, Object)}
     * {@link java.util.List}
     * @param value Value
     */
    default void condition9(@NonNull List<?> value) {}
    /**
     * Condition Other
     * @param value Value
     */
    default void other(Object value) {}
}
