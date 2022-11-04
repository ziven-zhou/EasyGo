package com.ziven.easygo.ui;

/**
 * @author Ziven
 * @date 2021/5/29
 */
public abstract class AbstractTypeData {

    public static final int TYPE_NO = -1;
    private int mViewType = TYPE_NO;

    /**
     * Set view type
     * @param viewType View type
     */
    public void setViewType(int viewType) {
        mViewType = viewType;
    }

    /**
     * Get view type
     * @return View type
     */
    public int getViewType() {
        return mViewType;
    }
}
