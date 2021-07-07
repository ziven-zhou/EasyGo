package com.ziven.easygo.util;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

/**
 * @author Ziven
 * @date 2021/5/22
 */
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

    @FunctionalInterface
    public interface IView {
        /**
         * Find View
         * @param view View
         */
         void view(@NonNull View view);
    }
}
