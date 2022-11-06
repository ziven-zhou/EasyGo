package com.ziven.easygo.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.RecyclerView;

import com.ziven.easygo.EasyGos;
import com.ziven.easygo.util.EasyUtils;
import com.ziven.easygo.util.ViewHelper;
import com.ziven.easygo.util.ViewUtils;

/**
 * @author Ziven
 * @date 2021/5/29
 */
public abstract class AbstractEasyViewHolder<D> extends RecyclerView.ViewHolder {

    private final ViewProvider<View> mViews;
    private View mLastCacheView;
    private OnItemClickListener<D> mListener;

    public AbstractEasyViewHolder(@NonNull View itemView) {
        super(itemView);
        mViews = EasyGos.newViewProvider();
        initView();
    }

    public AbstractEasyViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutId) {
        super(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
        mViews = EasyGos.newViewProvider();
        initView();
    }

    private void initView() {
        if(findLayout()) {
            ViewUtils.forEachView(itemView, view -> {
                if(view.getId() != View.NO_ID) {
                    mViews.putView(view.getId(), view);
                }
            });
        }
        initLayout();
    }

    /**
     * Find layout
     * @return Find
     */
    protected boolean findLayout() {
        return false;
    }

    /**
     * Init layout
     */
    protected void initLayout() {
    }

    /**
     * Bind layout
     * @param data Data
     * @param position Position
     */
    protected abstract void bindLayout(D data, int position);

    @NonNull
    protected Context getContext() {
        return itemView.getContext();
    }

    @NonNull
    protected ViewHelper<View> getViewHelper(@IdRes int id) {
        if(id == View.NO_ID) {
            return EasyGos.newViewHelper();
        }
        ViewHelper<View> helper = mViews.getViewHelperNullable(id);
        if(helper != null) {
            return helper;
        }
        getView(id);
        return mViews.getViewHelper(id);
    }

    protected <T extends View> T getView(@IdRes int id) {
        if(id == View.NO_ID) {
            return null;
        }
        View view = mViews.getView(id);
        if(view == null) {
            view = itemView.findViewById(id);
            mViews.putViewNonNull(id, view);
        }
        return EasyUtils.transition(view);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setLastCacheView(View view) {
        mLastCacheView = view;
        return EasyUtils.transition(this);
    }

    protected String getText() {
        if(mLastCacheView instanceof TextView) {
            CharSequence cs = ((TextView) mLastCacheView).getText();
            return cs == null ? "" : cs.toString();
        }
        return EasyUtils.transition(this);
    }

    protected String getText(@IdRes int id) {
        mLastCacheView = getView(id);
        return getText();
    }

    protected <T extends AbstractEasyViewHolder<D>> T setText(@StringRes int strId) {
        if(mLastCacheView instanceof TextView) {
            ((TextView) mLastCacheView).setText(strId);
        }
        return EasyUtils.transition(this);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setText(@IdRes int id, @StringRes int strId) {
        mLastCacheView = getView(id);
        return setText(strId);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setText(@Nullable Object obj) {
        if(mLastCacheView instanceof TextView && obj != null) {
            ((TextView) mLastCacheView).setText(obj.toString());
        }
        return EasyUtils.transition(this);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setText(@IdRes int id, @Nullable Object obj) {
        mLastCacheView = getView(id);
        return setText(obj);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setImage(@DrawableRes int drawableId) {
        if(mLastCacheView instanceof ImageView) {
            ((ImageView) mLastCacheView).setImageResource(drawableId);
        } else if(mLastCacheView != null) {
            mLastCacheView.setBackgroundResource(drawableId);
        }
        return EasyUtils.transition(this);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setImage(@IdRes int id, @DrawableRes int drawableId) {
        mLastCacheView = getView(id);
        return setImage(drawableId);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setImage(Bitmap bitmap) {
        if(mLastCacheView instanceof ImageView) {
            ((ImageView) mLastCacheView).setImageBitmap(bitmap);
        } else if(mLastCacheView != null) {
            mLastCacheView.setBackground(new BitmapDrawable(itemView.getResources(), bitmap));
        }
        return EasyUtils.transition(this);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setImage(@IdRes int id, Bitmap bitmap) {
        mLastCacheView = getView(id);
        return setImage(bitmap);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setImage(Drawable drawable) {
        if(mLastCacheView instanceof ImageView) {
            ((ImageView) mLastCacheView).setImageDrawable(drawable);
        } else if(mLastCacheView != null) {
            mLastCacheView.setBackground(drawable);
        }
        return EasyUtils.transition(this);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setImage(@IdRes int id, Drawable drawable) {
        mLastCacheView = getView(id);
        return setImage(drawable);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setListener(@Nullable View.OnClickListener listener) {
        if(mLastCacheView != null) {
            mLastCacheView.setOnClickListener(listener);
        }
        return EasyUtils.transition(this);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setListener(@IdRes int id, @Nullable View.OnClickListener listener) {
        mLastCacheView = getView(id);
        return setListener(listener);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setListener(@Nullable View.OnLongClickListener listener) {
        if(mLastCacheView != null) {
            mLastCacheView.setOnLongClickListener(listener);
        }
        return EasyUtils.transition(this);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setListener(@IdRes int id, @Nullable View.OnLongClickListener listener) {
        mLastCacheView = getView(id);
        return setListener(listener);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setSize(float size) {
        if(mLastCacheView instanceof TextView) {
            ((TextView) mLastCacheView).setTextSize(size);
        }
        return EasyUtils.transition(this);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setSize(@IdRes int id, float size) {
        mLastCacheView = getView(id);
        return setSize(size);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setUnitSize(int unit, float size) {
        if(mLastCacheView instanceof TextView) {
            ((TextView) mLastCacheView).setTextSize(unit, size);
        }
        return EasyUtils.transition(this);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setUnitSize(@IdRes int id, int unit, float size) {
        mLastCacheView = getView(id);
        return setUnitSize(unit, size);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setSizeId(@DimenRes int dimenId) {
        return setSize(itemView.getResources().getDimensionPixelSize(dimenId));
    }

    protected <T extends AbstractEasyViewHolder<D>> T setSizeId(@IdRes int id, @DimenRes int dimenId) {
        mLastCacheView = getView(id);
        return setSizeId(dimenId);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setColor(@ColorInt int color) {
        if(mLastCacheView instanceof TextView) {
            ((TextView) mLastCacheView).setTextColor(color);
        }
        return EasyUtils.transition(this);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setColor(@IdRes int id, @ColorInt int color) {
        mLastCacheView = getView(id);
        return setColor(color);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setColorId(@ColorRes int colorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return setColor(itemView.getResources().getColor(colorId, null));
        } else {
            return setColor(itemView.getResources().getColor(colorId));
        }
    }

    protected <T extends AbstractEasyViewHolder<D>> T setColorId(@IdRes int id, @ColorRes int colorId) {
        mLastCacheView = getView(id);
        return setColorId(colorId);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setBgColor(@ColorInt int color) {
        if(mLastCacheView != null) {
            mLastCacheView.setBackgroundColor(color);
        }
        return EasyUtils.transition(this);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setBgColor(@IdRes int id, @ColorInt int color) {
        mLastCacheView = getView(id);
        return setBgColor(color);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setBgColorId(@ColorRes int colorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return setBgColor(itemView.getResources().getColor(colorId, null));
        } else {
            return setBgColor(itemView.getResources().getColor(colorId));
        }
    }

    protected <T extends AbstractEasyViewHolder<D>> T setBgColorId(@IdRes int id, @ColorRes int colorId) {
        mLastCacheView = getView(id);
        return setBgColorId(colorId);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setVisible() {
        if(mLastCacheView != null) {
            mLastCacheView.setVisibility(View.VISIBLE);
        }
        return EasyUtils.transition(this);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setVisible(@IdRes int id) {
        mLastCacheView = getView(id);
        return setVisible();
    }

    protected <T extends AbstractEasyViewHolder<D>> T setGone() {
        if(mLastCacheView != null) {
            mLastCacheView.setVisibility(View.GONE);
        }
        return EasyUtils.transition(this);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setGone(@IdRes int id) {
        mLastCacheView = getView(id);
        return setGone();
    }

    protected <T extends AbstractEasyViewHolder<D>> T setInvisible() {
        if(mLastCacheView != null) {
            mLastCacheView.setVisibility(View.INVISIBLE);
        }
        return EasyUtils.transition(this);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setInvisible(@IdRes int id) {
        mLastCacheView = getView(id);
        return setInvisible();
    }

    protected <T extends AbstractEasyViewHolder<D>> T setSelected() {
        if(mLastCacheView != null) {
            mLastCacheView.setSelected(true);
        }
        return EasyUtils.transition(this);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setSelected(@IdRes int id) {
        mLastCacheView = getView(id);
        return setSelected();
    }

    protected <T extends AbstractEasyViewHolder<D>> T setUnSelected() {
        if(mLastCacheView != null) {
            mLastCacheView.setSelected(false);
        }
        return EasyUtils.transition(this);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setUnSelected(@IdRes int id) {
        mLastCacheView = getView(id);
        return setUnSelected();
    }

    protected <T extends AbstractEasyViewHolder<D>> T setClickable() {
        if(mLastCacheView != null) {
            mLastCacheView.setClickable(true);
        }
        return EasyUtils.transition(this);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setClickable(@IdRes int id) {
        mLastCacheView = getView(id);
        return setClickable();
    }

    protected <T extends AbstractEasyViewHolder<D>> T setUnClickable() {
        if(mLastCacheView != null) {
            mLastCacheView.setClickable(false);
        }
        return EasyUtils.transition(this);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setUnClickable(@IdRes int id) {
        mLastCacheView = getView(id);
        return setUnClickable();
    }

    protected <T extends AbstractEasyViewHolder<D>> T setTag(@Nullable Object tag) {
        if(mLastCacheView != null) {
            mLastCacheView.setTag(tag);
        }
        return EasyUtils.transition(this);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setTag(@IdRes int id, @Nullable Object tag) {
        mLastCacheView = getView(id);
        return setTag(tag);
    }

    public <T extends AbstractEasyViewHolder<D>> T setListener(@Nullable OnItemClickListener<D> listener) {
        mListener = listener;
        return EasyUtils.transition(this);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setOnItemClickListener(D data, int position) {
        if(mLastCacheView != null) {
            mLastCacheView.setOnClickListener(view -> {
                if(mListener != null) {
                    mListener.onItemClick(data, position);
                }
            });
        }
        return EasyUtils.transition(this);
    }

    protected <T extends AbstractEasyViewHolder<D>> T setOnItemClickListener(@IdRes int id, D data, int position) {
        return setOnItemClickListener(getView(id), data, position);
    }

    public <T extends AbstractEasyViewHolder<D>> T setOnItemClickListener(View view, D data, int position) {
        setLastCacheView(view);
        return setOnItemClickListener(data, position);
    }
}
