package com.ziven.easygo.util;

import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
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
import androidx.recyclerview.widget.RecyclerView;

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

    public ViewHelper<T> setClipToOutline() {
        if(mView != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mView.setClipToOutline(true);
        }
        return this;
    }

    public ViewHelper<T> setOutlineProvider(@NonNull ViewOutlineProvider provider) {
        if(mView != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mView.setOutlineProvider(provider);
        }
        return this;
    }

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
        if(mView == null) {
            return this;
        }
        return setPadding(
                left,
                mView.getPaddingTop(),
                right,
                mView.getPaddingBottom()
            );
    }

    public ViewHelper<T> setPaddingTopBottom(int topBottom) {
        return setTopBottomPadding(topBottom, topBottom);
    }

    public ViewHelper<T> setTopBottomPadding(int top, int bottom) {
        if(mView == null) {
            return this;
        }
        return setPadding(
                mView.getPaddingLeft(),
                top,
                mView.getPaddingRight(),
                bottom
            );
    }

    public ViewHelper<T> setPaddingLeft(int left) {
        if(mView == null) {
            return this;
        }
        return setPadding(
                left,
                mView.getPaddingTop(),
                mView.getPaddingRight(),
                mView.getPaddingBottom());
    }

    public ViewHelper<T> setPaddingTop(int top) {
        if(mView == null) {
            return this;
        }
        return setPadding(
                mView.getPaddingLeft(),
                top,
                mView.getPaddingRight(),
                mView.getPaddingBottom());
    }

    public ViewHelper<T> setPaddingRight(int right) {
        if(mView == null) {
            return this;
        }
        return setPadding(
                mView.getPaddingLeft(),
                mView.getPaddingTop(),
                right,
                mView.getPaddingBottom());
    }

    public ViewHelper<T> setPaddingBottom(int bottom) {
        if(mView == null) {
            return this;
        }
        return setPadding(
                mView.getPaddingLeft(),
                mView.getPaddingTop(),
                mView.getPaddingRight(),
                bottom);
    }

    public ViewHelper<T> invalidate() {
        if(mView != null) {
            mView.invalidate();
        }
        return this;
    }

    public ViewHelper<T> requestFocus() {
        if(mView != null) {
            mView.requestFocus();
        }
        return this;
    }

    public ViewHelper<T> requestLayout() {
        if(mView != null) {
            mView.requestLayout();
        }
        return this;
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

    public <V extends View> ViewHelper<T> findViewById(int id, @NonNull Carry<V> carry) {
        V child = mView != null ? mView.findViewById(id) : null;
        if(child != null) {
            carry.carry(child);
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
     *-------------------------------RecyclerView start-------------------
     */


    public ViewHelper<T> setLayoutManager(@Nullable RecyclerView.LayoutManager manager) {
        if(mView instanceof RecyclerView) {
            ((RecyclerView) mView).setLayoutManager(manager);
        }
        return this;
    }

    public ViewHelper<T> setAdapter(@Nullable RecyclerView.Adapter<?> adapter) {
        if(mView instanceof RecyclerView) {
            ((RecyclerView) mView).setAdapter(adapter);
        }
        return this;
    }

    public ViewHelper<T> notifyDataSetChanged() {
        if(mView instanceof RecyclerView) {
            EasyUtils.notifyDataSetChanged((RecyclerView.Adapter<? extends RecyclerView.ViewHolder>) ((RecyclerView) mView).getAdapter());
        }
        return this;
    }

    /**
     *-------------------------------RecyclerView end---------------------
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

    public ViewHelper<T> setWidth(int width) {
        if(mView != null && mView.getLayoutParams() != null) {
            ViewGroup.LayoutParams params = mView.getLayoutParams();
            params.width = width;
        }
        return this;
    }

    public ViewHelper<T> setHeight(int height) {
        if(mView != null && mView.getLayoutParams() != null) {
            ViewGroup.LayoutParams params = mView.getLayoutParams();
            params.height = height;
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

    public ViewHelper<T> setBottomRightMargin(int right, int bottom) {
        if(mView != null &&  mView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mView.getLayoutParams();
            params.rightMargin = right;
            params.bottomMargin = bottom;
        }
        return this;
    }

    public ViewHelper<T> setLeftRightMargin(int left, int right) {
        if(mView != null &&  mView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mView.getLayoutParams();
            params.leftMargin = left;
            params.rightMargin = right;
        }
        return this;
    }

    public ViewHelper<T> setTopBottomMargin(int top, int bottom) {
        if(mView != null &&  mView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mView.getLayoutParams();
            params.topMargin = top;
            params.bottomMargin = bottom;
        }
        return this;
    }

    public ViewHelper<T> setStartEndMargin(int start, int end) {
        if(mView != null &&  mView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mView.getLayoutParams();
            params.setMarginStart(start);
            params.setMarginEnd(end);
        }
        return this;
    }

    public ViewHelper<T> setTopMargin(int top) {
        if(mView != null &&  mView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mView.getLayoutParams();
            params.topMargin = top;
        }
        return this;
    }

    public ViewHelper<T> setBottomMargin(int bottom) {
        if(mView != null &&  mView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mView.getLayoutParams();
            params.bottomMargin = bottom;
        }
        return this;
    }

    public ViewHelper<T> setLeftMargin(int left) {
        if(mView != null &&  mView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mView.getLayoutParams();
            params.leftMargin = left;
        }
        return this;
    }

    public ViewHelper<T> setRightMargin(int right) {
        if(mView != null &&  mView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mView.getLayoutParams();
            params.rightMargin = right;
        }
        return this;
    }

    public ViewHelper<T> setStartMargin(int start) {
        if(mView != null &&  mView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mView.getLayoutParams();
            params.setMarginStart(start);
        }
        return this;
    }

    public ViewHelper<T> setEndMargin(int end) {
        if(mView != null &&  mView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mView.getLayoutParams();
            params.setMarginEnd(end);
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

    public ViewHelper<T> forEachChild(@NonNull final ViewUtils.IView iView) {
        if(mView != null) {
            ViewUtils.forEachChild(mView, iView);
        }
        return this;
    }

    public ViewHelper<T> forEachView(@NonNull final ViewUtils.IView iView) {
        if(mView != null) {
            ViewUtils.forEachView(mView, iView);
        }
        return this;
    }

    public ViewHelper<T> forEachParent(@NonNull final ViewUtils.IView iView) {
        if(mView != null) {
            ViewUtils.forEachParent(mView, iView);
        }
        return this;
    }
}
