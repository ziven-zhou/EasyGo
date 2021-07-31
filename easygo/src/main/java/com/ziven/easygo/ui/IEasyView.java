package com.ziven.easygo.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleableRes;

/**
 * @author Ziven
 * @date 2021/7/25
 */
public interface IEasyView {

    /**
     * Init View
     * @param context Context
     */
    default void initLayout(@NonNull Context context) {
        initLayout(context, null);
    }

    /**
     * Init View
     * @param context Context
     * @param attrs Attrs
     */
    default void initLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        initLayout(context, attrs, 0);
    }

    /**
     * Init View
     * @param context Context
     * @param attrs Attrs
     * @param defStyleAttr Default Style Attr
     */
    default void initLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        initLayout(context, attrs, defStyleAttr, 0);
    }

    /**
     * Init View
     * @param context Context
     * @param attrs Attrs
     * @param defStyleAttr Default Style Attr
     * @param defStyleRes Default Style Res
     */
    default void initLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        int[] styleableRes = getStyleableRes();
        if(styleableRes != null) {
            final TypedArray typedArray = context.obtainStyledAttributes(attrs, styleableRes, defStyleAttr, defStyleRes);
            initTypedArray(context, typedArray);
            typedArray.recycle();
        }
        View view = null;
        int childLayoutId = getChildLayoutId();
        if(childLayoutId != 0 && this instanceof ViewGroup) {
            view = LayoutInflater.from(context).inflate(childLayoutId, (ViewGroup) this, false);
            ViewGroup.LayoutParams layoutParams = getChildLayoutParams();
            if(layoutParams != null) {
                ((ViewGroup) this).addView(view, layoutParams);
            }
        }
        initView(context, view);
    }

    /**
     * Get StyleableRes
     * @return StyleableRes
     */
    @Nullable
    @StyleableRes
    default int[] getStyleableRes() {
        return null;
    }

    /**
     * Get child layout id
     * @return Child layout id
     */
    default int getChildLayoutId() {
        return 0;
    }

    /**
     * Get LayoutParams
     * @return Child LayoutParams
     */
    default ViewGroup.LayoutParams getChildLayoutParams() {
        return null;
    }

    /**
     * Init view
     * @param context Context
     * @param child Child view
     */
    void initView(@NonNull Context context, @Nullable View child);

    /**
     * Init TypedArray
     * @param context Context
     * @param typedArray TypedArray
     */
    default void initTypedArray(@NonNull Context context, @NonNull TypedArray typedArray) {}
}
