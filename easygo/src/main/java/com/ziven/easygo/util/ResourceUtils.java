package com.ziven.easygo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.AnimRes;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.content.res.ResourcesCompat;

import java.util.Objects;

/**
 * @author Ziven
 * @date 2021/5/29
 */
@MainThread
public final class ResourceUtils {

    private static Context mApplicationContext;

    private ResourceUtils() {
    }

    public static void init(@NonNull Context context) {
        if(mApplicationContext == null) {
            mApplicationContext = context.getApplicationContext();
        }
    }

    public static Context getContext() {
        return Objects.requireNonNull(mApplicationContext, "ResourceUtils not init!!!");
    }

    public static int getColor(@ColorRes int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getContext().getResources().getColor(color, null);
        }
        return getContext().getResources().getColor(color);
    }

    public static int getColor(@NonNull Context context, @ColorRes int color) {
        init(context);
        return getColor(color);
    }

    public static int getDimen(@DimenRes int dimenId) {
        return getContext().getResources().getDimensionPixelSize(dimenId);
    }

    public static int getDimen(@NonNull Context context, @DimenRes int dimenId) {
        init(context);
        return getDimen(dimenId);
    }

    public static Drawable getDrawable(@DrawableRes int drawableId) {
        return ResourcesCompat.getDrawable(getContext().getResources(), drawableId, null);
    }

    public static Drawable getDrawable(@NonNull Context context, @DrawableRes int drawableId) {
        init(context);
        return getDrawable(drawableId);
    }

    public static String getText(@StringRes int stringId) {
        return getContext().getResources().getString(stringId);
    }

    public static String getText(@NonNull Context context, @StringRes int stringId) {
        init(context);
        return getText(stringId);
    }

    public static Animation getAnim(@AnimRes int animId) {
        return AnimationUtils.loadAnimation(getContext(), animId);
    }

    public static Animation getAnim(@NonNull Context context, @AnimRes int animId) {
        init(context);
        return getAnim(animId);
    }

    public static View getView(@LayoutRes int layoutId) {
        return LayoutInflater.from(getContext()).inflate(layoutId, null);
    }

    public static View getView(@NonNull Context context, @LayoutRes int layoutId) {
        init(context);
        return getView(layoutId);
    }

    public static View getView(@LayoutRes int layoutId, @NonNull ViewGroup parent) {
        init(parent.getContext());
        return LayoutInflater.from(getContext()).inflate(layoutId, parent);
    }

    public static View getView(@LayoutRes int layoutId, @NonNull ViewGroup parent, boolean attach) {
        init(parent.getContext());
        return LayoutInflater.from(getContext()).inflate(layoutId, parent, attach);
    }

    public static Bitmap drawable2Bitmap(@NonNull Drawable drawable) {
        return drawable2Bitmap(drawable, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
    }

    public static Bitmap drawable2Bitmap(@NonNull Drawable drawable, int width, int height) {
        return drawable2Bitmap(drawable, width, height, null);
    }

    public static Bitmap drawable2Bitmap565(@NonNull Drawable drawable, int width, int height) {
        return drawable2Bitmap(drawable, width, height, Bitmap.Config.RGB_565);
    }

    public static Bitmap drawable2Bitmap565(@NonNull Drawable drawable) {
        return drawable2Bitmap(drawable, Bitmap.Config.RGB_565);
    }

    public static Bitmap drawable2Bitmap8888(@NonNull Drawable drawable, int width, int height) {
        return drawable2Bitmap(drawable, width, height, Bitmap.Config.ARGB_8888);
    }

    public static Bitmap drawable2Bitmap8888(@NonNull Drawable drawable) {
        return drawable2Bitmap(drawable, Bitmap.Config.ARGB_8888);
    }

    public static Bitmap drawable2Bitmap(@NonNull Drawable drawable, @Nullable Bitmap.Config config) {
        return drawable2Bitmap(drawable, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), config);
    }

    public static Bitmap drawable2Bitmap(@NonNull Drawable drawable, int width, int height, @Nullable Bitmap.Config config) {
        int w = CheckUtils.checkZeroDef(width, drawable.getIntrinsicWidth());
        int h = CheckUtils.checkZeroDef(height, drawable.getIntrinsicHeight());
        Bitmap.Config c = CheckUtils.nullDef(config, CheckUtils.equalDef(drawable.getOpacity(), PixelFormat.OPAQUE, Bitmap.Config.RGB_565, Bitmap.Config.ARGB_8888));
        Bitmap bitmap = Bitmap.createBitmap(w, h, c);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    public static Drawable bitmap2Drawable(@NonNull Bitmap bitmap) {
        if(mApplicationContext != null) {
            return bitmap2Drawable(mApplicationContext.getResources(), bitmap);
        } else {
            return bitmap2Drawable(null, bitmap);
        }
    }

    public static void recycleBitmap(@Nullable Bitmap bitmap) {
        if(bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }

    public static boolean hasRecycled(@Nullable Bitmap bitmap) {
        return bitmap == null || bitmap.isRecycled();
    }

    public static boolean notRecycle(@Nullable Bitmap bitmap) {
        return bitmap != null && !bitmap.isRecycled();
    }

    public static Drawable bitmap2Drawable(@Nullable Resources resources, @NonNull Bitmap bitmap) {
        return new BitmapDrawable(resources, bitmap);
    }

    public static SharedPreferences sp() {
        return sp(Sp.NAME);
    }

    public static SharedPreferences sp(@Nullable Context context) {
        return sp(context, Sp.NAME);
    }

    public static SharedPreferences sp(@NonNull String spName) {
        return sp(null, spName);
    }

    public static SharedPreferences sp(@Nullable Context context, @NonNull String spName) {
        return Sp.INSTANCE.getSp(context, spName);
    }

    public static SharedPreferences.Editor edit() {
        return edit(Sp.NAME);
    }

    public static SharedPreferences.Editor edit(@Nullable Context context) {
        return edit(context, Sp.NAME);
    }

    public static SharedPreferences.Editor edit(@NonNull String spName) {
        return edit(null, spName);
    }

    public static SharedPreferences.Editor edit(@Nullable Context context, @NonNull String spName) {
        return Sp.INSTANCE.getSp(context, spName).edit();
    }

    public static Sp ofSp() {
        return ofSp(Sp.NAME);
    }

    public static Sp ofSp(@Nullable Context context) {
        return ofSp(context, Sp.NAME);
    }

    public static Sp ofSp(@NonNull String spName) {
        return ofSp(null, spName);
    }

    public static Sp ofSp(@Nullable Context context, @NonNull String spName) {
        sp(context, spName);
        return Sp.INSTANCE;
    }

    public static final class Sp {
        private static final String NAME = "sp_easy_go_config";
        private static final Sp INSTANCE = new Sp();
        private SharedPreferences mSp;
        private String mSpName;

        private Sp() {}

        public <T> Sp obtainValues(@NonNull Class<T> cls,
                                   @NonNull MultiCarry<T> carry,
                                   @NonNull String... keys) {
            return obtainArray(cls, carry, keys);
        }

        public <T> Sp obtainArray(@NonNull Class<T> cls,
                                   @NonNull MultiCarry<T> carry,
                                   @NonNull String[] keys) {
            if(cls.isAssignableFrom(String.class)) {
                return obtainArray(cls, EasyUtils.transition(EasyUtils.EMPTY), carry, keys);
            } else if(cls.isAssignableFrom(Boolean.class)) {
                return obtainArray(cls, EasyUtils.transition(false), carry, keys);
            }
            return obtainArray(cls, EasyUtils.transition(0), carry, keys);
        }

        public <T> Sp obtainValues(@NonNull Class<T> cls,
                                   @NonNull T def,
                                   @NonNull MultiCarry<T> carry,
                                   @NonNull String... keys) {
            return obtainArray(cls, def, carry, keys);
        }

        public <T> Sp obtainArray(@NonNull Class<T> cls,
                               @NonNull T def,
                               @NonNull MultiCarry<T> carry,
                               @NonNull String... keys) {
            final SharedPreferences sp = CheckUtils.checkNull(mSp);
            if(cls.isAssignableFrom(String.class)) {
                final String[] results = new String[keys.length];
                EasyUtils.forEach(keys, (v, p) -> results[p] = sp.getString(v, EasyUtils.transition(def)));
                carry.carry(EasyUtils.transition(results));
            } else if(cls.isAssignableFrom(Boolean.class)) {
                final boolean[] results = new boolean[keys.length];
                EasyUtils.forEach(keys, (v, p) -> results[p] = sp.getBoolean(v, EasyUtils.transition(def)));
                carry.carry(EasyUtils.transition(results));
            } else if(cls.isAssignableFrom(Integer.class)) {
                final int[] results = new int[keys.length];
                EasyUtils.forEach(keys, (v, p) -> results[p] = sp.getInt(v, EasyUtils.transition(def)));
                carry.carry(EasyUtils.transition(results));
            } else if(cls.isAssignableFrom(Long.class)) {
                final long[] results = new long[keys.length];
                EasyUtils.forEach(keys, (v, p) -> results[p] = sp.getLong(v, EasyUtils.transition(def)));
                carry.carry(EasyUtils.transition(results));
            } else if(cls.isAssignableFrom(Float.class)) {
                final float[] results = new float[keys.length];
                EasyUtils.forEach(keys, (v, p) -> results[p] = sp.getFloat(v, EasyUtils.transition(def)));
                carry.carry(EasyUtils.transition(results));
            } else {
                throw new NullPointerException("Not is used the type:" + cls);
            }
            return INSTANCE;
        }

        @SafeVarargs
        public final Sp obtainValues(@NonNull MultiCarry<Object> carry, @NonNull Pair<Class<?>, String>... pairs) {
            return obtainArray(carry, pairs);
        }

        public Sp obtainArray(@NonNull MultiCarry<Object> carry, @NonNull Pair<Class<?>, String>[] pairs) {
            final Object[] results = new Object[pairs.length];
            final SharedPreferences sp = CheckUtils.checkNull(mSp);
            EasyUtils.forEach(pairs, (pair, p) -> {
                if(pair.first.isAssignableFrom(String.class)) {
                    results[p] = sp.getString(pair.second, EasyUtils.EMPTY);
                } else if(pair.first.isAssignableFrom(Boolean.class)) {
                    results[p] = sp.getBoolean(pair.second, false);
                } else if(pair.first.isAssignableFrom(Integer.class)) {
                    results[p] = sp.getInt(pair.second, 0);
                } else if(pair.first.isAssignableFrom(Long.class)) {
                    results[p] = sp.getLong(pair.second, 0);
                } else if(pair.first.isAssignableFrom(Float.class)) {
                    results[p] = sp.getFloat(pair.second, 0);
                }
            });
            carry.carry(results);
            return INSTANCE;
        }

        /**
         * Obtain Sp Values
         * @param carry Carry
         * @param triple {Class<String Boolean int long float>, Key:String, Def-value:same with first param}
         *               {Class<String Boolean int long float>, Key:String}
         * @return Values
         */
        public Sp obtainValues(@NonNull MultiCarry<Object> carry, @NonNull Object[]... triple) {
            return obtainArray(carry, triple);
        }

        /**
         * Obtain Sp Values
         * @param carry Carry
         * @param triple {Class<String Boolean int long float>, Key:String, Def-value:same with first param}
         *               or {Class<String Boolean int long float>, Key:String}
         * @return Values
         */
        public Sp obtainArray(@NonNull MultiCarry<Object> carry, @NonNull Object[][] triple) {
            final Object[] results = new Object[triple.length];
            final SharedPreferences sp = CheckUtils.checkNull(mSp);
            EasyUtils.forEach(triple, (object, p) -> {
                if(object == null || object.length <= 1) {
                    throw new NullPointerException("Position at " + p + " params not less than 2.");
                }
                Class<?> cls = EasyUtils.transition(object[0]);
                String key = EasyUtils.transition(object[1]);
                Object def = object.length > 2 ? object[3] : null;
                if(cls.isAssignableFrom(String.class)) {
                    results[p] = sp.getString(key, def == null ? EasyUtils.EMPTY : (String) def);
                } else if(cls.isAssignableFrom(Boolean.class)) {
                    results[p] = sp.getBoolean(key, def != null && (boolean) def);
                } else if(cls.isAssignableFrom(Integer.class)) {
                    results[p] = sp.getInt(key, def == null ? 0 : (int) def);
                } else if(cls.isAssignableFrom(Long.class)) {
                    results[p] = sp.getLong(key, def == null ? 0 : (long) def);
                } else if(cls.isAssignableFrom(Float.class)) {
                    results[p] = sp.getFloat(key, def == null ? 0 : (float) def);
                }
            });
            carry.carry(results);
            return INSTANCE;
        }

        @SafeVarargs
        public final Sp applyValues(@NonNull Pair<String, Object>... pairs) {
            return applyArray(pairs);
        }

        public final Sp applyArray(@NonNull Pair<String, Object>[] pairs) {
            final SharedPreferences.Editor edit = CheckUtils.checkNull(mSp).edit();
            EasyUtils.forEach(pairs, r -> EasyUtils.typeConditions(new Conditions() {

                @Override
                public void condition1() {
                    edit.putString(r.first, EasyUtils.transition(r.second));
                }

                @Override
                public void condition2() {
                    edit.putBoolean(r.first, EasyUtils.transition(r.second));
                }

                @Override
                public void condition3() {
                    edit.putInt(r.first, EasyUtils.transition(r.second));
                }

                @Override
                public void condition4() {
                    edit.putLong(r.first, EasyUtils.transition(r.second));
                }

                @Override
                public void condition5() {
                    edit.putFloat(r.first, EasyUtils.transition(r.second));
                }

                @Override
                public void condition7() {
                    edit.putStringSet(r.first, EasyUtils.transition(r.second));
                }
            }, r.second));
            edit.apply();
            return INSTANCE;
        }

        public Sp removeValues(@NonNull String... keys) {
            final SharedPreferences.Editor edit = CheckUtils.checkNull(mSp).edit();
            EasyUtils.forEach(keys, edit::remove);
            edit.apply();
            return INSTANCE;
        }

        private SharedPreferences getSp(@Nullable Context context, @NonNull String spName) {
            return Condition.of(mSp).and(TextUtils.equals(mSpName, spName)).isTrue()
                    ? mSp : newSp(context, spName);
        }

        private SharedPreferences newSp(@Nullable Context context, @NonNull String spName) {
            Nulls.of(context).doNotNull(ResourceUtils::init);
            mSp = getContext().getSharedPreferences(spName, Context.MODE_PRIVATE);
            mSpName = spName;
            return mSp;
        }
    }
}
