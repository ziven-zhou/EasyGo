package com.ziven.easygo.annotation;

import androidx.annotation.Keep;

/**
 * @author :Ziven
 * @date :2021/6/2
 */
@Keep
public class EasyGoType {

    private final String pathName;
    private final ThreadMode threadMode;
    private IEasyGoMethod easyGoMethod;
    private Object target;

    public EasyGoType(String path, ThreadMode mode) {
        pathName = path;
        threadMode = mode;
    }

    public String getPathName() {
        return pathName;
    }

    public ThreadMode getThreadMode() {
        return threadMode;
    }

    public IEasyGoMethod getEasyGoMethod() {
        return easyGoMethod;
    }

    public void setEasyGoMethod(IEasyGoMethod easyGoMethod) {
        this.easyGoMethod = easyGoMethod;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public enum ThreadMode {
        /**
         * CURRENT: Current Thread,Main or Worker
         * MAIN: Main Thread
         * WORKER: Worker Thread
         */
        CURRENT,
        MAIN,
        WORKER
    }
}
