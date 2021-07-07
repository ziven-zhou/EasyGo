package com.ziven.easygo.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ziven.easygo.util.EasyUtils;
import com.ziven.easygo.util.ViewUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ziven
 * @date 2021/5/28
 */
public abstract class AbstractBaseActivity extends AppCompatActivity {

    private Map<Integer, View> mViews;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayout();
        if(view != null) {
            setContentView(view);
            findView(view);
        }
        initLayout();
    }

    private View getLayout() {
        Object layout = obtainLayout();
        if(layout instanceof Integer) {
            return getLayoutInflater().inflate((int) layout, null);
        } else if(layout instanceof View) {
            return EasyUtils.transition(layout);
        }
        return null;
    }

    private void findView(@NonNull View view) {
        if(!findLayout()) {
            return;
        }
        if(mViews == null) {
            mViews = new HashMap<>(8);
        }
        ViewUtils.forEachView(view, v -> {
            if(v.getId() != View.NO_ID) {
                mViews.put(v.getId(), v);
            }
        });
    }

    protected <T extends View> T getView(@IdRes int id) {
        if(mViews != null && id != View.NO_ID) {
            return EasyUtils.transition(mViews.get(id));
        }
        return null;
    }

    /**
     * Obtain layout
     * @return Layout id or View
     */
    protected abstract  Object obtainLayout();

    /**
     * Find layout
     * @return Find
     */
    protected boolean findLayout() {
        return true;
    }

    /**
     * Init layout
     */
    protected abstract void initLayout();

    @Override
    protected void onDestroy() {
        destroyLayout();
        super.onDestroy();
    }

    /**
     * Destroy layout
     */
    protected abstract void destroyLayout();
}
