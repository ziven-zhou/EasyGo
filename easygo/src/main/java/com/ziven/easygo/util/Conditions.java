package com.ziven.easygo.util;

/**
 * @author :zhiyuan.zhou
 * @date :2021/7/1
 */
public interface Conditions {

    int CONDITION1 = 0;
    int CONDITION2 = 1;
    int CONDITION3 = 2;
    int CONDITION4 = 3;
    int CONDITION5 = 4;
    int CONDITION6 = 5;
    int CONDITION7 = 6;
    int CONDITION8 = 7;
    int CONDITION9 = 8;
    int OTHER = 9;

    /**
     * {@link EasyUtils#conditions(Conditions, int)}
     * {@link Conditions#CONDITION1}
     * {@link Conditions#CONDITION2}
     * {@link Conditions#CONDITION3}
     * {@link Conditions#CONDITION4}
     * {@link Conditions#CONDITION5}
     * {@link Conditions#CONDITION6}
     * {@link Conditions#CONDITION7}
     * {@link Conditions#CONDITION8}
     * {@link Conditions#CONDITION9}
     * {@link Conditions#OTHER}
     * @param condition Condition
     */
    default void condition(int condition) {}

    /**
     * Condition1
     * {@link EasyUtils#conditions(Conditions, int)}
     * {@link Conditions#CONDITION1}
     * {@link EasyUtils#typeConditions(Conditions, Object)}
     * {@link String}
     */
    default void condition1() {}
    /**
     * Condition2
     * {@link EasyUtils#conditions(Conditions, int)}
     * {@link Conditions#CONDITION2}
     * {@link EasyUtils#typeConditions(Conditions, Object)}
     * {@link Boolean}
     */
    default void condition2() {}
    /**
     * Condition3
     * {@link EasyUtils#conditions(Conditions, int)}
     * {@link Conditions#CONDITION3}
     * {@link EasyUtils#typeConditions(Conditions, Object)}
     * {@link Integer}
     */
    default void condition3() {}
    /**
     * Condition4
     * {@link EasyUtils#conditions(Conditions, int)}
     * {@link Conditions#CONDITION4}
     * {@link EasyUtils#typeConditions(Conditions, Object)}
     * {@link Long}
     */
    default void condition4() {}
    /**
     * Condition5
     * {@link EasyUtils#conditions(Conditions, int)}
     * {@link Conditions#CONDITION5}
     * {@link EasyUtils#typeConditions(Conditions, Object)}
     * {@link Float}
     */
    default void condition5() {}
    /**
     * Condition6
     * {@link EasyUtils#conditions(Conditions, int)}
     * {@link Conditions#CONDITION6}
     * {@link EasyUtils#typeConditions(Conditions, Object)}
     * {@link Double}
     */
    default void condition6() {}
    /**
     * Condition7
     * {@link EasyUtils#conditions(Conditions, int)}
     * {@link Conditions#CONDITION7}
     * {@link EasyUtils#typeConditions(Conditions, Object)}
     * {@link java.util.Set}
     */
    default void condition7() {}
    /**
     * Condition8
     * {@link EasyUtils#conditions(Conditions, int)}
     * {@link Conditions#CONDITION8}
     * {@link EasyUtils#typeConditions(Conditions, Object)}
     * {@link java.util.Map}
     */
    default void condition8() {}
    /**
     * Condition9
     * {@link EasyUtils#conditions(Conditions, int)}
     * {@link Conditions#CONDITION9}
     * {@link EasyUtils#typeConditions(Conditions, Object)}
     * {@link java.util.List}
     */
    default void condition9() {}
    /**
     * Condition Other
     */
    default void other() {}
}
