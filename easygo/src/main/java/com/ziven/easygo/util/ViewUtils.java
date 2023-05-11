package com.ziven.easygo.util;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author Ziven
 * @date 2021/5/22
 */
@Keep
public final class ViewUtils {

    private ViewUtils() {
    }

    public static void forEachParent(@NonNull final Object child, @NonNull final IView iView) {
        if(child instanceof View) {
            iView.view((View) child);
            forEachParent(((View) child).getParent(), iView);
        }
    }

    public static void forEachView(@NonNull final View root, @NonNull final IView iView) {
        iView.view(root);
       if(root instanceof ViewGroup) {
           ViewGroup vg = EasyUtils.transition(root);
           int count = vg.getChildCount();
           View view;
           for(int i=0; i<count; i++) {
               if((view = vg.getChildAt(i)) != null) {
                   forEachView(view, iView);
               }
           }
       }
    }

    public static void forEachChild(@NonNull final View root, @NonNull final IView iView) {
        if(root instanceof ViewGroup) {
            ViewGroup vg = EasyUtils.transition(root);
            int count = vg.getChildCount();
            View view;
            for(int i=0; i<count; i++) {
                if((view = vg.getChildAt(i)) != null) {
                    iView.view(view);
                }
            }
        }
    }

    public static boolean removeFromParent(@Nullable View child) {
        if(child == null) {
            return false;
        }
        ViewParent vp = child.getParent();
        if(vp instanceof ViewGroup) {
            ((ViewGroup) vp).removeView(child);
            return true;
        }
        return false;
    }

    @FunctionalInterface
    public interface IView {
        /**
         * Find View
         * @param view View
         */
         void view(@NonNull View view);
    }
}
