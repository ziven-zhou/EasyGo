package com.ziven.easygo.design.mvvm;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import com.ziven.easygo.design.mvp.AbstractOneData;
import com.ziven.easygo.util.ViewHelper;

/**
 * @author Ziven
 */
@Keep
public class ImageViewModel implements IModelView<ImageView> {

    @Override
    public void layoutViewData(@NonNull ViewHelper<ImageView> helper,
                               @NonNull AbstractOneData data) {
        Object d = data.getOneData();
        if(d instanceof Drawable) {
            helper.setImageDrawable((Drawable) d);
        } else if(d instanceof Bitmap) {
            helper.setImageBitmap((Bitmap) d);
        } else if(d instanceof Integer) {
            helper.setImageResource((Integer) d);
        }
    }
}
