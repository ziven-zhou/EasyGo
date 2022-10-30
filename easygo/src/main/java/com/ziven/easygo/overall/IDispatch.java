package com.ziven.easygo.overall;

import androidx.annotation.NonNull;

import com.ziven.easygo.design.mvp.IOneView;
import com.ziven.easygo.design.mvp.OnePresenter;
import com.ziven.easygo.util.EasyUtils;
import com.ziven.easygo.util.MapDataProvider;

import java.util.concurrent.ConcurrentHashMap;

/**
 * for {@link IOverall}
 * @author :zhiyuan.zhou
 * @date :2022/10/29
 */
public interface IDispatch {

    MapDataProvider<String, OverallModel> PROVIDER = MapDataProvider.of(new ConcurrentHashMap<>());

    /**
     * New OverallModel
     * @return OverallModel
     */
    @NonNull
    OverallModel newModel();

    /**
     * New OverallModel
     * @return OverallModel
     */
    @NonNull
    default OverallModel newModel2() {
        return OverallModel.EMPTY;
    }

    /**
     * New OverallModel
     * @return OverallModel
     */
    @NonNull
    default OverallModel newModel3() {
        return OverallModel.EMPTY;
    }

    /**
     * New OverallModel
     * @return OverallModel
     */
    @NonNull
    default OverallModel newModel4() {
        return OverallModel.EMPTY;
    }

    /**
     * New OverallModel
     * @return OverallModel
     */
    @NonNull
    default OverallModel newModel5() {
        return OverallModel.EMPTY;
    }

    /**
     * New OverallModel
     * @return OverallModel
     */
    @NonNull
    default OverallModel newModel6() {
        return OverallModel.EMPTY;
    }

    /**
     * New OverallModel
     * @return OverallModel
     */
    @NonNull
    default OverallModel newModel7() {
        return OverallModel.EMPTY;
    }

    /**
     * New OverallModel
     * @return OverallModel
     */
    @NonNull
    default OverallModel newModel8() {
        return OverallModel.EMPTY;
    }

    /**
     * New OverallModel
     * @return OverallModel
     */
    @NonNull
    default OverallModel newModel9() {
        return OverallModel.EMPTY;
    }

    /**
     * New OverallModel
     * @param condition Condition
     * @return OverallModel
     */
    @NonNull
    default OverallModel newOtherModel(int condition) {
        return OverallModel.EMPTY;
    }

    /**
     * Get OverallModel
     * @return OverallModel
     */
    @NonNull
    default OverallModel getModel() {
        return PROVIDER.getIfNullObtain("getModel", this::newModel);
    }

    /**
     * Get OverallModel
     * @return OverallModel
     */
    @NonNull
    default OverallModel getModel2() {
        return PROVIDER.getIfNullObtain("getModel2", this::newModel2);
    }

    /**
     * Get OverallModel
     * @return OverallModel
     */
    @NonNull
    default OverallModel getModel3() {
        return PROVIDER.getIfNullObtain("getModel3", this::newModel3);
    }

    /**
     * Get OverallModel
     * @return OverallModel
     */
    @NonNull
    default OverallModel getModel4() {
        return PROVIDER.getIfNullObtain("getModel4", this::newModel4);
    }

    /**
     * Get OverallModel
     * @return OverallModel
     */
    @NonNull
    default OverallModel getModel5() {
        return PROVIDER.getIfNullObtain("getModel5", this::newModel5);
    }

    /**
     * Get OverallModel
     * @return OverallModel
     */
    @NonNull
    default OverallModel getModel6() {
        return PROVIDER.getIfNullObtain("getModel6", this::newModel6);
    }

    /**
     * Get OverallModel
     * @return OverallModel
     */
    @NonNull
    default OverallModel getModel7() {
        return PROVIDER.getIfNullObtain("getModel7", this::newModel7);
    }

    /**
     * Get OverallModel
     * @return OverallModel
     */
    @NonNull
    default OverallModel getModel8() {
        return PROVIDER.getIfNullObtain("getModel8", this::newModel8);
    }

    /**
     * Get OverallModel
     * @return OverallModel
     */
    @NonNull
    default OverallModel getModel9() {
        return PROVIDER.getIfNullObtain("getModel9", this::newModel9);
    }

    /**
     * Get OverallModel
     * @param condition Condition
     * @return OverallModel
     */
    @NonNull
    default OverallModel getOtherModel(int condition) {
        return PROVIDER.getIfNullObtain("getOtherModel" + condition, () -> newOtherModel(condition));
    }

    /**
     * Get OverallModel
     * @return OverallModel
     */
    @NonNull
    default OnePresenter getPresenter() {
        return getPresenter(getModel());
    }

    /**
     * Get OverallModel
     * @return OverallModel
     */
    @NonNull
    default OnePresenter getPresenter2() {
        return getPresenter(getModel2());
    }

    /**
     * Get OverallModel
     * @return OverallModel
     */
    @NonNull
    default OnePresenter getPresenter3() {
        return getPresenter(getModel3());
    }

    /**
     * Get OverallModel
     * @return OverallModel
     */
    @NonNull
    default OnePresenter getPresenter4() {
        return getPresenter(getModel4());
    }

    /**
     * Get OverallModel
     * @return OverallModel
     */
    @NonNull
    default OnePresenter getPresenter5() {
        return getPresenter(getModel5());
    }

    /**
     * Get OverallModel
     * @return OverallModel
     */
    @NonNull
    default OnePresenter getPresenter6() {
        return getPresenter(getModel6());
    }

    /**
     * Get OverallModel
     * @return OverallModel
     */
    @NonNull
    default OnePresenter getPresenter7() {
        return getPresenter(getModel7());
    }

    /**
     * Get OverallModel
     * @return OverallModel
     */
    @NonNull
    default OnePresenter getPresenter8() {
        return getPresenter(getModel8());
    }

    /**
     * Get OverallModel
     * @return OverallModel
     */
    @NonNull
    default OnePresenter getPresenter9() {
        return getPresenter(getModel9());
    }

    /**
     * Get OverallModel
     * @param condition Condition
     * @return OverallModel
     */
    @NonNull
    default OnePresenter getOtherPresenter(int condition) {
        return getPresenter(getOtherModel(condition));
    }

    /**
     * Get OnePresenter
     * @param model OverallModel
     * @return OnePresenter
     */
    @NonNull
    default OnePresenter getPresenter(@NonNull OverallModel model) {
        return EasyUtils.transition(model.getPresenter());
    }

    /**
     * Set IOneView
     * @param view IOneView
     * @return Myself
     */
    default IDispatch setView(IOneView view) {
        return setView(getPresenter(), view);
    }

    /**
     * Set IOneView
     * @param view IOneView
     * @return Myself
     */
    default IDispatch setView2(IOneView view) {
        return setView(getPresenter2(), view);
    }

    /**
     * Set IOneView
     * @param view IOneView
     * @return Myself
     */
    default IDispatch setView3(IOneView view) {
        return setView(getPresenter3(), view);
    }

    /**
     * Set IOneView
     * @param view IOneView
     * @return Myself
     */
    default IDispatch setView4(IOneView view) {
        return setView(getPresenter4(), view);
    }

    /**
     * Set IOneView
     * @param view IOneView
     * @return Myself
     */
    default IDispatch setView5(IOneView view) {
        return setView(getPresenter5(), view);
    }

    /**
     * Set IOneView
     * @param view IOneView
     * @return Myself
     */
    default IDispatch setView6(IOneView view) {
        return setView(getPresenter6(), view);
    }

    /**
     * Set IOneView
     * @param view IOneView
     * @return Myself
     */
    default IDispatch setView7(IOneView view) {
        return setView(getPresenter7(), view);
    }

    /**
     * Set IOneView
     * @param view IOneView
     * @return Myself
     */
    default IDispatch setView8(IOneView view) {
        return setView(getPresenter8(), view);
    }

    /**
     * Set IOneView
     * @param view IOneView
     * @return Myself
     */
    default IDispatch setView9(IOneView view) {
        return setView(getPresenter9(), view);
    }

    /**
     * Set IOneView
     * @param condition Condition
     * @param view IOneView
     * @return Myself
     */
    default IDispatch setOtherView(int condition, IOneView view) {
        return setView(getOtherPresenter(condition), view);
    }

    /**
     * Set IOneView
     * @param presenter OnePresenter
     * @param view IOneView
     * @return Myself
     */
    default IDispatch setView(@NonNull OnePresenter presenter, IOneView view) {
        presenter.setView(view);
        return this;
    }
}
