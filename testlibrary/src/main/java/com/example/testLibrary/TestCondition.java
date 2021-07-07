package com.example.testLibrary;

import android.util.Log;

import com.ziven.easygo.util.Condition;

/**
 * @author :zhiyuan.zhou
 * @date :2020/8/19
 */
public class TestCondition {

    public static TestCondition of() {
        return new TestCondition();
    }

    private TestCondition() {
    }

    public void test() {
        Condition.ofFalse().doJust(() -> {
            Log.d("TestCondition","doJust()");
        }).doFalse(() -> {
            Log.d("TestCondition","doFalse()");
        }).doTrue(() -> {
            Log.d("TestCondition","doTrue()");
        }).toTrue().doThen(() -> {
            Log.d("TestCondition","doThen(1)");
            return true;
        }, () -> {
            Log.d("TestCondition","doThen(2)");
            return true;
        }, () -> {
            Log.d("TestCondition","doThen(3)");
            return false;
        }, () -> {
            Log.d("TestCondition","doThen(4)");
            return true;
        }).doTrue(() -> {
            Log.d("TestCondition","after doThen(), i am true");
        }).doFalse(() -> {
            Log.d("TestCondition","after doThen(), i am false");
        }).toTrue().then(() -> {
            Log.d("TestCondition","then(1)");
            return true;
        }).then(() -> {
            Log.d("TestCondition","then(2)");
            return true;
        }).then(() -> {
            Log.d("TestCondition","then(3)");
            return false;
        }).then(() -> {
            Log.d("TestCondition","then(4)");
            return true;
        }).doTrue(() -> {
            Log.d("TestCondition","after then(), i am true");
        }).doFalse(() -> {
            Log.d("TestCondition","after then(), i am false");
        }).toFalse().doNot(() -> {
            Log.d("TestCondition","doNot(1)");
            return false;
        }, () -> {
            Log.d("TestCondition","doNot(2)");
            return false;
        }, () -> {
            Log.d("TestCondition","doNot(3)");
            return true;
        }, () -> {
            Log.d("TestCondition","doNot(4)");
            return false;
        }).doTrue(() -> {
            Log.d("TestCondition","after doNot(), i am true");
        }).doFalse(() -> {
            Log.d("TestCondition","after doNot(), i am false");
        }).toFalse().not(() -> {
            Log.d("TestCondition","not(1)");
            return false;
        }).not(() -> {
            Log.d("TestCondition","not(2)");
            return false;
        }).not(() -> {
            Log.d("TestCondition","not(3)");
            return true;
        }).not(() -> {
            Log.d("TestCondition","not(4)");
            return false;
        }).doTrue(() -> {
            Log.d("TestCondition","after not(), i am true");
        }).doFalse(() -> {
            Log.d("TestCondition","after not(), i am false");
        }).ofMoreAndEqual(1, 2).doTrue(() -> {
            Log.d("TestCondition","after ofMoreAndEqual(), i am true");
        }).doFalse(() -> {
            Log.d("TestCondition","after ofMoreAndEqual(), i am false");
        }).ofEqual(1.0f, 1.0f).doTrue(() -> {
            Log.d("TestCondition","after ofEqual(), i am true");
        }).doFalse(() -> {
            Log.d("TestCondition","after ofEqual(), i am false");
        });
    }
}
