package com.ziven.easygo.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ziven.easygo.EasyGos;
import com.ziven.easygo.util.EasyUtils;
import com.ziven.easygo.util.ViewHelper;
import com.ziven.easygo.util.ViewUtils;

/**
 * @author Ziven
 * @date 2021/5/28
 */
public abstract class AbstractBaseActivity extends AppCompatActivity {

    private ViewProvider<View> viewProvider;

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

    @NonNull
    private ViewProvider<View> getViewProvider() {
        if(viewProvider == null) {
            viewProvider = ViewProvider.newInstance();
        }
        return viewProvider;
    }

    private void findView(@NonNull View view) {
        if(findLayout()) {
            ViewUtils.forEachView(view, v -> {
                if(v.getId() != View.NO_ID) {
                    getViewProvider().putView(v.getId(), v);
                }
            });
        }
    }

    @NonNull
    protected ViewHelper<View> getViewHelper(@IdRes int id) {
        if(id == View.NO_ID) {
            return EasyGos.newViewHelper();
        }
        ViewHelper<View> helper = getViewProvider().getViewHelperNullable(id);
        if(helper != null) {
            return helper;
        }
        getView(id);
        return getViewProvider().getViewHelper(id);
    }

    protected <T extends View> T getView(@IdRes int id) {
        if(id == View.NO_ID) {
            return null;
        }
        View view = getViewProvider().getView(id);
        if(view == null) {
            view = findViewById(id);
            getViewProvider().putViewNonNull(id, view);
        }
        return EasyUtils.transition(view);
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
        return false;
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
