package com.example.myapplication.activity;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.ziven.easygo.design.mvp.OnePresenter;
import com.ziven.easygo.design.mvvm.DataViewModel;
import com.ziven.easygo.design.mvvm.ModelView;
import com.ziven.easygo.ui.AbstractBaseActivity;
import com.ziven.easygo.ui.AbstractEasyAdapter;
import com.ziven.easygo.ui.AbstractEasyViewHolder;

/**
 * @author Ziven
 */
public class TestViewModelActivity extends AbstractBaseActivity {

    @Override
    protected Object obtainLayout() {
        return R.layout.activity_view_model;
    }

    @Override
    protected void initLayout() {
        RecyclerView rv = getView(R.id.recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new MyAdapter());

        ModelView mv = getView(R.id.model_view);
        mv.presenter().doNotNull(OnePresenter::obtainOneData);
    }

    @Override
    protected void destroyLayout() {

    }

    private static class MyHolder extends AbstractEasyViewHolder<String> {

        private final ModelView mv;

        public MyHolder(@NonNull ViewGroup parent, int layoutId) {
            super(parent, layoutId);
            mv = getView(R.id.model_view);
        }

        @Override
        protected void bindLayout(String data, int position) {
            mv.paramPosition(0, DataViewModel.DATA, data)
                    .updateAll();
        }
    }

    private static class MyAdapter extends AbstractEasyAdapter<String, MyHolder> {

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyHolder(parent, R.layout.item_view_model);
        }
    }
}
