package com.ziven.easygo.processor;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;

/**
 * @author :zhiyuan.zhou
 * @date :2021/5/31
 */
class Logger {
    private final Messager messager;

    public Logger(Messager messager) {
        this.messager = messager;
    }

    public void info(CharSequence info) {
        if (isNotEmpty(info)) {
            messager.printMessage(Diagnostic.Kind.NOTE, Constant.PREFIX_OF_LOGGER + info);
        }
    }

    public void error(CharSequence error) {
        if (isNotEmpty(error)) {
            messager.printMessage(Diagnostic.Kind.ERROR, Constant.PREFIX_OF_LOGGER + "An exception is encountered, [" + error + "]");
        }
    }

    public void error(Throwable error) {
        if (null != error) {
            messager.printMessage(Diagnostic.Kind.ERROR, Constant.PREFIX_OF_LOGGER + "An exception is encountered, [" + error.getMessage() + "]" + "\n" + formatStackTrace(error.getStackTrace()));
        }
    }

    public void warning(CharSequence warning) {
        if (isNotEmpty(warning)) {
            messager.printMessage(Diagnostic.Kind.WARNING, Constant.PREFIX_OF_LOGGER + warning);
        }
    }

    private boolean isNotEmpty(CharSequence charSequence) {
        return charSequence != null && charSequence.length() > 0;
    }

    private String formatStackTrace(StackTraceElement[] stackTrace) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : stackTrace) {
            sb.append("    at ").append(element.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
