package com.ziven.easygo.util;

import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

/**
 * @author Ziven
 * @date 2022/1/26
 */
public final class ViewHelper<T extends View> {

    @Nullable
    private final T mView;

    public static <T extends View> ViewHelper<T> create(@Nullable T view) {
        return new ViewHelper<>(view);
    }

    private ViewHelper(@Nullable T view) {
        mView = view;
    }

    public Nulls<T> getViewNulls() {
        return Nulls.of(getView());
    }

    @Nullable
    public T getView() {
        return mView;
    }

    /**
     *-------------------------------View start-----------------------------
     */

    public ViewHelper<T> setBackgroundResource(@DrawableRes int resId) {
        if(mView != null) {
            mView.setBackgroundResource(resId);
        }
        return this;
    }

    public ViewHelper<T> setBackground(Drawable background) {
        if(mView != null) {
            mView.setBackground(background);
        }
        return this;
    }

    public ViewHelper<T> setBackgroundColor(@ColorInt int color) {
        if(mView != null) {
            mView.setBackgroundColor(color);
        }
        return this;
    }

    public ViewHelper<T> setVisibility(int visibility) {
        if(mView != null) {
            mView.setVisibility(visibility);
        }
        return this;
    }

    public ViewHelper<T> setLayoutParams(@Nullable ViewGroup.LayoutParams params) {
        if(mView != null && params != null) {
            mView.setLayoutParams(params);
        }
        return this;
    }

    public ViewHelper<T> setOnClickListener(@Nullable View.OnClickListener l) {
        if(mView != null) {
            mView.setOnClickListener(l);
        }
        return this;
    }

    public ViewHelper<T> setOnLongClickListener(@Nullable View.OnLongClickListener l) {
        if(mView != null) {
            mView.setOnLongClickListener(l);
        }
        return this;
    }

    public ViewHelper<T> setPadding(int left, int top, int right, int bottom) {
        if(mView != null) {
            mView.setPadding(left, top, right, bottom);
        }
        return this;
    }

    public ViewHelper<T> setPadding(int leftRight) {
        return setPadding(leftRight, leftRight);
    }

    public ViewHelper<T> setPadding(int left, int right) {
        return setPadding(left, 0, right, 0);
    }


    /**
     *-------------------------------View end-----------------------------
     *
     *-------------------------------TextView start-----------------------
     */


    public ViewHelper<T> setText(@StringRes int resId) {
        if(mView instanceof TextView) {
            ((TextView) mView).setText(resId);
        }
        return this;
    }

    public ViewHelper<T> setText(CharSequence text) {
        if(mView instanceof TextView) {
            ((TextView) mView).setText(text);
        }
        return this;
    }

    public ViewHelper<T> setText(CharSequence text, TextView.BufferType type) {
        if(mView instanceof TextView) {
            ((TextView) mView).setText(text, type);
        }
        return this;
    }

    public ViewHelper<T> setText(@StringRes int resId, TextView.BufferType type) {
        if(mView instanceof TextView) {
            ((TextView) mView).setText(resId, type);
        }
        return this;
    }

    public ViewHelper<T> setTextColor(@ColorInt int color) {
        if(mView instanceof TextView) {
            ((TextView) mView).setTextColor(color);
        }
        return this;
    }

    public ViewHelper<T> setTextColorId(@ColorRes int colorId) {
        if(mView instanceof TextView) {
            return setTextColor(mView.getContext().getResources().getColor(colorId));
        }
        return this;
    }

    public ViewHelper<T> setTextSize(float size) {
        if(mView instanceof TextView) {
            ((TextView) mView).setTextSize(size);
        }
        return this;
    }

    public ViewHelper<T> setTextSize(int unit, float size) {
        if(mView instanceof TextView) {
            ((TextView) mView).setTextSize(unit, size);
        }
        return this;
    }

    public ViewHelper<T> setTextSizeId(@DimenRes int sizeId) {
        return setTextSize(TypedValue.COMPLEX_UNIT_PX, mView.getResources().getDimensionPixelSize(sizeId));
    }


    /**
     *-------------------------------TextView end-----------------------------
     *
     *-------------------------------ImageView start--------------------------
     */


    public ViewHelper<T> setImageDrawable(@Nullable Drawable drawable) {
        if(mView instanceof ImageView) {
            ((ImageView) mView).setImageDrawable(drawable);
        }
        return this;
    }

    public ViewHelper<T> setImageResource(@DrawableRes int resId) {
        if(mView instanceof ImageView) {
            ((ImageView) mView).setImageResource(resId);
        }
        return this;
    }

    public ViewHelper<T> setImageBitmap(Bitmap bm) {
        if(mView instanceof ImageView) {
            ((ImageView) mView).setImageBitmap(bm);
        }
        return this;
    }

    public ViewHelper<T> setColorFilter(int color) {
        if(mView instanceof ImageView) {
            ((ImageView) mView).setColorFilter(color);
        }
        return this;
    }

    public ViewHelper<T> setColorFilter(ColorFilter cf) {
        if(mView instanceof ImageView) {
            ((ImageView) mView).setColorFilter(cf);
        }
        return this;
    }

    public ViewHelper<T> setColorFilter(int color, PorterDuff.Mode mode) {
        if(mView instanceof ImageView) {
            ((ImageView) mView).setColorFilter(color, mode);
        }
        return this;
    }

    public ViewHelper<T> setScaleType(ImageView.ScaleType scaleType) {
        if(mView instanceof ImageView) {
            ((ImageView) mView).setScaleType(scaleType);
        }
        return this;
    }


    /**
     *-------------------------------ImageView end-----------------------------
     *
     *-------------------------------ViewGroup start--------------------------
     */


    public ViewHelper<T> addView(View child) {
        if(mView instanceof ViewGroup) {
            ((ViewGroup) mView).addView(child);
        }
        return this;
    }

    public ViewHelper<T> addView(View child, int index) {
        if(mView instanceof ViewGroup) {
            ((ViewGroup) mView).addView(child, index);
        }
        return this;
    }

    public ViewHelper<T> addView(View child, int width, int height) {
        if(mView instanceof ViewGroup) {
            ((ViewGroup) mView).addView(child, width, height);
        }
        return this;
    }

    public ViewHelper<T> addView(View view, ViewGroup.LayoutParams params) {
        if(mView instanceof ViewGroup) {
            ((ViewGroup) mView).addView(view, params);
        }
        return this;
    }

    public ViewHelper<T> addView(View child, int index, ViewGroup.LayoutParams params) {
        if(mView instanceof ViewGroup) {
            ((ViewGroup) mView).addView(child, index, params);
        }
        return this;
    }

    public ViewHelper<T> removeAllViews() {
        if(mView instanceof ViewGroup) {
            ((ViewGroup) mView).removeAllViews();
        }
        return this;
    }

    public ViewHelper<T> removeView(@Nullable View view) {
        if(mView instanceof ViewGroup && view != null) {
            ((ViewGroup) mView).removeView(view);
        }
        return this;
    }

    public ViewHelper<T> removeViewAt(int index) {
        if(mView instanceof ViewGroup) {
            ((ViewGroup) mView).removeViewAt(index);
        }
        return this;
    }


    /**
     *-------------------------------ViewGroup end-----------------------------
     *
     *-------------------------------LinearLayout start------------------------
     */


    public ViewHelper<T> setOrientation(int orientation) {
        if(mView instanceof LinearLayout) {
            ((LinearLayout) mView).setOrientation(orientation);
        }
        return this;
    }


    /**
     *-------------------------------LinearLayout end---------------------
     *
     *-------------------------------Other start--------------------------
     */

    public ViewHelper<T> setGravity(int gravity) {
        if(mView instanceof TextView) {
            ((TextView) mView).setGravity(gravity);
            return this;
        }
        if(mView instanceof LinearLayout) {
            ((LinearLayout) mView).setGravity(gravity);
            return this;
        }
        if(mView instanceof RelativeLayout) {
            ((RelativeLayout) mView).setGravity(gravity);
            return this;
        }
        return this;
    }


    public ViewHelper<T> setWidthAndHeight(int width, int height) {
        if(mView != null && mView.getLayoutParams() != null) {
            ViewGroup.LayoutParams params = mView.getLayoutParams();
            params.width = width;
            params.height = height;
        }
        return this;
    }

    public ViewHelper<T> resetTopLeftMargin() {
        return setTopLeftMargin(0, 0);
    }

    public ViewHelper<T> setTopLeftMargin(int left, int top) {
        if(mView != null &&  mView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mView.getLayoutParams();
            params.leftMargin = left;
            params.topMargin = top;
        }
        return this;
    }

    public ViewHelper<T> removeFromParent() {
        if(mView != null && mView.getParent() instanceof ViewGroup) {
            ((ViewGroup) mView.getParent()).removeView(mView);
        }
        return this;
    }

    public ViewHelper<T> addToParent(@Nullable View parent) {
        if(mView != null && parent instanceof ViewGroup) {
            ((ViewGroup) parent).addView(mView);
        }
        return this;
    }

    public ViewHelper<T> addToParentWithChildWidthHeight(@Nullable View parent) {
        return mView == null ? this : addToParent(parent, mView.getMeasuredWidth(), mView.getMeasuredHeight());
    }

    public ViewHelper<T> addToParent(@Nullable View parent, @NonNull ViewGroup.LayoutParams params) {
        if(mView != null && parent instanceof ViewGroup) {
            ((ViewGroup) parent).addView(mView, params);
        }
        return this;
    }

    public ViewHelper<T> addToParent(@Nullable View parent, int width ,int height) {
        if(mView != null && parent instanceof ViewGroup) {
            ((ViewGroup) parent).addView(mView, new ViewGroup.LayoutParams(width, height));
        }
        return this;
    }

    public ViewHelper<T> addToParent(@Nullable View parent, int width ,int height, int left, int top) {
        if(mView != null && parent instanceof ViewGroup) {
            ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(width, height);
            params.leftMargin = left;
            params.topMargin = top;
            ((ViewGroup) parent).addView(mView, params);
        }
        return this;
    }
}
