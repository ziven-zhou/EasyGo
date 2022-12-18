package com.ziven.easygo.design.mvp;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ziven.easygo.util.EasyUtils;

/**
 * @author :zhiyuan.zhou
 * @date :2021/5/25
 */
@Keep
public abstract class AbstractOneData {

    private Object mOneData;
    private int mOneCode;

    public AbstractOneData() {

    }

    public AbstractOneData(int code) {
        setOneCode(code);
    }

    public AbstractOneData(@Nullable Object data) {
        setOneData(data);
    }

    public AbstractOneData(int code, @Nullable Object data) {
        setOneCode(code);
        setOneData(data);
    }

    public <D extends AbstractOneData> D setOneCode(int code) {
        mOneCode = code;
        return EasyUtils.transition(this);
    }

    public int getOneCode() {
        return mOneCode;
    }

    public <D extends AbstractOneData> D setOneData(@Nullable Object data) {
        mOneData = data;
        return EasyUtils.transition(this);
    }

    public <D> D getOneData() {
        return EasyUtils.transition(mOneData);
    }

    public boolean hasOneData() {
        return mOneData != null;
    }

    public <D extends AbstractOneData> D getMyChild(@NonNull Class<D> cls) {
        return EasyUtils.transition(cls, this);
    }

    @NonNull
    @Override
    public String toString() {
        return "AbstractOneData{" +
                "mOneData=" + mOneData +
                ", mOneCode=" + mOneCode +
                '}';
    }
}
