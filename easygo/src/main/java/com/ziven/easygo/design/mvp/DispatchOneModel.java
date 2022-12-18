package com.ziven.easygo.design.mvp;

import android.content.Context;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;

import com.ziven.easygo.util.Conditions;

import java.util.Map;

/**
 * @author Ziven
 * @date 2022/1/12
 */
@Keep
public abstract class DispatchOneModel extends OneModel implements Conditions {

    @Override
    protected void obtainOneData(@Nullable Context c, @Nullable Map<Object, Object> params) {
        int condition = obtainCondition(c, params);
        switch (condition) {
            case Conditions.CONDITION1:
                condition1();
                break;
            case Conditions.CONDITION2:
                condition2();
                break;
            case Conditions.CONDITION3:
                condition3();
                break;
            case Conditions.CONDITION4:
                condition4();
                break;
            case Conditions.CONDITION5:
                condition5();
                break;
            case Conditions.CONDITION6:
                condition6();
                break;
            case Conditions.CONDITION7:
                condition7();
                break;
            case Conditions.CONDITION8:
                condition8();
                break;
            case Conditions.CONDITION9:
                condition9();
                break;
            default:
                other();
                break;
        }
        condition(condition);
    }

    /**
     * Obtain Condition
     * @param c Context
     * @param params Params
     * @return Condition
     */
    protected abstract int obtainCondition(@Nullable Context c, @Nullable Map<Object, Object> params);
}
