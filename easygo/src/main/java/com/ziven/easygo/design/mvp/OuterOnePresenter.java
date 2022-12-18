package com.ziven.easygo.design.mvp;

import androidx.annotation.Keep;

import com.ziven.easygo.util.EasyUtils;

/**
 * @author Ziven
 * @date 2022/1/12
 */
@Keep
public class OuterOnePresenter extends OnePresenter {

    public <T> T getOneModel() {
        return EasyUtils.transition(getModel());
    }

    public <T> T geOneView() {
        return EasyUtils.transition(getView());
    }
}
