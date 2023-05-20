package com.ziven.easygo.task;

/**
 * @author Ziven
 */
public abstract class NoTimeoutTask extends EasyGoTask {

    @Override
    public long timeout() {
        return Long.MAX_VALUE;
    }
}
