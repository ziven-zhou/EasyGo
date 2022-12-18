package com.ziven.easygo.util;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;

/**
 * @author :zhiyuan.zhou
 * @date :2021/6/30
 */
@Keep
public final class CheckUtils {
    private CheckUtils() {
    }

    public static <T> T nullDef(@Nullable T value, @Nullable T def) {
        return Nulls.of(value).isNull() ? def : value;
    }

    public static <T> T checkNull(@Nullable T value) {
        if (value == null) {
            throw new NullPointerException("CheckUtils Null Value.");
        }
        return value;
    }

    public static <T> T checkNullDef(@Nullable T value, @Nullable T def) {
        return checkNull(nullDef(value, def));
    }

    public static <T> T equalDef(@Nullable Object value1, @Nullable Object value2, @Nullable T def1, @Nullable T def2) {
        return checkEqual(value1, value2) ? def1 : def2;
    }

    public static <T> T notEqualDef(@Nullable Object value1, @Nullable Object value2, @Nullable T def1, @Nullable T def2) {
        return checkEqual(value1, value2) ? def2 : def1;
    }

    public static <T> T equalDef(@Nullable Object value1, @Nullable Object value2, @Nullable T def) {
        return equalDef(value1, value2, def, null);
    }

    public static <T> T notEqualDef(@Nullable Object value1, @Nullable Object value2, @Nullable T def) {
        return notEqualDef(value1, value2, def, null);
    }

    public static <T> T equalDef(@Nullable T value1, @Nullable T value2) {
        return equalDef(value1, value2, value1);
    }

    public static <T> T notEqualDef1(@Nullable T value1, @Nullable T value2) {
        return notEqualDef(value1, value2, value1);
    }

    public static <T> T notEqualDef2(@Nullable T value1, @Nullable T value2) {
        return notEqualDef(value1, value2, value2);
    }

    public static <T> T checkEqualDef(@Nullable Object value1, @Nullable Object value2, @Nullable T def1, @Nullable T def2) {
        return checkNull(equalDef(value1, value2, def1 , def2));
    }

    public static <T> T checkNotEqualDef(@Nullable Object value1, @Nullable Object value2, @Nullable T def1, @Nullable T def2) {
        return checkNull(notEqualDef(value1, value2, def1 , def2));
    }

    public static <T> T checkEqualDef(@Nullable Object value1, @Nullable Object value2, @Nullable T def) {
        return checkEqualDef(value1, value2, def, null);
    }

    public static <T> T checkNotEqualDef(@Nullable Object value1, @Nullable Object value2, @Nullable T def) {
        return checkNotEqualDef(value1, value2, def, null);
    }

    public static <T> T checkEqualDef(@Nullable T value1, @Nullable T value2) {
        return checkEqualDef(value1, value2, value1);
    }

    public static <T> T checkNotEqualDef1(@Nullable T value1, @Nullable T value2) {
        return checkNotEqualDef(value1, value2, value1);
    }

    public static <T> T checkNotEqualDef2(@Nullable T value1, @Nullable T value2) {
        return checkNotEqualDef(value1, value2, value2);
    }

    public static boolean checkEqual(@Nullable Object value1, @Nullable Object value2) {
        if(value1 == value2) {
            return value1 != null;
        }
        return value1 != null && value1.equals(value2);
    }

    public static int minusDef(int value, int def) {
        return value < 0 ? def : value;
    }

    public static int zeroDef(int value, int def) {
        return value <= 0 ? def : value;
    }

    public static int checkMinus(int value) {
        if(value < 0) {
            throw new RuntimeException("CheckUtils Minus Value.");
        }
        return value;
    }

    public static int checkZero(int value) {
        if(value <= 0) {
            throw new RuntimeException("CheckUtils Zero Value.");
        }
        return value;
    }

    public static int checkMinusDef(int value, int def) {
        return checkMinus(minusDef(value, def));
    }

    public static int checkZeroDef(int value, int def) {
        return checkZero(zeroDef(value, def));
    }

    public static float minusDef(float value, float def) {
        return value < 0 ? def : value;
    }

    public static float zeroDef(float value, float def) {
        return value <= 0 ? def : value;
    }

    public static float checkMinus(float value) {
        if(value < 0) {
            throw new RuntimeException("CheckUtils Minus Value.");
        }
        return value;
    }

    public static float checkZero(float value) {
        if(value <= 0) {
            throw new RuntimeException("CheckUtils Zero Value.");
        }
        return value;
    }

    public static float checkMinusDef(float value, float def) {
        return checkMinus(minusDef(value, def));
    }

    public static float checkZeroDef(float value, float def) {
        return checkZero(zeroDef(value, def));
    }
}
