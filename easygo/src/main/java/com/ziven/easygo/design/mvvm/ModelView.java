package com.ziven.easygo.design.mvvm;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ziven.easygo.R;
import com.ziven.easygo.design.mvp.AbstractOneData;
import com.ziven.easygo.design.mvp.IOneView;
import com.ziven.easygo.design.mvp.OneData;
import com.ziven.easygo.design.mvp.OnePresenter;
import com.ziven.easygo.util.EasyUtils;
import com.ziven.easygo.util.LogHelper;
import com.ziven.easygo.util.Nulls;
import com.ziven.easygo.util.ViewHelper;
import com.ziven.easygo.util.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ziven
 */
@Keep
public class ModelView extends FrameLayout implements IOneView {

    private static final String TAG = "ModelView";
    private final List<ModelView> childModelViews = new ArrayList<>();
    private final IModelView<? extends View> modelView;
    private OnePresenter onePresenter;
    private final boolean isObtainData;
    private final int modelViewId;
    private View modelViewView;

    public ModelView(@NonNull Context context) {
        this(context, null);
    }

    public ModelView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ModelView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ModelView, defStyleAttr, 0);
        modelViewId = a.getResourceId(R.styleable.ModelView_model_view_id, View.NO_ID);
        isObtainData = a.getBoolean(R.styleable.ModelView_is_obtain_data, true);

        String modelViewString = a.getString(R.styleable.ModelView_model_view_cls);
        modelView = !TextUtils.isEmpty(modelViewString) ? EasyUtils.newInstance(modelViewString) : null;

        String viewModelString = a.getString(R.styleable.ModelView_view_model_cls);
        ViewModel viewModel = !TextUtils.isEmpty(viewModelString) ? EasyUtils.newInstance(viewModelString) : null;

        LogHelper.log(TAG, " modelView=" + modelView + " viewModel=" + viewModel);

        if(viewModel != null) {
            onePresenter = new OnePresenter();
            onePresenter.setViewModel(this, viewModel);
            onePresenter.clearAfterObtain(false);
            if(a.getBoolean(R.styleable.ModelView_is_run_on_worker, false)) {
                onePresenter.runOnWorkThread();
            } else {
                onePresenter.runOnMainThread();
            }
            addParam(onePresenter, ViewModel.PARAM_S_1, a.getString(R.styleable.ModelView_param_s_1));
            addParam(onePresenter, ViewModel.PARAM_S_2, a.getString(R.styleable.ModelView_param_s_2));
            addParam(onePresenter, ViewModel.PARAM_S_3, a.getString(R.styleable.ModelView_param_s_3));
            addParam(onePresenter, ViewModel.PARAM_S_4, a.getString(R.styleable.ModelView_param_s_4));
            addParam(onePresenter, ViewModel.PARAM_S_5, a.getString(R.styleable.ModelView_param_s_5));

            addParam(onePresenter, ViewModel.PARAM_I_1, a.getInt(R.styleable.ModelView_param_i_1, 0));
            addParam(onePresenter, ViewModel.PARAM_I_2, a.getInt(R.styleable.ModelView_param_i_2, 0));
            addParam(onePresenter, ViewModel.PARAM_I_3, a.getInt(R.styleable.ModelView_param_i_3, 0));
            addParam(onePresenter, ViewModel.PARAM_I_4, a.getInt(R.styleable.ModelView_param_i_4, 0));
            addParam(onePresenter, ViewModel.PARAM_I_5, a.getInt(R.styleable.ModelView_param_i_5, 0));

            addParam(onePresenter, ViewModel.PARAM_B_1, a.getBoolean(R.styleable.ModelView_param_b_1, false));
            addParam(onePresenter, ViewModel.PARAM_B_2, a.getBoolean(R.styleable.ModelView_param_b_2, false));
            addParam(onePresenter, ViewModel.PARAM_B_3, a.getBoolean(R.styleable.ModelView_param_b_3, false));
            addParam(onePresenter, ViewModel.PARAM_B_4, a.getBoolean(R.styleable.ModelView_param_b_4, false));
            addParam(onePresenter, ViewModel.PARAM_B_5, a.getBoolean(R.styleable.ModelView_param_b_5, false));
        }

        a.recycle();

        setVisibility(GONE);
        setWillNotDraw(true);
    }

    private void addParam(@NonNull OnePresenter presenter,
                          @NonNull String key,
                          Object value) {
        LogHelper.log(TAG, " Key=" + key + " value=" + value);
        if(value != null) {
            presenter.setParam(key, value);
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {}

    public Nulls<OnePresenter> presenter() {
        return Nulls.of(onePresenter);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        childModelViews.clear();
        EasyUtils.runSafety(() -> ViewUtils.forEachChild(this, child -> {
            if(child instanceof ModelView) {
                childModelViews.add((ModelView) child);
            }
        }));
        if(isObtainData) {
            post(() -> presenter().doNotNull(presenter -> presenter.obtainOneData(getContext())));
        }
    }

    @Override
    public void layoutOneData(@NonNull AbstractOneData data) {
        LogHelper.log(TAG, " data=" + data);
        if(data instanceof ViewData) {
            ViewData viewData = (ViewData) data;
            Object modelViewData;
            View modelViewView;
            if(modelView != null
                    && (modelViewView = getModelViewView()) != null
                    && (modelViewData = viewData.getViewData(modelViewId)) != null) {
                LogHelper.log(TAG, " modelViewData=" + modelViewData);
                modelView.layoutViewData(
                        ViewHelper.create(EasyUtils.transitionSafety(modelViewView)),
                        OneData.of(modelViewData));
            }
            EasyUtils.forEach(childModelViews, modelView -> modelView.layoutOneData(data));
        }
    }

    private View getModelViewView() {
        if(modelViewView == null) {
            modelViewView = getRootView().findViewById(modelViewId);
            LogHelper.log(TAG, " modelViewId=" + modelViewId + " modelViewView=" + modelViewView);
        }
        return modelViewView;
    }
}
