package com.ziven.easygo.processor;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * @author :zhiyuan.zhou
 * @date :2021/5/31
 */
public abstract class AbstractEasyGoProcessor extends AbstractProcessor {

    protected Filer filer;
    protected Types types;
    protected Locale locale;
    protected Elements elements;
    protected Messager messager;
    protected SourceVersion sourceVersion;
    protected Map<String,String> options;
    protected Logger logger;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        filer = processingEnvironment.getFiler();
        locale = processingEnvironment.getLocale();
        types = processingEnvironment.getTypeUtils();
        options = processingEnvironment.getOptions();
        messager = processingEnvironment.getMessager();
        elements = processingEnvironment.getElementUtils();
        sourceVersion = processingEnvironment.getSourceVersion();

        logger = new Logger(getMessage());
    }

    protected Filer getFiler() {
        return filer;
    }

    protected Types getTypes() {
        return types;
    }

    protected Locale getLocale() {
        return locale;
    }

    protected Elements getElements() {
        return elements;
    }

    protected Messager getMessage() {
        return messager;
    }

    protected SourceVersion getSourceVersion() {
        return sourceVersion;
    }

    protected Map<String, String> getOptions() {
        return options;
    }

    protected Logger getLogger() {
        return logger;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if(set == null || set.isEmpty()) {
            getLogger().info(" AbstractEasyGoProcessor, has no method");
            return false;
        }
        try {
            getLogger().info(" AbstractEasyGoProcessor, Start process");
            parseProcess(roundEnvironment.getElementsAnnotatedWith(getAnnotationClass()));
        } catch (Exception e) {
            getLogger().error(e);
        }
        return false;
    }

    /**
     * Parse Process
     * @param elements Elements
     */
    protected abstract void parseProcess(Set<? extends Element> elements);

    /**
     * Get Annotation class
     * @return Class
     */
    protected abstract Class<? extends Annotation> getAnnotationClass();

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotation = new LinkedHashSet<>();
        annotation.add(getAnnotationClass().getCanonicalName());
        return annotation;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
